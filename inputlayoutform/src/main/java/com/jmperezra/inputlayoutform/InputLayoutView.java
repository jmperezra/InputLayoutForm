package com.jmperezra.inputlayoutform;


public interface InputLayoutView {

    void updateValues();

    void showInfoText();
    void hideInfoText();

    void showLabelText();
    void hideLabelText();
    void setInputHint(CharSequence hint);

    void setupFocusEvent();

    void setupTextChangedEvent();
}
