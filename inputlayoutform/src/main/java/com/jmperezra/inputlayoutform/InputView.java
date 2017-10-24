package com.jmperezra.inputlayoutform;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jmperezra.inputformlayout.R;

public abstract class InputView<T extends TextView> extends LinearLayout implements InputLayoutView{

    private InputPresenter presenter;

    private ViewGroup layoutInflated;
    private ViewGroup viewWrapperInput;

    private AppCompatTextView viewLabel;
    private AppCompatTextView viewInfo;
    private TextView viewInput;

    @Override
    public abstract void buildInputLayout();

    public abstract T getInputView();

    public InputView(Context context) {
        this(context, null);
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        presenter = InputPresenter.createInstance(this);
        presenter.setLabel(viewLabel.getText());
        presenter.setInfo(viewInfo.getText());
    }

    public void setupView(){
        setOrientation(VERTICAL);
    }

    public void inflateView(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        this.layoutInflated = (ViewGroup) inflater.inflate(R.layout.view_input_layout, this, true);
        findViews();
    }

    private void findViews(){
        viewLabel = layoutInflated.findViewById(R.id.viewLabel);
        viewWrapperInput = layoutInflated.findViewById(R.id.wrapperInput);
        viewInfo = layoutInflated.findViewById(R.id.viewInfo);
    }

    @Override
    public void obtainValues() {
        viewLabel.setText(viewLabel.getText());
        viewInfo.setText(viewInfo.getText());
    }

    public void setLabelText(CharSequence text){
        viewLabel.setText(text);
        presenter.setLabel(text);
    }

    public void setLabelTextColor(@ColorRes int color){
        viewLabel.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setInfoText(CharSequence text){
        viewInfo.setText(text);
        presenter.setInfo(text);
    }

    public void setInfoTextColor(@ColorRes int color){
        viewInfo.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setIconRight(@DrawableRes int drawable){
        viewInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
    }

    public void setIconLeft(@DrawableRes int drawable){
        viewInput.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
    }

    private void tintIcon(@DrawableRes int icon, @ColorInt int color){
        Drawable drawable = ContextCompat.getDrawable(getContext(), icon);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_OVER);
    }

    @Override
    public void showInfoText() {
        viewInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInfoText() {
        viewInfo.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLabelText() {
        viewLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLabelText() {
        viewLabel.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setInputHint(CharSequence hint){
        viewInput.setHint(hint);
    }

    public void setInputText(CharSequence text){
        viewInput.setText(text);
        presenter.setInputText(text);
    }

    @Override
    public void setupFocusEvent(){
        viewInput.setOnFocusChangeListener(this.getFocusChangeListener());
    }

    private OnFocusChangeListener getFocusChangeListener(){
        return new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                presenter.changeFocus(hasFocus);
            }
        };
    }

    @Override
    public void setupTextChangedEvent(){
        viewInput.addTextChangedListener(getTextWatcher());
    }

    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.setInputText(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
    }

    @Override
    public void addInputLayout(){
        viewInput = getInputView();
        viewWrapperInput.addView(viewInput);
    }

    public String getInputLabel(){
        return viewLabel.getText().toString();
    }

    protected String getStringFromCharSequence(CharSequence charSequence){
        if (charSequence != null){
            return charSequence.toString();
        }else{
            return "";
        }
    }
}
