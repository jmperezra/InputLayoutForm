package com.jmperezra.inputlayoutform.inputs.calendar;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Date;

public class DefaultDatePicker extends DialogFragment
        implements DefaultDatePickerView, DatePickerDialog.OnDateSetListener {

    private DefaultDatePickerPresenter presenter
            = new DefaultDatePickerPresenter(this);

    private DatePickerListener listener;
    private DatePickerDialog datePickerDialog;

    public interface DatePickerListener{
        void dateSelected(Date date);
    }

    public void setStartDate(Date date) {
        presenter.setStartDate(date);
    }

    public void setMaxDate(Date date){
        presenter.setMaxDate(date);
    }

    public void setMinDate(Date date){
        presenter.setMinDate(date);
    }

    public void setListener(DatePickerListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        presenter.onCreateDialog();
        return datePickerDialog;
    }

    public void createDatePicker(int year, int month, int day){
        datePickerDialog = new DatePickerDialog(
                getActivity(),
                this,
                year,
                month,
                day);
    }

    @Override
    public void setMaxDateCalendar(long maxDate){
        datePickerDialog.getDatePicker().setMaxDate(maxDate);
    }

    @Override
    public void setMinDateCalendar(long minDate){
        datePickerDialog.getDatePicker().setMinDate(minDate);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        presenter.dateSelected(year, month, day);
    }

    @Override
    public void closeDialogWithDate(Date dateSelected){
        if (listener != null){
            listener.dateSelected(dateSelected);
        }
    }
}