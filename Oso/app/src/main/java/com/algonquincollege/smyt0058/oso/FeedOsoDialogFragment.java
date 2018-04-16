package com.algonquincollege.smyt0058.oso;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.algonquincollege.smyt0058.oso.feedingFragments.OsoDonutFeedingFragment;

/**
 * Created by Jason on 2018-03-21.
 */

public class FeedOsoDialogFragment extends DialogFragment {

    public ImageView    mDonut;
    public ImageView    mFish;
    public ImageView    mApple;
    public ImageView    mIceCream;
    public ImageView    mHotChocolate;



    private static final String OSO_EATING_DIALOG_TAG = "Eating Oso Dialog";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout

        final View view = inflater.inflate(R.layout.oso_food_dialog, null);

        final ImageView mDonut = (ImageView) view.findViewById(R.id.donutBtn);
        final ImageView mFish = (ImageView) view.findViewById(R.id.fishBtn);
        final ImageView mApple = (ImageView) view.findViewById(R.id.appleBtn);
        final ImageView mIceCream = (ImageView) view.findViewById(R.id.iceCreamBtn);
        final ImageView mHotChocolate = (ImageView) view.findViewById(R.id.hotChocolateBtn);

        builder.setCancelable(false);
        builder.setIcon(R.drawable.places_ic_search);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        dialog.cancel();

                    }
                });

        mDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DialogFragment newFragment = new OsoDonutFeedingFragment();
                newFragment.show(getFragmentManager(),OSO_EATING_DIALOG_TAG);

                getDialog().cancel();



            }
        });

        mFish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DialogFragment newFragment = new OsoDonutFeedingFragment();
                newFragment.show(getFragmentManager(),OSO_EATING_DIALOG_TAG);

                getDialog().cancel();



            }
        });

        mApple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DialogFragment newFragment = new OsoDonutFeedingFragment();
                newFragment.show(getFragmentManager(),OSO_EATING_DIALOG_TAG);

                getDialog().cancel();



            }
        });

        mIceCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DialogFragment newFragment = new OsoDonutFeedingFragment();
                newFragment.show(getFragmentManager(),OSO_EATING_DIALOG_TAG);

                getDialog().cancel();



            }
        });

        mHotChocolate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DialogFragment newFragment = new OsoDonutFeedingFragment();
                newFragment.show(getFragmentManager(),OSO_EATING_DIALOG_TAG);

                getDialog().cancel();



            }
        });

        return builder.create();
    }

}
