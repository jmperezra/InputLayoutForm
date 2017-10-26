package com.jmperezra.inputlayoutform.inputs;


import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.jmperezra.inputlayoutform.InputView;
import com.jmperezra.inputlayoutform.R;


public abstract class PickerInputLayout extends InputView<AppCompatTextView> {

    protected AppCompatTextView textView;

    public PickerInputLayout(Context context) {
        this(context, null);
    }

    public PickerInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    private void setup(){
        setClickable(true);
        setFocusableInTouchMode(true);
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus){
                    textView.setFocusableInTouchMode(true);
                    textView.requestFocus();
                    pickerClicked();
                }else {
                    textView.setFocusableInTouchMode(false);
                }
            }
        });
    }

    @Override
    public void attachInputLayout() {
        ViewGroup viewGroup = (ViewGroup) inflate(getContext(), R.layout.view_input_layout_form_input_picker, viewWrapperInput);
        textView = (AppCompatTextView) viewGroup.getChildAt(0);
    }

    @Override
    protected AppCompatTextView getViewInput() {
        return textView;
    }

    public abstract void pickerClicked();
}
