package com.algonquincollege.smyt0058.oso.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.algonquincollege.smyt0058.oso.R;
import com.algonquincollege.smyt0058.oso.util.api.BaseApiService;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;
import com.algonquincollege.smyt0058.oso.util.api.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeNameDialog extends DialogFragment implements View.OnClickListener{

    private BaseApiService  mApiService;

    private EditText        nameET;

    private Button          cancelBtn;
    private Button          doneBtn;

    private View            view;
    private Context         context;

    private LayoutInflater  inflater;

    private String          currentName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mApiService = UtilsApi.getAPIService();






        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        inflater = (getActivity()).getLayoutInflater();

        context = getContext();

        view = inflater.inflate(R.layout.oso_change_name_dialog,  null);
        nameET = view.findViewById(R.id.nameET);
        doneBtn = view.findViewById(R.id.doneNameChange);
        cancelBtn = view.findViewById(R.id.cancelNameChange);

        doneBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout
        builder.setCancelable(false);
        builder.setIcon(R.drawable.places_ic_search);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onClick(View view) {

        if(view.equals(doneBtn)){
            currentName = nameET.getText().toString();
            currentName = currentName.substring(0,1).toUpperCase() + currentName.substring(1).toLowerCase();

            Call<ResponseBody> call = mApiService.changeNamePost(SharedPrefUtils.get(getContext(), "authkey"), currentName);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()){

                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                            if (jsonRESULTS.getString("errorMessage").equals("success")){

                                newNameToast("Awesome! I will cal you " + currentName + " :)");

                            }
                            else {
                                String error_message = jsonRESULTS.getString("errorMessage");


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
                    Toast.makeText(getContext(), "Retrofit Error", Toast.LENGTH_LONG).show();

                }
            });

            this.dismiss();
        } else  {
            nameET.setText("");
            this.dismiss();
        }

    }

    public void newNameToast(String message) {

        View layout = inflater.inflate(R.layout.paw_point_toast_layout,
                (ViewGroup) view.findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_oso_face_icon);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }
}
