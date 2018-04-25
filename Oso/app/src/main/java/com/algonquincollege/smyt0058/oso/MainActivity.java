package com.algonquincollege.smyt0058.oso;

import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algonquincollege.smyt0058.oso.database.AppDatabase;
import com.algonquincollege.smyt0058.oso.database.UserChat;
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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private Button                  loginBtn;
    private EditText                email;
    private String                  emailValue;
    private EditText                password;
    private String                  passwordValue;
    private AppDatabase             database;
    public static BaseApiService    mApiService;

    public ProgressDialog           loading;

    public Context                  mContext;

    public Boolean                  onBoardingFirst;

    SharedPrefUtils                 SharedPrefUtils;

    private final String            DATABASE_NAME = "OSO_DATABASE";

    private ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        onBoardingFirst = SharedPrefUtils.getIsFirstLoggedIn(mContext);

        email = (EditText) findViewById(R.id.emailField);
        password = (EditText) findViewById(R.id.passwordField);

        loginBtn = (Button) findViewById(R.id.loginBtn);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                database.userDAO().nukeTable();
//
//
//            }
//        }) .start();

//        Date currentTime = Calendar.getInstance().getTime();
//        //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
//        ChatMessage onBoarding1 = new ChatMessage(getResources().getString(R.string.oso_onboarding_1), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
//        messageArrayList.add(onBoarding1);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().matches("")){
                    email.setError("Please enter your email address");
                    email.requestFocus();
                } else if (password.getText().toString().matches("")){
                    password.setError("Please enter your password");
                    password.requestFocus();
                } else {
                    emailValue = email.getText().toString().trim();
                    passwordValue = password.getText().toString().trim();



                    loading = ProgressDialog.show(mContext, null, "Logging in...", true, false);
                    requestLogin();
                }


            }
        });

        // The following code serves to check the session, If the session is true (already logged in)
        // then start ChatActivity immediately.
        if (SharedPrefUtils.getIsLoggedIn(mContext)){
            startActivity(new Intent(MainActivity.this, ChatActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    private void requestLogin(){
        mApiService.loginRequest(emailValue, passwordValue)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("errorType").equals("success")){
                                    // If the login is successful then the name data in the response API
                                    // will be parsed to the next activity.
                                    //Toast.makeText(mContext, "LOGIN", Toast.LENGTH_SHORT).show();
                                    Log.i("Object: ", jsonRESULTS.toString());
                                    String name = jsonRESULTS.getString("name");
                                    Log.i("Name: ", name);
                                    String authkey = jsonRESULTS.getString("authkey");
                                    Log.i("AuthKey: ", authkey);

                                    SharedPrefUtils.put(mContext, "authkey", authkey);

                                    //Toast.makeText(mContext, SharedPrefUtils.get(mContext, "authkey"), Toast.LENGTH_SHORT).show();

                                    //sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                    // Shared Pref ini berfungsi untuk menjadi trigger session login
                                    //sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                    SharedPrefUtils.saveIsLoggedIn(mContext, SharedPrefUtils.ISLOGGED, true);

                                    Date currentTime = Calendar.getInstance().getTime();
                                    //ArrayList<ChatMessage> messageArrayList = new ArrayList<ChatMessage>();
                                    ChatMessage onBoarding1 = new ChatMessage(getResources().getString(R.string.oso_onboarding_1), ChatMessage.MSG_TYPE_RECEIVED, currentTime);
                                    messageArrayList.add(onBoarding1);

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            UserChat userChat =new UserChat();
                                            userChat.setUserID(1);
                                            userChat.setChatHistory(messageArrayList);
                                            database.userDAO().insertUser(userChat);
                                        }
                                    }) .start();

                                    //userDBSetup();

                                    if(!onBoardingFirst) {
                                        SharedPrefUtils.saveIsFirstLoggedIn(mContext, SharedPrefUtils.ISFIRSTLOGGED, true);
                                        startActivity(new Intent(mContext, OnBoardingActivity.class));
                                        finish();
                                    } else {

                                        startActivity(new Intent(mContext, ChatActivity.class)
                                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                        finish();
                                    }
                                } else {

                                    AlertDialog.Builder builder;

                                    builder = new AlertDialog.Builder(MainActivity.this);

                                    builder.setTitle("Whoops!")
                                            .setMessage("Oso looked and looked but couldn't find your email and/or password anywhere. \nRemember that email we sent you with all your info? Make sure the email and password you enter match what we sent you in that email.")
                                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    email.setText("");
                                                    password.setText("");
                                                }
                                            })
                                            .setIcon(R.drawable.ic_oso_face_icon)
                                            .show();

                                    String error_message = jsonRESULTS.getString("errorType");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        Toast.makeText(mContext, "Whoops! There seems to be an issue with the server at the moment. Please try again after a short while.", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                });
    }


}
