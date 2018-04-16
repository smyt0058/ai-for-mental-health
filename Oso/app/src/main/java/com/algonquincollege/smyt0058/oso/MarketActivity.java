package com.algonquincollege.smyt0058.oso;

import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.algonquincollege.smyt0058.oso.storeTabFragments.EyeWearStoreTab;
import com.algonquincollege.smyt0058.oso.storeTabFragments.HatsStoreTab;
import com.algonquincollege.smyt0058.oso.storeTabFragments.NeckWearStoreTab;
import com.algonquincollege.smyt0058.oso.storeTabFragments.ShirtsStoreTab;
import com.algonquincollege.smyt0058.oso.storeTabFragments.WristWearStoreTab;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-22.
 */

public class MarketActivity extends AppCompatActivity {

    private MarketTabAdapter mTabAdapter;

    private ViewPager       mViewPager;

    private boolean         isPurchasedMp3 = false;
    private boolean         isPurchasedHeadbow = false;
    private boolean         isPurchasedPinkBowtie = false;

    private int             pawPoints;

    private static final String  OOPS_NO_POINTS_TAG = "no points dialog";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        Toolbar toolbar = (Toolbar) findViewById(R.id.storeToolbar);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_oso_back_icon_white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences prefs = SharedPrefUtils.getAppState(getApplicationContext());
        pawPoints = prefs.getInt(SharedPrefUtils.PAW_POINTS, 0);

        TextView pawPointNumber = findViewById(R.id.paw_points_amount);
        pawPointNumber.setText(String.valueOf(pawPoints));


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mTabAdapter = new MarketTabAdapter(getSupportFragmentManager());

        mTabAdapter.AddFragment(new ShirtsStoreTab());
        mTabAdapter.AddFragment(new HatsStoreTab());
        mTabAdapter.AddFragment(new EyeWearStoreTab());
        mTabAdapter.AddFragment(new NeckWearStoreTab());
        mTabAdapter.AddFragment(new WristWearStoreTab());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.store_view_pager);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mTabAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.store_tab_layout);

        tabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabAdapter.getCount(); i++) {

            switch (i) {

                case 0:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_tab_shirt_vector);
                    //Toast.makeText(this, "I is " + i, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_tab_hat_vector);
                    //Toast.makeText(this, "I is " + i, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_tab_monocle_vector);
                    //Toast.makeText(this, "I is " + i, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_tab_bowtie_vector);
                    //Toast.makeText(this, "I is " + i, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_tab_watch_vector);
                    //Toast.makeText(this, "I is " + i, Toast.LENGTH_SHORT).show();
                    break;
            }

        }

        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        tab.getIcon().setColorFilter(Color.parseColor("#FF4081"), PorterDuff.Mode.SRC_IN);
                        //tab.getCustomView().setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        tab.getIcon().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
                        //tab.getCustomView().setBackgroundColor(Color.parseColor("#303F9F"));
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );





    }



    @Override
    protected void onStop() {
        super.onStop();

        //SharedPrefUtils.putMarketState(getApplicationContext(), pawPoints, isPurchasedMp3, isPurchasedHeadbow, isPurchasedPinkBowtie);

    }

    public void tabMarketHeadbowClick(View view) {

        if (pawPoints < 25){
            DialogFragment newFragment = new OopsNoPointsFragment();
            newFragment.show(getFragmentManager(), OOPS_NO_POINTS_TAG);
        } else {
            pawPoints -= 25;

            isPurchasedHeadbow = true;
            findViewById(R.id.headbow_sold).setVisibility(View.VISIBLE);
            findViewById(R.id.market_headbow).setClickable(false);

            //TextView pawPointWorth = findViewById(R.id.paw_points_amount_headbow);
            //int pawPointsDeduction = Integer.getInteger(pawPointWorth.getText().toString());
            SharedPrefUtils.putPawPointState(getApplicationContext(), pawPoints);
            TextView pawPointNumber = findViewById(R.id.paw_points_amount);
            pawPointNumber.setText(String.valueOf(pawPoints));

            SharedPrefUtils.putHeadbowState(getApplicationContext(), isPurchasedHeadbow);
        }



    }

    public void tabMarketPinkBowtieClick(View view) {

        if (pawPoints < 25){
            DialogFragment newFragment = new OopsNoPointsFragment();
            newFragment.show(getFragmentManager(), OOPS_NO_POINTS_TAG);
        } else {
            pawPoints -= 25;

            isPurchasedPinkBowtie = true;
            findViewById(R.id.pink_bowtie_sold).setVisibility(View.VISIBLE);
            findViewById(R.id.market_pink_bowtie).setClickable(false);
            SharedPrefUtils.putPawPointState(getApplicationContext(), pawPoints);
            //TextView pawPointWorth = findViewById(R.id.paw_points_amount_pink_bowtie);
            //int pawPointsDeduction = Integer.getInteger(pawPointWorth.getText().toString());



            TextView pawPointNumber = findViewById(R.id.paw_points_amount);
            pawPointNumber.setText(String.valueOf(pawPoints));
            SharedPrefUtils.putPinkBowtieState(getApplicationContext(), isPurchasedPinkBowtie);
        }



    }

    public void tabMarketMp3Click(View view) {

        if (pawPoints < 25){
            DialogFragment newFragment = new OopsNoPointsFragment();
            newFragment.show(getFragmentManager(), OOPS_NO_POINTS_TAG);
        } else {
            pawPoints -= 25;

            isPurchasedMp3 = true;
            findViewById(R.id.mp3_sold).setVisibility(View.VISIBLE);
            findViewById(R.id.market_mp3).setClickable(false);
            SharedPrefUtils.putPawPointState(getApplicationContext(), pawPoints);
            //TextView pawPointWorth = findViewById(R.id.paw_points_amount_mp3);
            //int pawPointsDeduction = Integer.getInteger(pawPointWorth.getText().toString());

            TextView pawPointNumber = findViewById(R.id.paw_points_amount);
            pawPointNumber.setText(String.valueOf(pawPoints));
            SharedPrefUtils.putMp3State(getApplicationContext(), isPurchasedMp3);
        }



    }




}
