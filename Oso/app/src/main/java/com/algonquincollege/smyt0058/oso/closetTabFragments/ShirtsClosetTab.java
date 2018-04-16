package com.algonquincollege.smyt0058.oso.closetTabFragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algonquincollege.smyt0058.oso.R;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-27.
 */

public class ShirtsClosetTab extends Fragment {

    private boolean isWearingShirt;

    private ConstraintLayout constraintLayout;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        //TODO when fragment is created, check against preferences and change state of ImageViews
        view = inflater.inflate(R.layout.closet_shirts_layout, container, false);

        setState(view);

        Log.i("TAG", view.toString());



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setState(view);

    }

    public void setState(View view) {

        SharedPreferences prefs = SharedPrefUtils.getAppState(getActivity());

        isWearingShirt = prefs.getBoolean(SharedPrefUtils.SHIRT_WEAR_BOOL, false);

        if (isWearingShirt) {
            view.findViewById(R.id.closet_shirt).setAlpha(0.5f);
        } else {
            view.findViewById(R.id.closet_shirt).setAlpha(1);
        }

    }

}
