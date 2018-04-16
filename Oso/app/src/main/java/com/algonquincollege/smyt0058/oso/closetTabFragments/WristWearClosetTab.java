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

public class WristWearClosetTab extends Fragment {

    private boolean isWearingWatch;
    private boolean isWearingMp3;
    private boolean isPurchasedMp3;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //TODO when fragment is created, check against preferences and change state of ImageViews

        view = inflater.inflate(R.layout.closet_wrist_wear_layout, container, false);

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

        isWearingWatch = prefs.getBoolean(SharedPrefUtils.WATCH_WEAR_BOOL, false);
        isWearingMp3 = prefs.getBoolean(SharedPrefUtils.MP3_WEAR_BOOL, false);
        isPurchasedMp3 = prefs.getBoolean(SharedPrefUtils.MP3_BUY_BOOL, false);

        if(isPurchasedMp3){
            ImageView pMp3 = view.findViewById(R.id.closet_mp3);
            pMp3.setClickable(true);
            pMp3.setVisibility(View.VISIBLE);
        }

        if (isWearingWatch) {
            view.findViewById(R.id.closet_watch).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_watch).setAlpha(1);
        }
        if (isWearingMp3) {
            view.findViewById(R.id.closet_mp3).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_mp3).setAlpha(1);
        }

    }

}
