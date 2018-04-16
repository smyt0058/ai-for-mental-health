package com.algonquincollege.smyt0058.oso.closetTabFragments;

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

public class HatsClosetTab extends Fragment {

    private boolean isWearingHat;
    private boolean isWearingHeadbow;
    private boolean isPurchasedHeadbow;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.closet_hats_layout, container, false);

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

        isWearingHat = prefs.getBoolean(SharedPrefUtils.HAT_WEAR_BOOL, false);
        isPurchasedHeadbow = prefs.getBoolean(SharedPrefUtils.HEADBOW_BUY_BOOL, false);
        isWearingHeadbow = prefs.getBoolean(SharedPrefUtils.HEADBOW_WEAR_BOOL, false);

        if (isWearingHat) {
            view.findViewById(R.id.closet_hat).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_hat).setAlpha(1);
        }

        if (isPurchasedHeadbow) {
            ImageView bow = view.findViewById(R.id.closet_headbow);
            bow.setClickable(true);
            bow.setVisibility(View.VISIBLE);
        }

        if(isWearingHeadbow){
            view.findViewById(R.id.closet_headbow).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_headbow).setAlpha(1);
        }

    }
}
