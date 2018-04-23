package com.algonquincollege.smyt0058.oso.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
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

/**
 * Created by Jason on 2018-04-17.
 */

public class ChangePasswordDialog extends android.app.DialogFragment implements View.OnClickListener {
    
    private EditText    oldPasswordET;
    private EditText    newPasswordET;
    private EditText    confirmPasswordET;

    private Button      doneBtn;
    private Button      cancelBtn;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = (getActivity()).getLayoutInflater();

        View view = inflater.inflate(R.layout.oso_change_password_dialog,  null);
        oldPasswordET = view.findViewById(R.id.oldPasswordET);
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
                newPasswordToast();
                this.dismiss();
            }
        } else if(view.equals(cancelBtn)) {
            oldPasswordET.setText("");
            newPasswordET.setText("");
            confirmPasswordET.setText("");
            this.dismiss();
        }
    }

    public void newPasswordToast() {

        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View layout = inflater.inflate(R.layout.paw_point_toast_layout,
                (ViewGroup) getActivity().findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_oso_face_icon);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText("Your password has been changed!");

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();

    }

}
