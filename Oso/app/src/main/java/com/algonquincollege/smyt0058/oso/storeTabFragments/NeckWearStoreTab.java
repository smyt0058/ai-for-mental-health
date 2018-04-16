package com.algonquincollege.smyt0058.oso.storeTabFragments;

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

public class NeckWearStoreTab extends Fragment {

    private boolean isPurchasedPinkBowtie;

    private View view;

    public NeckWearStoreTab() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //TODO when fragment is created, check against preferences and change state of ImageViews

        view = inflater.inflate(R.layout.market_neck_wear_layout, container, false);

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

        isPurchasedPinkBowtie = prefs.getBoolean(SharedPrefUtils.PINK_BOWTIE_BUY_BOOL, false);
        Log.i("Is p bowtie bought" , String.valueOf(isPurchasedPinkBowtie));

        if (isPurchasedPinkBowtie) {
            ImageView bow = view.findViewById(R.id.market_pink_bowtie);
            ImageView sold = view.findViewById(R.id.pink_bowtie_sold);
            bow.setClickable(false);
            sold.setVisibility(View.VISIBLE);
        } else {
            ImageView bow = view.findViewById(R.id.market_pink_bowtie);
            ImageView sold = view.findViewById(R.id.pink_bowtie_sold);
            bow.setClickable(true);
            sold.setVisibility(View.INVISIBLE);
        }

    }

}
