package com.algonquincollege.smyt0058.oso.feedingFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.algonquincollege.smyt0058.oso.R;

/**
 * Created by Jason on 2018-04-10.
 */

public class OsoHotChocolateFeedingFragment extends android.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout
        builder.setCancelable(false);
        builder.setIcon(R.drawable.places_ic_search);
        builder.setView(inflater.inflate(R.layout.oso_feeding_dialog, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }
                });
        return builder.create();
    }

}
