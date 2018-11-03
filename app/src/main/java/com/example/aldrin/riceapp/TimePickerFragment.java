package com.example.aldrin.riceapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import android.text.format.DateFormat;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {
    private static final String TAG = TimePickerFragment.class.getName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateDialog: TimePickerFragment");
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(),
                hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}
