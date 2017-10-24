package com.jmperezra.inputlayoutform.inputs.spinner;


public class DefaultItemSpinnerLayout implements ItemSpinnerLayout {

    public final static int id = -1;
    private final String label;

    public static DefaultItemSpinnerLayout createInstance(String label){
        return new DefaultItemSpinnerLayout(label);
    }

    public DefaultItemSpinnerLayout(String label) {
        this.label = label;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
