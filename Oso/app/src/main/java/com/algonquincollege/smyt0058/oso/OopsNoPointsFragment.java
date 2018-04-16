package com.algonquincollege.smyt0058.oso;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Jason on 2018-04-12.
 */

public class OopsNoPointsFragment extends DialogFragment {

    private static final String  OOPS_NO_POINTS_TAG = "no points dialog";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout

        final View view = inflater.inflate(R.layout.no_points_dialog_layout, null);

        final ImageView mDonut = (ImageView) view.findViewById(R.id.donutBtn);

        builder.setCancelable(false);
        builder.setIcon(R.drawable.places_ic_search);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        dialog.cancel();

                    }
                });

        return builder.create();
    }

}
