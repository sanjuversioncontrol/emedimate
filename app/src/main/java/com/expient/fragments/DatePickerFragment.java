package com.expient.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.expient.activity.R;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by TOSHIBA on 18-Jun-16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        // Create a new instance of DatePickerDialog and return it
        return dialog;


    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

        EditText dob_edt=(EditText)getActivity().findViewById(R.id.input_dob);

        int _day=view.getDayOfMonth();
        int _month=view.getMonth();
        int _year=view.getYear();
        _month=_month+1;

        dob_edt.setText(_day+" / "+_month+" / "+_year);

    }
}