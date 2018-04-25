package com.algonquincollege.smyt0058.oso.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algonquincollege.smyt0058.oso.R;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-27.
 */

public class EyeWearClosetTab extends Fragment {

    private boolean isWearingMonocle;

    View view;

    public EyeWearClosetTab() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


            view = inflater.inflate(R.layout.closet_eye_wear_layout, container, false);

            setState(view);

            return view;

    }

    //Sets current state every time the fragment comes back into view
    @Override
    public void onResume() {
        super.onResume();

        setState(view);

    }

    //Sets current tab state based off of data from shared preferences
    public void setState(View view) {

        SharedPreferences prefs = SharedPrefUtils.getAppState(getActivity());

        isWearingMonocle = prefs.getBoolean(SharedPrefUtils.MONOCLE_WEAR_BOOL, false);

        if (isWearingMonocle) {
            view.findViewById(R.id.closet_monocle).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_monocle).setAlpha(1);
        }

    }
}
