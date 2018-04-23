package com.algonquincollege.smyt0058.oso;

import android.app.DialogFragment;
import android.arch.persistence.room.Room;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquincollege.smyt0058.oso.database.AppDatabase;
import com.algonquincollege.smyt0058.oso.database.Converters;
import com.algonquincollege.smyt0058.oso.database.UserChat;

import com.algonquincollege.smyt0058.oso.fragments.FeedOsoDialogFragment;

import com.algonquincollege.smyt0058.oso.models.ChatMessage;
import com.algonquincollege.smyt0058.oso.util.api.BaseApiService;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;
import com.algonquincollege.smyt0058.oso.util.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jason on 2018-03-19.
 */

public class ChatActivity extends AppCompatActivity {

    private static final int    REQ_CODE_SPEECH_INPUT = 100;
    private EditText            userMessage;
    private RecyclerView        mMessageRecyclerview;
    private RelativeLayout      addBtn;
    private Boolean             flagFab = true;
    private ChatAdapter         chatAdapter;
    private Toolbar             toolbar;
    private BaseApiService      mApiService;
    private int                 sessionID = 1;
    private int                 lastQuestionDay;
  
    public int                  questionAskedMax = 0;
    public int                  pawPoints = 0;
    public boolean              isTimeForQuestion;
    private AppDatabase         database;


    private boolean             isQuestionnaire = false;
    private boolean             isJournal = false;
    private boolean             isFallBack = false;

    private static final String FEED_OSO_DIALOG_TAG = "Feed Oso Dialog";
    private final String        DATABASE_NAME = "OSO_DATABASE";
    private final String        JOURNAL_ENTRY_EVENT = "journalEntry";
    private final String        START_QUESTIONNAIRE_EVENT = "question";
    private final String        REGULAR_CHAT_EVENT = "";

    ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();

    private String authkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences prefs = SharedPrefUtils.getAppState(getApplicationContext());

        pawPoints = prefs.getInt(SharedPrefUtils.PAW_POINTS, 0);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Cursor cursor = database.userDAO().getChatHistoryByID();

                cursor.moveToFirst();
                String test = cursor.getString(cursor.getColumnIndex("chatHistory"));

                messageArrayList = Converters.fromString(test);
                Log.i("messageArrayList", test);

            }
        }) .start();



        //String test = cursor.getString(cursor.getColumnIndex("chatHistory"));



        lastQuestionDay = Calendar.DAY_OF_MONTH;

        mApiService = UtilsApi.getAPIService();

        authkey = SharedPrefUtils.get(getApplicationContext(), "authkey");

        setContentView(R.layout.activity_message_list);

        toolbar = (Toolbar) findViewById(R.id.oso_app_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.RECORD_AUDIO}, 1);

        mMessageRecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        userMessage = (EditText) findViewById(R.id.messageInputField);
        addBtn = (RelativeLayout) findViewById(R.id.inputBtn);

        //setting up layout manager
        mMessageRecyclerview.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageRecyclerview.setLayoutManager(linearLayoutManager);



        //creates a dummy message for testing purposes. when connected just initialize list

        //grabs adapter
        chatAdapter = new ChatAdapter(messageArrayList);

//        Date currentTime = Calendar.getInstance().getTime();
//        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
//        ChatMessage onBoarding1 = new ChatMessage(getResources().getString(R.string.oso_onboarding_1), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
//        chatAdapter.addMessage(onBoarding1);

        //sets adapter
        mMessageRecyclerview.setAdapter(chatAdapter);

        //onBoarding();




        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String messageContent = userMessage.getText().toString().trim();

                if (!TextUtils.isEmpty(messageContent)) {

                    Date currentTime = Calendar.getInstance().getTime();

                    ChatMessage message = new ChatMessage(messageContent, ChatMessage.MSG_TYPE_SENT, currentTime);
                    chatAdapter.addMessage(message);

//                    scrollToBottom();

                    userMessage.getText().clear();

                    //pawPoints += pawPointToast(50);

                    msgEventPost(messageContent, REGULAR_CHAT_EVENT);
                    
                    //pawPoints = pawPointToast(25);
                    //gordyMessageSend(messageContent);
                    scrollToBottom();

                    }
                    else {

                    startVoiceInput();
                }

            }
        });

        // watches to see if text is inputted then changes images accordingly
        userMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ImageView fab_img = (ImageView) findViewById(R.id.fab_img);
                Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.ic_send_white_24dp);
                Bitmap img1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mic_white_24dp);


                if (s.toString().trim().length() != 0 && flagFab) {
                    ImageViewAnimatedChange(ChatActivity.this, fab_img, img);
                    flagFab = false;

                } else if (s.toString().trim().length() == 0) {
                    ImageViewAnimatedChange(ChatActivity.this, fab_img, img1);
                    flagFab = true;

                }


            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPrefUtils.putPawPointState(getApplicationContext(), pawPoints);

        new Thread(new Runnable() {
            @Override
            public void run() {
                UserChat userChat =new UserChat();
                userChat.setUserID(1);
                userChat.setChatHistory(chatAdapter.messagesList);
                database.userDAO().updateUser(userChat);
            }
        }) .start();



    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = SharedPrefUtils.getAppState(getApplicationContext());

        pawPoints = prefs.getInt(SharedPrefUtils.PAW_POINTS, 0);

    }

    public void msgPost(String content) {

        Call<ResponseBody> call = mApiService.msgPost(authkey, content);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){

                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("errorMessage").equals("success") && !jsonRESULTS.getString("message").equals("")){

                            // If the login is successful then the name data in the response API
                            // will be parsed to the next activity.
                            Log.i("Object: ", jsonRESULTS.toString());
                            String content = jsonRESULTS.getString("message");
                            Log.i("content: ", content);

                            Date currentTime = Calendar.getInstance().getTime();

                            ChatMessage message = new ChatMessage(content, ChatMessage.MSG_TYPE_RECEIVED, currentTime);

                            chatAdapter.addMessage(message);

                            scrollToBottom();



                        }
                        else {

                            String error_message = jsonRESULTS.getString("errorMessage");
                            serverErrorOso();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e( "tag", "Retrofit Error: " + t.getLocalizedMessage() );
                //Toast.makeText(ChatActivity.this, "Retrofit Error", Toast.LENGTH_LONG).show();
                serverErrorOso();

            }
        });
    }

    public void msgEventPost(String content, String event) {

        //Toast.makeText(ChatActivity.this, "msgPost called, passing : " + content, Toast.LENGTH_LONG).show();

        Call<ResponseBody> call = mApiService.msgEventPost(authkey, content, event);


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){

                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("errorMessage").equals("success") && !jsonRESULTS.getString("message").equals("")){
                            // If the login is successful then the name data in the response API
                            // will be parsed to the next activity.
                            Log.i("Object: ", jsonRESULTS.toString());
                            String content = jsonRESULTS.getString("message");
                            Log.i("content: ", content);

                            Date currentTime = Calendar.getInstance().getTime();

                            ChatMessage message = new ChatMessage(content, ChatMessage.MSG_TYPE_RECEIVED, currentTime);

                            //Toast.makeText(ChatActivity.this, "Response: " + message.getMessageContent(), Toast.LENGTH_SHORT).show();



                            chatAdapter.addMessage(message);

                            //questionAskedMax += 1;

                            scrollToBottom();

                            //pawPoints += pawPointToast(75);



                        }
                        else {
                            // Jika login gagal
                            String error_message = jsonRESULTS.getString("errorMessage");
                            Toast.makeText(ChatActivity.this, error_message, Toast.LENGTH_SHORT).show();
                            serverErrorOso();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e( "tag", "Retrofit Error: " + t.getLocalizedMessage() );
                //Toast.makeText(ChatActivity.this, "Retrofit Error", Toast.LENGTH_LONG).show();
                serverErrorOso();

            }
        });
    }

    public void scrollToBottom() {

        int newPosition = chatAdapter.getItemCount() - 1;

        // Notify recycler view insert one new data.
        chatAdapter.notifyItemInserted(newPosition);

        // Scroll RecyclerView to the last message.
        mMessageRecyclerview.scrollToPosition(newPosition);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    //Toolbar navigation button methods
    public void settingsBtnOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void closetBtnOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ClosetActivity.class);
        startActivity(intent);
    }

    //inflates food fragment
    public void osoFoodBtnClick(View view) {

        DialogFragment newFragment = new FeedOsoDialogFragment();
        newFragment.show(getFragmentManager(), FEED_OSO_DIALOG_TAG);


    }

    // setting up speech to text intent
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "What would you like to say to Oso?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    userMessage.setText(result.get(0));
                }
                break;
            }

        }
    }

    // runs animation for input button image change
    public void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.zoom_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.zoom_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageBitmap(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

    public void onBoarding() {

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Date currentTime = Calendar.getInstance().getTime();
                        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
                        ChatMessage onBoarding1 = new ChatMessage(getResources().getString(R.string.oso_onboarding_1), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
                        chatAdapter.addMessage(onBoarding1);
                        scrollToBottom();
                    }
                }, 1000);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Date currentTime = Calendar.getInstance().getTime();
                        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
                        ChatMessage onBoarding2 = new ChatMessage(getResources().getString(R.string.oso_onboarding_2), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
                        chatAdapter.addMessage(onBoarding2);
                        scrollToBottom();
                    }
                }, 3000);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Date currentTime = Calendar.getInstance().getTime();
                        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
                        ChatMessage onBoarding3 = new ChatMessage(getResources().getString(R.string.oso_onboarding_3), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
                        chatAdapter.addMessage(onBoarding3);
                        scrollToBottom();
                    }
                }, 5000);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Date currentTime = Calendar.getInstance().getTime();
                        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
                        ChatMessage onBoarding4 = new ChatMessage(getResources().getString(R.string.oso_onboarding_4), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
                        chatAdapter.addMessage(onBoarding4);
                        scrollToBottom();
                    }
                }, 7000);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Date currentTime = Calendar.getInstance().getTime();
                        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
                        ChatMessage onBoarding4 = new ChatMessage(getResources().getString(R.string.oso_onboarding_5), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
                        chatAdapter.addMessage(onBoarding4);
                        scrollToBottom();
                    }
                }, 8000);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Date currentTime = Calendar.getInstance().getTime();
                        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
                        ChatMessage onBoarding4 = new ChatMessage(getResources().getString(R.string.oso_onboarding_6), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
                        chatAdapter.addMessage(onBoarding4);
                        scrollToBottom();
                    }
                }, 8000);


    }

    public int pawPointToast(int pawPoints) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.paw_point_toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_oso_paw_pink);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Awesome! you get " + pawPoints + " Paw Points!" );

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

        return pawPoints;

    }

    public void serverErrorOso() {
        Date currentTime = Calendar.getInstance().getTime();
        ChatMessage onBoarding1 = new ChatMessage("This is embarassing... It looks like the server were I live is down right now. Feel free to try again later :) ", ChatMessage.MSG_TYPE_RECEIVED, currentTime);
        chatAdapter.addMessage(onBoarding1);

    }


}

