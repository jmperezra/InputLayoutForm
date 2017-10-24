package com.jmperezra.inputlayoutform.inputs.spinner.containers;


import android.view.View;

import com.jmperezra.inputlayoutform.inputs.spinner.SpinnerAdapterLayout;

public interface Container {

    void setContainerModal(boolean modal);

    void setSpinnerAdapterLayout(SpinnerAdapterLayout adapterLayout);

    void setContainerView(View containerView);
}
