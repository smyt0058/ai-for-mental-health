package com.algonquincollege.smyt0058.oso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-22.
 */

public class SettingsActivity extends AppCompatActivity {

    SharedPrefUtils                 SharedPrefUtils;

    private Context                 mContext;

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
                true,
                "theme1");

        startActivity(new Intent(mContext, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();

    }


}
