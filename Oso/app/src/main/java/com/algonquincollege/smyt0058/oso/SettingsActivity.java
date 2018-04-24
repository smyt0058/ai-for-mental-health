package com.algonquincollege.smyt0058.oso;


import android.app.DialogFragment;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.algonquincollege.smyt0058.oso.database.AppDatabase;
import com.algonquincollege.smyt0058.oso.fragments.ChangeNameDialog;
import com.algonquincollege.smyt0058.oso.fragments.ChangePasswordDialog;
import com.algonquincollege.smyt0058.oso.fragments.TimePickerFragment;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-22.
 */

public class SettingsActivity extends AppCompatActivity {

    SharedPrefUtils                 SharedPrefUtils;

    private Context                 mContext;

    private EditText                editText;

    private int                     questionHour;
    private int                     questionMinute;

    private int                     pawPoints = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mContext = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.settingsToolbar);
        toolbar.setNavigationIcon(R.drawable.ic_oso_back_icon_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public void logoutBtnOnClick (View view) {

        SharedPrefUtils.saveIsLoggedIn(mContext, SharedPrefUtils.ISLOGGED, false);

        SharedPrefUtils.putInitialState(getApplicationContext(),
                pawPoints,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                13,
                00);

        final AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "OSO_DATABASE").build();



        new Thread(new Runnable() {
            @Override
            public void run() {

                database.userDAO().nukeTable();


            }
        }) .start();

        startActivity(new Intent(mContext, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();

    }

    public void changeTimeBtnOnClick(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public void changeNameOnClick(View view) {
        DialogFragment newFragment = new ChangeNameDialog();
        newFragment.show(getFragmentManager(),"change name dialog");
    }

    public void changePasswordClick(View view) {

        Toast.makeText(mContext, "Change Password button clicked", Toast.LENGTH_SHORT).show();

        DialogFragment newFragment = new ChangePasswordDialog();
        newFragment.show(getFragmentManager(),"change password dialog");

    }

}
