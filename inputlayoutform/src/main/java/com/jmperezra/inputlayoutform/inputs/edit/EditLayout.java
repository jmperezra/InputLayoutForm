package com.jmperezra.inputlayoutform.inputs.edit;


import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.jmperezra.inputlayoutform.InputView;
import com.jmperezra.inputlayoutform.R;


public class EditLayout extends InputView<AppCompatEditText> {

    private AppCompatEditText editText;

    public EditLayout(Context context) {
        this(context, null);
    }

    public EditLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public AppCompatEditText createInputLayout() {
        editText = (AppCompatEditText) inflate(getContext(), R.layout.view_input_edit, null);
        return editText;
    }

    @Override
    public void setInputHint(CharSequence hint) {
        editText.setHint(hint);
    }
}
