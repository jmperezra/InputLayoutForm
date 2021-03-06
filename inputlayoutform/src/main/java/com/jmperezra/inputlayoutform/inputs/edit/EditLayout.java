package com.jmperezra.inputlayoutform.inputs.edit;


import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.ViewGroup;

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
    public void attachInputLayout() {
        ViewGroup viewGroup = (ViewGroup) inflate(getContext(), R.layout.view_input_layout_form_input_edit, viewWrapperInput);
        editText = (AppCompatEditText) viewGroup.getChildAt(0);
    }

    @Override
    protected AppCompatEditText getViewInput() {
        return editText;
    }

    @Override
    public void setInputHint(CharSequence hint) {
        editText.setHint(hint);
    }

    @Override
    public CharSequence getText(){
        return editText.getText();
    }
}
