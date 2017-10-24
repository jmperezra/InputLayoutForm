package com.jmperezra.inputlayoutform.inputs.calendar;


import java.util.Date;

public interface DefaultDatePickerView {

    void createDatePicker(int year, int month, int day);
    void setMinDateCalendar(long minDate);
    void setMaxDateCalendar(long maxDate);
    void closeDialogWithDate(Date dateSelected);
}
