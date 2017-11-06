package com.jmperezra.inputlayoutform;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class InputView<T extends TextView> extends LinearLayout implements InputLayoutView{

    private InputPresenter presenter;

    private AttributeSet attrs;

    private ViewGroup layoutInflated;
    protected ViewGroup viewWrapperInput;

    private AppCompatTextView viewLabel;
    private AppCompatTextView viewInfo;

    protected abstract void attachInputLayout();
    protected abstract T getViewInput();

    private InputListener inputListener;

    public interface InputListener{
        void onFocusChanged(boolean hasFocus);
    }

    public InputView(Context context) {
        this(context, null);
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        init();
    }

    private void init(){
        setupView();
        initPresenter();
        setAttrsValues();
    }

    private void setupView(){
        setOrientation(VERTICAL);
        inflateView();
        findViews();
        attachInputLayout();
    }

    private void inflateView(){
        this.layoutInflated = (ViewGroup) inflate(getContext(), R.layout.view_input_layout_form_input_layout, this);
    }

    private void findViews(){
        viewLabel = layoutInflated.findViewById(R.id.viewLabel);
        viewWrapperInput = layoutInflated.findViewById(R.id.wrapperInput);
        viewInfo = layoutInflated.findViewById(R.id.viewInfo);
    }

    private void setAttrsValues(){
        if (attrs != null){
            TypedArray typedArray = getTypedArray(R.styleable.InputLayoutFormStyleable);
            try {
                setLabelFromAttrs(typedArray);
                setInputFromAttrs(typedArray);
                setInfoFromAttrs(typedArray);
            }finally {
                if (typedArray != null){
                    typedArray.recycle();
                }
            }

        }
    }

    protected TypedArray getTypedArray(@StyleableRes int[] styleableId) {
        return getContext().getTheme().obtainStyledAttributes(attrs, styleableId, 0, 0);
    }

    private void setLabelFromAttrs(TypedArray typedArray){
        setLabelTextFromAttrs(typedArray);
        setLabelTextColorFromAttrs(typedArray);
        setLabelTextSizeFromAttrs(typedArray);
    }

    private void setLabelTextFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputLabelText)){
            setLabelText(typedArray.getText(R.styleable.InputLayoutFormStyleable_inputLabelText));
        }
    }

    private void setLabelTextColorFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputLabelTextColor)){
            setLabelTextColor(typedArray.getColor(R.styleable.InputLayoutFormStyleable_inputLabelTextColor, Color.GRAY));
        }
    }

    private void setLabelTextSizeFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputLabelTextSize)){
            setLabelTextSize(typedArray.getDimension(R.styleable.InputLayoutFormStyleable_inputLabelTextSize, 12));
        }
    }

    private void setInputFromAttrs(TypedArray typedArray){
        setInputTextFromAttrs(typedArray);
        setInputTextColorFromAttrs(typedArray);
        setInputTextSizeFromAttrs(typedArray);
    }

    private void setInputTextFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputLayoutText)){
            setInputText(typedArray.getText(R.styleable.InputLayoutFormStyleable_inputLayoutText));
            setFocusAtEndOfText();
        }
    }

    private void setInputTextColorFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputLayoutTextColor)){
            setInputTextColor(typedArray.getColor(R.styleable.InputLayoutFormStyleable_inputLayoutTextColor, Color.BLACK));
        }
    }

    private void setInputTextSizeFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputLayoutTextSize)){
            setInputTextSize(typedArray.getDimension(R.styleable.InputLayoutFormStyleable_inputLayoutTextSize,  14));
        }
    }

    private void setFocusAtEndOfText(){
        int len = getViewInput().getText().length();
        if (getViewInput() instanceof EditText) {
            ((EditText)getViewInput()).setSelection(len, len);
        }
    }

    private void setInfoFromAttrs(TypedArray typedArray){
        setInfoTextFromAttrs(typedArray);
        setInfoTextColorFromAttrs(typedArray);
        setInfoTextSizeFromAttrs(typedArray);
    }

    private void setInfoTextFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputInfoText)){
            setInfoText(typedArray.getText(R.styleable.InputLayoutFormStyleable_inputInfoText));
        }
    }

    private void setInfoTextColorFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputInfoTextColor)){
            setInfoTextColor(typedArray.getColor(R.styleable.InputLayoutFormStyleable_inputInfoTextColor, Color.GRAY));
        }
    }

    private void setInfoTextSizeFromAttrs(TypedArray typedArray){
        if (typedArray.hasValue(R.styleable.InputLayoutFormStyleable_inputInfoTextSize)){
            setInfoTextSize(typedArray.getDimension(R.styleable.InputLayoutFormStyleable_inputInfoTextSize, 12));
        }
    }

    private void initPresenter(){
        presenter = InputPresenter.createInstance(this);
        presenter.setLabel(viewLabel.getText());
        presenter.setInfo(viewInfo.getText());
    }

    @Override
    public void updateValues() {
        viewLabel.setText(viewLabel.getText());
        viewInfo.setText(viewInfo.getText());
    }

    public void setLabelText(CharSequence text){
        viewLabel.setText(text);
        presenter.setLabel(text);
    }

    public void setLabelTextColor(@ColorInt int color){
        viewLabel.setTextColor(color);
    }

    public void setLabelTextSize(float textSize){
        viewLabel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setInfoText(CharSequence text){
        viewInfo.setText(text);
        presenter.setInfo(text);
    }

    public void setInfoTextColor(@ColorInt int color){
        viewInfo.setTextColor(color);
    }

    public void setInfoTextSize(float textSize){
        viewInfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setIconRight(@DrawableRes int drawable){
        getViewInput().setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
    }

    public void setIconLeft(@DrawableRes int drawable){
        getViewInput().setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
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
        getViewInput().setHint(hint);
    }

    public void setInputText(CharSequence text){
        getViewInput().setText(text);
        presenter.setInputText(text);
    }

    public void setInputTextSize(float textSize){
        getViewInput().setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setInputTextColor(@ColorInt int color){
        getViewInput().setTextColor(color);
    }

    @Override
    public void setupFocusEvent(){
        getViewInput().setOnFocusChangeListener(this.getFocusChangeListener());
    }

    private OnFocusChangeListener getFocusChangeListener(){
        return new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                presenter.changeFocus(hasFocus);
                if (inputListener != null){
                    inputListener.onFocusChanged(hasFocus);
                }
            }
        };
    }

    @Override
    public void setupTextChangedEvent(){
        getViewInput().addTextChangedListener(getTextWatcher());
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

    public String getInputLabel(){
        return viewLabel.getText().toString();
    }

    public abstract CharSequence getText();

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }
}
