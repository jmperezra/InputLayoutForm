package com.jmperezra.inputlayoutform.inputs.calendar;


import java.util.Calendar;
import java.util.Date;

public class DefaultDatePickerPresenter {

    private DefaultDatePickerView view;

    private Date date;
    private Date maxDate;
    private Date minDate;

    public DefaultDatePickerPresenter(DefaultDatePickerView view) {
        this.view = view;
    }

    public void setStartDate(Date date) {
        this.date = date;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public void onCreateDialog(){
        startDateCalendar();
        setMaxDateCalendar();
        setMinDateCalendar();
    }

    private void startDateCalendar(){
        if (date == null){
            initWithToday();
        }else {
            initWithStartDate();
        }
    }

    private void initWithToday(){
        createDatePickerWithInitDate(new Date());
    }

    private void initWithStartDate(){
        createDatePickerWithInitDate(this.date);
    }

    private void createDatePickerWithInitDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        view.createDatePicker(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setMaxDateCalendar(){
        if (maxDate != null){
            view.setMaxDateCalendar(maxDate.getTime());
        }
    }

    private void setMinDateCalendar(){
        if (minDate != null){
            view.setMinDateCalendar(minDate.getTime());
        }
    }

    public void dateSelected(int year, int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        view.closeDialogWithDate(calendar.getTime());
    }
}
