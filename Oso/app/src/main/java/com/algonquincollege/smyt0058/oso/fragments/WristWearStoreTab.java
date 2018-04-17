package com.algonquincollege.smyt0058.oso.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.algonquincollege.smyt0058.oso.R;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-27.
 */

public class WristWearStoreTab extends Fragment {

    private boolean isPurchasedMp3;

    private View view;

    public WristWearStoreTab() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //TODO when fragment is created, check against preferences and change state of ImageViews
        view = inflater.inflate(R.layout.market_wrist_wear_layout, container, false);

        setState(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setState(view);

    }

    public void setState(View view) {

        SharedPreferences prefs = SharedPrefUtils.getAppState(getActivity());

        isPurchasedMp3 = prefs.getBoolean(SharedPrefUtils.MP3_BUY_BOOL, false);
        Log.i("Is Mp3 Purchased", String.valueOf(isPurchasedMp3));

        if (isPurchasedMp3) {
            ImageView bow = view.findViewById(R.id.market_mp3);
            ImageView sold = view.findViewById(R.id.mp3_sold);
            bow.setClickable(false);
            sold.setVisibility(View.VISIBLE);
        } else {
            ImageView bow = view.findViewById(R.id.market_mp3);
            ImageView sold = view.findViewById(R.id.mp3_sold);
            bow.setClickable(true);
            sold.setVisibility(View.INVISIBLE);
        }

    }
}
