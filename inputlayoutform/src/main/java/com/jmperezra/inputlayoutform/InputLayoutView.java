package com.jmperezra.inputlayoutform;


public interface InputLayoutView {

    void obtainValues();

    void showInfoText();
    void hideInfoText();

    void showLabelText();
    void hideLabelText();
    void setInputHint(CharSequence hint);

    void setupFocusEvent();

    void setupTextChangedEvent();
}
