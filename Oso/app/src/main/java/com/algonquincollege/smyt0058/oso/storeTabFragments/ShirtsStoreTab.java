package com.algonquincollege.smyt0058.oso.storeTabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.algonquincollege.smyt0058.oso.R;

/**
 * Created by Jason on 2018-03-27.
 */

public class ShirtsStoreTab extends Fragment {

    public ShirtsStoreTab() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //TODO when fragment is created, check against preferences and change state of ImageViews

        return inflater.inflate(R.layout.market_shirts_layout, container, false);
    }

}
