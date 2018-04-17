package com.algonquincollege.smyt0058.oso.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.algonquincollege.smyt0058.oso.R;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-27.
 */

public class NeckWearClosetTab extends Fragment {

    private boolean isWearingBowtie;
    private boolean isWearingPinkBowtie;
    private boolean isPurchasedPinkBowtie;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //TODO when fragment is created, check against preferences and change state of ImageViews
        view = inflater.inflate(R.layout.closet_neck_wear_layout, container, false);

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


        isWearingBowtie = prefs.getBoolean(SharedPrefUtils.BOWTIE_WEAR_BOOL, false);
        isWearingPinkBowtie = prefs.getBoolean(SharedPrefUtils.PINK_BOWTIE_WEAR_BOOL, false);
        isPurchasedPinkBowtie = prefs.getBoolean(SharedPrefUtils.PINK_BOWTIE_BUY_BOOL, false);

        if(isPurchasedPinkBowtie){
            ImageView pBowtie = view.findViewById(R.id.closet_pink_bowtie);
            pBowtie.setClickable(true);
            pBowtie.setVisibility(View.VISIBLE);
        }

        if (isWearingBowtie) {
            view.findViewById(R.id.closet_bowtie).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_bowtie).setAlpha(1);
        }

        if (isWearingPinkBowtie) {
            view.findViewById(R.id.closet_pink_bowtie).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_pink_bowtie).setAlpha(1);
        }

    }

}
