package com.algonquincollege.smyt0058.oso.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
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

/**
 * Created by Jason on 2018-04-17.
 */

public class ChangePasswordDialog extends android.app.DialogFragment implements View.OnClickListener {
    
    private EditText        oldPasswordET;
    private EditText        newPasswordET;
    private EditText        confirmPasswordET;

    private Button          doneBtn;
    private Button          cancelBtn;

    private BaseApiService  mApiService;

    private View            view;
    private Context         context;

    private LayoutInflater  inflater;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mApiService = UtilsApi.getAPIService();
        

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        inflater = (getActivity()).getLayoutInflater();

        context = getContext();

        view = inflater.inflate(R.layout.oso_change_password_dialog,  null);

        oldPasswordET = view.findViewById(R.id.nameChangeBtn);
        newPasswordET = view.findViewById(R.id.newPasswordET);
        confirmPasswordET = view.findViewById(R.id.confirmPasswordET);

        doneBtn = view.findViewById(R.id.donePassChange);
        cancelBtn = view.findViewById(R.id.cancel);
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
            if(!newPasswordET.getText().toString().equals(confirmPasswordET.getText().toString()) ){
                confirmPasswordET.setError("Does not match new password");
                confirmPasswordET.requestFocus();
            } else {

                Call<ResponseBody> call = mApiService.changePasswordPost(SharedPrefUtils.get(getContext(), "authkey"), oldPasswordET.getText().toString() ,confirmPasswordET.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()){

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("errorMessage").equals("success")){

                                    newPasswordToast("Your password has been changed!");


                                }
                                else {
                                    String error_message = jsonRESULTS.getString("errorMessage");
                                    newPasswordToast("old password isn't a match, please input again");

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
            }
        } else if(view.equals(cancelBtn)) {
            oldPasswordET.setText("");
            newPasswordET.setText("");
            confirmPasswordET.setText("");
            this.dismiss();
        }
    }

    public void newPasswordToast(String message) {

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
