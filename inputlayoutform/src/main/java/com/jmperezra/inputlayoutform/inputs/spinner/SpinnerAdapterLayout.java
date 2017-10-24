package com.jmperezra.inputlayoutform.inputs.spinner;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jmperezra.inputformlayout.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapterLayout extends BaseAdapter {

    private Context context;
    private List<ItemSpinnerLayout> items;
    private int positionSelected = -1;

    public SpinnerAdapterLayout(Context context){
        this.context = context;
        items = new ArrayList<>();
    }

    public void setItems(List<ItemSpinnerLayout> items){
        this.items = items;
    }

    public void addItems(List<ItemSpinnerLayout> items){
        this.items.addAll(items);
    }

    public void addItem(int position, ItemSpinnerLayout item){
        items.add(position, item);
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ItemSpinnerLayout getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    public int getPosition(ItemSpinnerLayout itemSpinnerLayout){
        if (items != null){
            for(int i = 0; i < items.size(); i++){
                if (items.get(i).getId() == itemSpinnerLayout.getId()){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSpinnerLayout rowItem = getItem(position);
        View rootView = inflateView();
        if (position == positionSelected){
            rootView.setSelected(true);
        }
        rootView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_key));
        setLabel(rootView, rowItem);
        return rootView;
    }

    public void setPositionSelected(int positionSelected){
        this.positionSelected = positionSelected;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private void setLabel(View rootView, ItemSpinnerLayout rowItem){
        TextView txtTitle = rootView.findViewById(R.id.viewItem);
        txtTitle.setText(rowItem.getLabel());
    }

    private View inflateView(){
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.view_spinner_item, null, false);
    }
}