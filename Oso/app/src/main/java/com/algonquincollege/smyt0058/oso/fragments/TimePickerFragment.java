package com.algonquincollege.smyt0058.oso.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import com.algonquincollege.smyt0058.oso.notifications.Notification;
import com.algonquincollege.smyt0058.oso.util.api.SharedPrefUtils;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        SharedPrefUtils.putSettingsState(getContext(), hourOfDay, minute );
        SharedPreferences prefs = SharedPrefUtils.getAppState(getContext());
        long next = prefs.getLong(SharedPrefUtils.NEXT_QUESTIONNAIRE_DATE, 0);

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(next);
        //long now = c.getTimeInMillis();

        // set hour and minute.
        // adjust to tomorrow if that result is not in the future
        // (by five seconds minute at least)
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        next = c.getTimeInMillis();
        SharedPrefUtils.putNextDateState(getContext(), next);
//        if (c.getTimeInMillis() < now + 5 * 1000) {
//            c.setTimeInMillis(c.getTimeInMillis() + 24 * 60 * 60 * 1000);
//        }

        Notification.init(getContext());
        Toast.makeText(getContext(), "Time Picked: " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
    }

}

