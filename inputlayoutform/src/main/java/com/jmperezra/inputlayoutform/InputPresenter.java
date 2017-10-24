package com.jmperezra.inputlayoutform;


public class InputPresenter {

    private final InputLayoutView view;

    private CharSequence label;
    private CharSequence info;
    private CharSequence inputText;

    public static InputPresenter createInstance(InputLayoutView view){
        return new InputPresenter(view);
    }

    private InputPresenter(InputLayoutView view) {
        this.view = view;
        viewAttached();
    }

    private void viewAttached(){
        view.setupView();
        view.inflateView();
        view.obtainValues();
        view.hideLabelText();
        view.buildInputLayout();
        view.addInputLayout();
        view.setupFocusEvent();
        view.setupTextChangedEvent();
    }

    public void setLabel(CharSequence label) {
        this.label = label;
        view.setInputHint(label);
    }

    public void setInfo(CharSequence info) {
        this.info = info;
    }

    public void setInputText(CharSequence text){
        this.inputText = text;
        changeVisibilityLabel();
    }

    public void changeFocus(boolean hasFocus){
        if (hasFocus){
            setFocus();
        }else{
            setLostFocus();
        }
    }

    private void setFocus(){
        changeVisibilityLabel();
        view.showInfoText();
    }

    private void changeVisibilityLabel(){
        if (isEmptyInputText()){
            view.hideLabelText();
        }else {
            view.showLabelText();
        }
    }

    private boolean isEmptyInputText(){
        return inputText == null || inputText.toString().isEmpty();
    }

    private void setLostFocus(){
        changeVisibilityLabel();
        view.hideInfoText();
    }

}
