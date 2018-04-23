package com.algonquincollege.smyt0058.oso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Toast;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AhoyOnboarderActivity {

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("", "", R.drawable.ic_welcomeonboarding);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("", "", R.drawable.ic_dressmeuponboarding);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("", "", R.drawable.ic_buymeoutfitsonboarding);

        ahoyOnboarderCard1.setBackgroundColor(R.color.theme1_colorPrimary);
        ahoyOnboarderCard2.setBackgroundColor(R.color.theme1_colorPrimary);
        ahoyOnboarderCard3.setBackgroundColor(R.color.theme1_colorPrimary);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
        }

        setFinishButtonTitle("Get Started");
        showNavigationControls(true);
        setColorBackground(R.color.theme1_colorPrimary);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        //setFont(face);

        setInactiveIndicatorColor(R.color.grey_600);
        setActiveIndicatorColor(R.color.white);
        setOnboardPages(pages);

    }

    @Override
    public void onFinishButtonPressed() {
        startActivity(new Intent(mContext, ChatActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
}
