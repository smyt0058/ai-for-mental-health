package com.algonquincollege.smyt0058.oso;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Jason on 2018-03-27.
 */

public class ClosetTabAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> closetFragmentList = new ArrayList<>();

    public ClosetTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return closetFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return closetFragmentList.size();
    }

    public void AddFragment(Fragment fragment) {
        closetFragmentList.add(fragment);

    }
}
