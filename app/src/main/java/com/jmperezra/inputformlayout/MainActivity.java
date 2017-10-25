package com.jmperezra.inputformlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jmperezra.inputlayoutform.inputs.calendar.CalendarLayout;
import com.jmperezra.inputlayoutform.inputs.edit.EditLayout;
import com.jmperezra.inputlayoutform.inputs.spinner.ItemSpinnerLayout;
import com.jmperezra.inputlayoutform.inputs.spinner.SpinnerLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditLayout inputView;

    SpinnerLayout pickerInputLayout;

    CalendarLayout calendarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup(){
        inputView = findViewById(R.id.input);
        //inputView.setLabelText(getString(R.string.app_name));
        //inputView.setLabelTextColor(R.color.colorAccent);
        //inputView.setInfoText(getString(R.string.app_name));
        //inputView.setInfoTextColor(R.color.colorPrimary);

        pickerInputLayout = findViewById(R.id.pickerLayout);
        //pickerInputLayout.setLabelText(getString(R.string.app_name), true);
        //pickerInputLayout.setLabelTextColor(R.color.colorAccent);
        //pickerInputLayout.setInfoText(getString(R.string.app_name));
        //pickerInputLayout.setInfoTextColor(R.color.colorPrimary);

        final List<ItemSpinnerLayout> items = new ArrayList<>();
        items.add(new AnimalsItemRow(1, "Tux"));
        items.add(new AnimalsItemRow(2, "Dino"));
        items.add(new AnimalsItemRow(3, "Trully"));
        items.add(new AnimalsItemRow(4, "Niebla"));
        items.add(new AnimalsItemRow(5, "Nora"));

        pickerInputLayout.setItems(items);
        //pickerInputLayout.setItemPositionSelected(0);
        pickerInputLayout.setItemSelected(items.get(2));
        pickerInputLayout.setListener(new SpinnerLayout.SpinnerLayoutListener() {
            @Override
            public void onItemSelected(ItemSpinnerLayout itemSpinnerLayout) {
                Log.d("@dev", "Item Seleccionado: " + itemSpinnerLayout.getLabel());
            }
        });

        calendarLayout = findViewById(R.id.calendarLayout);
        calendarLayout.setLabelText("Seleccione una fecha");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 9, 16);

        calendarLayout.setInputDate(calendar.getTime());
        calendarLayout.setListener(new CalendarLayout.CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                Log.d("@dev", "Fecha Seleccionada" + date);
                Log.d("@dev", "Fecha Seleccionada2" + calendarLayout.getDateSelected());
            }
        });
    }
}
