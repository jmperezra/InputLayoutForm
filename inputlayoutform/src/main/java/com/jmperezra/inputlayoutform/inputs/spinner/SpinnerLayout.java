package com.jmperezra.inputlayoutform.inputs.spinner;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import com.jmperezra.inputlayoutform.R;
import com.jmperezra.inputlayoutform.inputs.PickerInputLayout;

import java.util.List;

public class SpinnerLayout extends PickerInputLayout {

    private SpinnerAdapterLayout adapterLayout;

    private ListPopupWindow listPopupWindow;

    private SpinnerLayoutListener listener;

    private boolean showLabelInList = false;

    public SpinnerLayout(Context context) {
        this(context, null);
    }

    public SpinnerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public interface SpinnerLayoutListener {
        void onItemSelected(ItemSpinnerLayout itemSpinnerLayout);
        void onItemDefaultSelected();
    }

    public void setListener(SpinnerLayoutListener listener) {
        this.listener = listener;
    }

    private void init() {
        createDefaultAdapter();
        setupPopupWindow();
    }

    private void createDefaultAdapter() {
        adapterLayout = new SpinnerAdapterLayout(getContext());
    }

    private void setupPopupWindow() {
        listPopupWindow = new ListPopupWindow(getContext());
        listPopupWindow.setAdapter(adapterLayout);
        listPopupWindow.setAnchorView(textView);
        listPopupWindow.setWidth(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setModal(true);
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                ItemSpinnerLayout itemSpinnerLayout = adapterLayout.getItem(position);
                view.setSelected(true);
                adapterLayout.setPositionSelected(position);

                if (isDefaultItemSelected(itemSpinnerLayout)) {
                    setInputText(null);
                    if (listener != null){
                        listener.onItemDefaultSelected();
                    }
                } else {
                    setInputText(itemSpinnerLayout.getLabel());
                    if (listener != null){
                        listener.onItemSelected(itemSpinnerLayout);
                    }
                }

                listPopupWindow.dismiss();
            }
        });
    }

    public ListPopupWindow getListPopupWindow() {
        return listPopupWindow;
    }

    private boolean isDefaultItemSelected(ItemSpinnerLayout itemSpinnerLayout) {
        return (showLabelInList && itemSpinnerLayout.getId() == DefaultItemSpinnerLayout.id);
    }

    @Override
    public void pickerClicked() {
        listPopupWindow.show();
        listPopupWindow.getListView().setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bg_key));
    }

    public void setLabelText(CharSequence text, boolean showLabelInList) {
        this.showLabelInList = showLabelInList;
        setLabelText(text);
        addLabelToList();
    }

    public <T extends SpinnerAdapterLayout> void setAdapterLayout(T adapterLayout) {
        this.adapterLayout = adapterLayout;
        setupPopupWindow();
    }

    /**
     * Operaciones Con Adaptador
     **/
    public void setItems(List<ItemSpinnerLayout> items) {
        adapterLayout.setItems(items);
        addLabelToList();
        adapterLayout.refresh();
    }

    public void addItems(List<ItemSpinnerLayout> items) {
        adapterLayout.addItems(items);
        addLabelToList();
        adapterLayout.refresh();
    }

    public void setItemSelected(ItemSpinnerLayout itemSelected) {
        setItemPositionSelected(adapterLayout.getPosition(itemSelected));
    }

    public void setItemPositionSelected(int position) {
        if (position >= 0 && position < adapterLayout.getCount()) {
            if (showLabelInList && position == 0) {
                position = 1;
            }
            setInputText(adapterLayout.getItem(position).getLabel());
        }
    }

    private void addLabelToList() {
        if (showLabelInList) {
            adapterLayout.addItem(0, DefaultItemSpinnerLayout.createInstance(getInputLabel()));
        }
    }
}
