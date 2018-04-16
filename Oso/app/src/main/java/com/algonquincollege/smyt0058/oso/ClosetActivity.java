package com.algonquincollege.smyt0058.oso;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.algonquincollege.smyt0058.oso.closetTabFragments.EyeWearClosetTab;
import com.algonquincollege.smyt0058.oso.closetTabFragments.HatsClosetTab;
import com.algonquincollege.smyt0058.oso.closetTabFragments.NeckWearClosetTab;
import com.algonquincollege.smyt0058.oso.closetTabFragments.ShirtsClosetTab;
import com.algonquincollege.smyt0058.oso.closetTabFragments.WristWearClosetTab;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

/**
 * Created by Jason on 2018-03-22.
 */

public class ClosetActivity extends AppCompatActivity {

    private ClosetTabAdapter    mTabAdapter;
    private ViewPager           mViewPager;

    private boolean             isMp3Purchased = false;
    private boolean             isHeadbowPurchased = false;
    private boolean             isPinkBowtiePurchased = false;

    private boolean             isOsoWearingShirt = false;
    private boolean             isOsoWearingHat = false;
    private boolean             isOsoWearingHeadbow = false;
    private boolean             isOsoWearingMonocle = false;
    private boolean             isOsoWearingBowtie = false;
    private boolean             isOsoWearingPinkBowtie = false;
    private boolean             isOsoWearingWatch = false;
    private boolean             isOsoWearingMp3 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.closetToolbar);
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_oso_back_icon_pink));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mTabAdapter = new ClosetTabAdapter(getSupportFragmentManager());

        mTabAdapter.AddFragment(new ShirtsClosetTab());
        mTabAdapter.AddFragment(new HatsClosetTab());
        mTabAdapter.AddFragment(new EyeWearClosetTab());
        mTabAdapter.AddFragment(new NeckWearClosetTab());
        mTabAdapter.AddFragment(new WristWearClosetTab());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(mTabAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.closet_tab_layout);

        tabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabAdapter.getCount(); i++) {

            switch (i) {

                case 0:
                    tabLayout.getTabAt(i).setIcon(R.drawable.ic_tab_shirt_vector).select();
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

        SharedPreferences prefs = SharedPrefUtils.getAppState(getApplicationContext());

        //isMp3Purchased = prefs.getBoolean(SharedPrefUtils.MP3_BUY_BOOL, false);
        //isHeadbowPurchased = prefs.getBoolean(SharedPrefUtils.HEADBOW_BUY_BOOL, false);
        //isPinkBowtiePurchased = prefs.getBoolean(SharedPrefUtils.PINK_BOWTIE_BUY_BOOL, false);
        isOsoWearingShirt = prefs.getBoolean(SharedPrefUtils.SHIRT_WEAR_BOOL, false);
        isOsoWearingHat = prefs.getBoolean(SharedPrefUtils.HAT_WEAR_BOOL, false);
        isOsoWearingHeadbow = prefs.getBoolean(SharedPrefUtils.HEADBOW_WEAR_BOOL, false);
        isOsoWearingMonocle = prefs.getBoolean(SharedPrefUtils.MONOCLE_WEAR_BOOL, false);
        isOsoWearingBowtie = prefs.getBoolean(SharedPrefUtils.BOWTIE_WEAR_BOOL, false);
        isOsoWearingPinkBowtie = prefs.getBoolean(SharedPrefUtils.PINK_BOWTIE_WEAR_BOOL, false);
        isOsoWearingWatch = prefs.getBoolean(SharedPrefUtils.WATCH_WEAR_BOOL, false);
        isOsoWearingMp3 = prefs.getBoolean(SharedPrefUtils.MP3_WEAR_BOOL, false);

        setState();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = SharedPrefUtils.getAppState(getApplicationContext());

        //isMp3Purchased = prefs.getBoolean(SharedPrefUtils.MP3_BUY_BOOL, false);
        //isHeadbowPurchased = prefs.getBoolean(SharedPrefUtils.HEADBOW_BUY_BOOL, false);
        //isPinkBowtiePurchased = prefs.getBoolean(SharedPrefUtils.PINK_BOWTIE_BUY_BOOL, false);
        isOsoWearingShirt = prefs.getBoolean(SharedPrefUtils.SHIRT_WEAR_BOOL, false);
        isOsoWearingHat = prefs.getBoolean(SharedPrefUtils.HAT_WEAR_BOOL, false);
        isOsoWearingHeadbow = prefs.getBoolean(SharedPrefUtils.HEADBOW_WEAR_BOOL, false);
        isOsoWearingMonocle = prefs.getBoolean(SharedPrefUtils.MONOCLE_WEAR_BOOL, false);
        isOsoWearingBowtie = prefs.getBoolean(SharedPrefUtils.BOWTIE_WEAR_BOOL, false);
        isOsoWearingPinkBowtie = prefs.getBoolean(SharedPrefUtils.PINK_BOWTIE_WEAR_BOOL, false);
        isOsoWearingWatch = prefs.getBoolean(SharedPrefUtils.WATCH_WEAR_BOOL, false);
        isOsoWearingMp3 = prefs.getBoolean(SharedPrefUtils.MP3_WEAR_BOOL, false);

    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPrefUtils.putClosetState(getApplicationContext(),
                isOsoWearingShirt,
                isOsoWearingHat,
                isOsoWearingHeadbow,
                isOsoWearingMonocle,
                isOsoWearingBowtie,
                isOsoWearingPinkBowtie,
                isOsoWearingWatch,
                isOsoWearingMp3 );


    }

    public void storeBtnOnClick(View view) {
        Intent intent = new Intent(getApplicationContext(), MarketActivity.class);
        startActivity(intent);
    }

    public void tabClosetMonocleClick(View view) {

        if(findViewById(R.id.oso_closet_monocle).getVisibility() == View.INVISIBLE) {

            isOsoWearingMonocle = true;

            findViewById(R.id.closet_monocle).setAlpha(0.5f);

            findViewById(R.id.oso_closet_monocle).setVisibility(View.VISIBLE);

        } else {

            isOsoWearingMonocle = false;

            findViewById(R.id.closet_monocle).setAlpha(1);

            findViewById(R.id.oso_closet_monocle).setVisibility(View.INVISIBLE);

        }

    }

    public void tabClosetHatClick(View view) {

        if(findViewById(R.id.oso_closet_hat).getVisibility() == View.INVISIBLE) {

            if(isOsoWearingHeadbow) {
                isOsoWearingHeadbow = false;

                findViewById(R.id.closet_headbow).setAlpha(1);

                findViewById(R.id.oso_closet_headbow).setVisibility(View.INVISIBLE);
            }

            isOsoWearingHat = true;

            findViewById(R.id.closet_hat).setAlpha(0.5f);

            findViewById(R.id.oso_closet_hat).setVisibility(View.VISIBLE);

        } else {

            isOsoWearingHat = false;

            findViewById(R.id.closet_hat).setAlpha(1);

            findViewById(R.id.oso_closet_hat).setVisibility(View.INVISIBLE);

        }

    }

    public void tabClosetHeadbowClick(View view) {

        if(findViewById(R.id.oso_closet_headbow).getVisibility() == View.INVISIBLE) {

            isOsoWearingHeadbow = true;

            if(isOsoWearingHat) {
                isOsoWearingHat = false;

                findViewById(R.id.closet_hat).setAlpha(1);

                findViewById(R.id.oso_closet_hat).setVisibility(View.INVISIBLE);
            }

            findViewById(R.id.closet_headbow).setAlpha(0.5f);

            findViewById(R.id.oso_closet_headbow).setVisibility(View.VISIBLE);

        } else {

            isOsoWearingHeadbow = false;

            findViewById(R.id.closet_headbow).setAlpha(1);

            findViewById(R.id.oso_closet_headbow).setVisibility(View.INVISIBLE);

        }

    }

    public void tabClosetShirtClick(View view) {

        if(!isOsoWearingShirt){



            ImageView mImg = (ImageView) findViewById(R.id.oso_closet_body);

            mImg.setImageResource(R.drawable.ic_oso_closet_shirt);

            findViewById(R.id.closet_shirt).setAlpha(0.5f);

            isOsoWearingShirt = true;

        } else {

            ImageView mImg = (ImageView) findViewById(R.id.oso_closet_body);

            mImg.setImageResource(R.drawable.ic_oso_closet_body);

            findViewById(R.id.closet_shirt).setAlpha(1);

            isOsoWearingShirt = false;

        }

    }

    public void tabClosetBowtieClick(View view) {

        if(findViewById(R.id.oso_closet_bowtie).getVisibility() == View.INVISIBLE) {

            if(isOsoWearingPinkBowtie) {
                isOsoWearingPinkBowtie = false;

                findViewById(R.id.closet_pink_bowtie).setAlpha(1);

                findViewById(R.id.oso_closet_pink_bowtie).setVisibility(View.INVISIBLE);
            }

            isOsoWearingBowtie = true;

            findViewById(R.id.closet_bowtie).setAlpha(0.5f);

            findViewById(R.id.oso_closet_bowtie).setVisibility(View.VISIBLE);

        } else {

            isOsoWearingBowtie = false;

            findViewById(R.id.closet_bowtie).setAlpha(1);

            findViewById(R.id.oso_closet_bowtie).setVisibility(View.INVISIBLE);

        }

    }

    public void tabClosetPinkBowtieClick(View view) {

        if(findViewById(R.id.oso_closet_pink_bowtie).getVisibility() == View.INVISIBLE) {

            if(isOsoWearingBowtie) {
                isOsoWearingBowtie = false;

                findViewById(R.id.closet_bowtie).setAlpha(1);

                findViewById(R.id.oso_closet_bowtie).setVisibility(View.INVISIBLE);
            }

            isOsoWearingPinkBowtie = true;

            findViewById(R.id.closet_pink_bowtie).setAlpha(0.5f);

            findViewById(R.id.oso_closet_pink_bowtie).setVisibility(View.VISIBLE);

        } else {

            isOsoWearingPinkBowtie = false;

            findViewById(R.id.closet_pink_bowtie).setAlpha(1);

            findViewById(R.id.oso_closet_pink_bowtie).setVisibility(View.INVISIBLE);

        }

    }

    public void tabClosetWatchClick(View view) {

        if(findViewById(R.id.oso_closet_watch).getVisibility() == View.INVISIBLE) {

            isOsoWearingWatch = true;

            findViewById(R.id.closet_watch).setAlpha(0.5f);

            findViewById(R.id.oso_closet_watch).setVisibility(View.VISIBLE);

        } else {

            isOsoWearingWatch = false;

            findViewById(R.id.closet_watch).setAlpha(1);

            findViewById(R.id.oso_closet_watch).setVisibility(View.INVISIBLE);

        }

    }

    public void tabClosetMp3Click(View view) {

        if(findViewById(R.id.oso_closet_mp3).getVisibility() == View.INVISIBLE) {

            isOsoWearingMp3 = true;

            findViewById(R.id.closet_mp3).setAlpha(0.5f);

            findViewById(R.id.oso_closet_mp3).setVisibility(View.VISIBLE);

        } else {

            isOsoWearingMp3 = false;

            findViewById(R.id.closet_mp3).setAlpha(1);

            findViewById(R.id.oso_closet_mp3).setVisibility(View.INVISIBLE);

        }

    }


    //TODO move code that infers items that are created inside tab fragments into their respective fragments. See other TODOs
    public void setState() {

//            if(isMp3Purchased){
//                ImageView mp3 = findViewById(R.id.closet_mp3);
//                mp3.setClickable(true);
//                mp3.setVisibility(View.VISIBLE);
//            }
//            if(isHeadbowPurchased) {
//                ImageView bow = findViewById(R.id.closet_headbow);
//                bow.setClickable(true);
//                bow.setVisibility(View.VISIBLE);
//            }
//            if(isPinkBowtiePurchased){
//                ImageView pBowtie = findViewById(R.id.closet_pink_bowtie);
//                pBowtie.setClickable(true);
//                pBowtie.setVisibility(View.VISIBLE);
//            }

            if(isOsoWearingShirt){
                ImageView mImg = (ImageView) findViewById(R.id.oso_closet_body);
                mImg.setImageResource(R.drawable.ic_oso_closet_shirt);
                //findViewById(R.id.closet_shirt).setAlpha(0.5f);
            } else {
                ImageView mImg = (ImageView) findViewById(R.id.oso_closet_body);
                mImg.setImageResource(R.drawable.ic_oso_closet_body);
                //findViewById(R.id.closet_shirt).setAlpha(1);
            }

            if(isOsoWearingHat){
                //findViewById(R.id.closet_hat).setAlpha(0.5f);
                findViewById(R.id.oso_closet_hat).setVisibility(View.VISIBLE);
            } else {
                //findViewById(R.id.closet_hat).setAlpha(1);
                findViewById(R.id.oso_closet_hat).setVisibility(View.INVISIBLE);
            }

            if(isOsoWearingHeadbow){
                //findViewById(R.id.closet_headbow).setAlpha(0.5f);
                findViewById(R.id.oso_closet_headbow).setVisibility(View.VISIBLE);
            } else {
                //findViewById(R.id.closet_headbow).setAlpha(1);
                findViewById(R.id.oso_closet_headbow).setVisibility(View.INVISIBLE);
            }

            if(isOsoWearingMonocle){
                //findViewById(R.id.closet_monocle).setAlpha(0.5f);
                findViewById(R.id.oso_closet_monocle).setVisibility(View.VISIBLE);
            } else {
                //findViewById(R.id.closet_monocle).setAlpha(1);
                findViewById(R.id.oso_closet_monocle).setVisibility(View.INVISIBLE);
            }

            if(isOsoWearingBowtie) {
                if (findViewById(R.id.oso_closet_pink_bowtie).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.oso_closet_pink_bowtie).setVisibility(View.INVISIBLE);
                }
                //findViewById(R.id.closet_bowtie).setAlpha(0.5f);
                findViewById(R.id.oso_closet_bowtie).setVisibility(View.VISIBLE);
            } else {
                //findViewById(R.id.closet_bowtie).setAlpha(1);
                findViewById(R.id.oso_closet_bowtie).setVisibility(View.INVISIBLE);
            }

            if(isOsoWearingPinkBowtie) {
                if (findViewById(R.id.oso_closet_bowtie).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.oso_closet_bowtie).setVisibility(View.INVISIBLE);
                }
                //findViewById(R.id.closet_bowtie).setAlpha(0.5f);
                findViewById(R.id.oso_closet_bowtie).setVisibility(View.VISIBLE);
            } else {
                //findViewById(R.id.closet_bowtie).setAlpha(1);
                findViewById(R.id.oso_closet_bowtie).setVisibility(View.INVISIBLE);
            }

            if(isOsoWearingWatch) {
                //findViewById(R.id.closet_watch).setAlpha(0.5f);
                findViewById(R.id.oso_closet_watch).setVisibility(View.VISIBLE);
            } else {
                //findViewById(R.id.closet_watch).setAlpha(1);
                findViewById(R.id.oso_closet_watch).setVisibility(View.INVISIBLE);
            }

            if(isOsoWearingMp3) {
                //findViewById(R.id.closet_mp3).setAlpha(0.5f);
                findViewById(R.id.oso_closet_mp3).setVisibility(View.VISIBLE);
            } else {
                //findViewById(R.id.closet_mp3).setAlpha(1);
                findViewById(R.id.oso_closet_mp3).setVisibility(View.INVISIBLE);
            }

    }




}
