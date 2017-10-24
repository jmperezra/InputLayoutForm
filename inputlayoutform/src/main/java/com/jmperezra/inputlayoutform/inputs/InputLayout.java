package com.jmperezra.inputlayoutform.inputs;


import android.support.annotation.DrawableRes;
import android.text.TextWatcher;
import android.view.View;

public interface InputLayout{

    void setIconRight(@DrawableRes int drawable);

    void setIconLeft(@DrawableRes int drawable);

    void setTextChangedListener(TextWatcher textWatcher);

    View getView();

}
