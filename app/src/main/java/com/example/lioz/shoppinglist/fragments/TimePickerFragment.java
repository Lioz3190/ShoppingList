package com.example.lioz.shoppinglist.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.lioz.shoppinglist.R;

import java.util.Calendar;

/**
 * Created by VALENTIN on 10/11/2016.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {


    onTimePass timePasser;

    public interface onTimePass {
        public void onTimePass(String data);
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        timePasser = (onTimePass) a;
    }

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
        TextView tv1=(TextView) getActivity().findViewById(R.id.timeTextView);
        tv1.setText(view.getCurrentHour()+"h : "+view.getCurrentMinute() + "min");
        timePasser.onTimePass(hourOfDay+"/"+minute);
    }
}