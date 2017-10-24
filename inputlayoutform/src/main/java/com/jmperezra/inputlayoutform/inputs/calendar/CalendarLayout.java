package com.jmperezra.inputlayoutform.inputs.calendar;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import com.jmperezra.inputlayoutform.inputs.PickerInputLayout;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarLayout extends PickerInputLayout {

    private DefaultDatePicker datePicker = new DefaultDatePicker();

    private Locale locale = Locale.getDefault();

    private DateFormat dateFormat;

    private CalendarListener listener;

    private Date dateSelected;

    private Date minDate;

    private Date maxDate;

    public interface CalendarListener{
        void onDateSelected(Date date);
    }

    public CalendarLayout(Context context) {
        this(context, null);
    }

    public CalendarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    private void setup(){
        setuptDatePicker();
    }

    public <T extends DefaultDatePicker> void setDatePicker(T datePicker) {
        this.datePicker = datePicker;
        setuptDatePicker();
    }

    public DefaultDatePicker getDatePicker() {
        return datePicker;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setListener(CalendarListener listener) {
        this.listener = listener;
        setuptDatePicker();
    }

    private void setuptDatePicker(){
        datePicker.setListener(new DefaultDatePicker.DatePickerListener() {
            @Override
            public void dateSelected(Date date) {
                dateSelected = date;
                setInputText(getDateTextFromLocale(date));
                if (listener != null){
                    listener.onDateSelected(date);
                }
            }
        });
    }

    private String getDateTextFromLocale(Date date){
        if (dateFormat == null) {
            dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        }
        return dateFormat.format(date);
    }

    private Date getDateFromString(String textDate){
        try {
            if (dateFormat == null) {
                dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            }
            return dateFormat.parse(textDate);
        }catch (Exception ex){
            return null;
        }
    }

    public Date getDateSelected(){
        return dateSelected;
    }

    public void setInputDate(Date date) {
        this.dateSelected = date;
        setInputText(getDateTextFromLocale(date));
    }

    public void setMinDate(Date date){
        this.minDate = date;
    }

    public void setMaxDate(Date date){
        this.maxDate = date;
    }

    @Override
    public void pickerClicked() {
        setDatePickerValues();
        datePicker.show(((Activity)getContext()).getFragmentManager(), "datepicker");
    }

    private void setDatePickerValues(){
        datePicker.setStartDate(dateSelected);
        datePicker.setMinDate(minDate);
        datePicker.setMaxDate(maxDate);
    }
}
