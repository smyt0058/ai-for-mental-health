package com.algonquincollege.smyt0058.oso;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Jason on 2018-03-27.
 */

public class MarketTabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> storeFragmentList = new ArrayList<>();

    public MarketTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return storeFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return storeFragmentList.size();
    }

    public void AddFragment(Fragment fragment) {
        storeFragmentList.add(fragment);

    }
}
