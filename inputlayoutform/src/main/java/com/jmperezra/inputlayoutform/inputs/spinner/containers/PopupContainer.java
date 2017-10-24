package com.jmperezra.inputlayoutform.inputs.spinner.containers;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListPopupWindow;

import com.jmperezra.inputlayoutform.inputs.spinner.SpinnerAdapterLayout;

public class PopupContainer extends ListPopupWindow implements Container {

    public PopupContainer(@NonNull Context context) {
        super(context);
        setup();
    }

    private void setup(){
        setWidth(android.support.v7.widget.ListPopupWindow.WRAP_CONTENT);
        setHeight(android.support.v7.widget.ListPopupWindow.WRAP_CONTENT);
        setModal(true);
    }

    @Override
    public void setContainerModal(boolean modal) {
        setModal(modal);
    }

    @Override
    public void setSpinnerAdapterLayout(SpinnerAdapterLayout adapterLayout) {
        setAdapter(adapterLayout);
    }

    @Override
    public void setContainerView(View containerView) {
        setAnchorView(containerView);
    }
}
