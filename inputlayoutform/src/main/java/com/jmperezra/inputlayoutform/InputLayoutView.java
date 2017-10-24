package com.jmperezra.inputlayoutform;


public interface InputLayoutView {

    void setupView();
    void inflateView();
    void obtainValues();

    void showInfoText();
    void hideInfoText();

    void showLabelText();
    void hideLabelText();
    void setInputHint(CharSequence hint);

    void setupFocusEvent();
    void addInputLayout();

    void setupTextChangedEvent();

    abstract void buildInputLayout();
}
