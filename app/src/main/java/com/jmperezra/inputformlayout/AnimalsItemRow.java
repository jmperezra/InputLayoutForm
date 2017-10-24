package com.jmperezra.inputformlayout;


import com.jmperezra.inputlayoutform.inputs.spinner.ItemSpinnerLayout;

public class AnimalsItemRow implements ItemSpinnerLayout {

    int id;
    String name;

    public AnimalsItemRow(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return name;
    }
}
