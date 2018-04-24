package com.algonquincollege.smyt0058.oso.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.algonquincollege.smyt0058.oso.R;
import com.algonquincollege.smyt0058.oso.util.api.BaseApiService;
import com.algonquincollege.smyt0058.oso.util.api.UtilsApi;

public class ChangeNameDialog extends DialogFragment implements View.OnClickListener{

    private BaseApiService  mApiService;

    private EditText        nameET;

    private Button          cancelBtn;
    private Button          doneBtn;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mApiService = UtilsApi.getAPIService();






        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = (getActivity()).getLayoutInflater();

        View view = inflater.inflate(R.layout.oso_change_name_dialog,  null);
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
            String name = nameET.getText().toString();
            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();

            Toast.makeText(getContext(), "I will call you: " + name, Toast.LENGTH_SHORT).show();
            this.dismiss();
        } else  {
            nameET.setText("");
            this.dismiss();
        }

    }
}
