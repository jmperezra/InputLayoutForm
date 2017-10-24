package com.jmperezra.inputlayoutform.inputs.edit;


import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.jmperezra.inputformlayout.R;
import com.jmperezra.inputlayoutform.InputView;

public class EditLayout extends InputView<AppCompatEditText>{

    private AppCompatEditText editText;

    public EditLayout(Context context) {
        this(context, null);
    }

    public EditLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void buildInputLayout() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        editText = (AppCompatEditText) inflater.inflate(R.layout.view_input_edit, null, false);
    }

    @Override
    public AppCompatEditText getInputView() {
        return editText;
    }

    @Override
    public void setInputHint(CharSequence hint) {
        editText.setHint(hint);
    }
}
