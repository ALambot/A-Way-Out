package com.dolphin.awayout;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


public class InventoryAdapt extends ArrayAdapter<MyObject>{


    public InventoryAdapt(@NonNull Context context, ArrayList<MyObject> ImageNames) {
        super(context, R.layout.inventory_slot,ImageNames);
    }

    @Nullable
    @Override
    public MyObject getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public void add(@Nullable MyObject object) {
        super.add(object);
    }

    @Override
    public void remove(@Nullable MyObject object){
        super.remove(object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.inventory_slot, parent , false);
        ImageView image = (ImageView) view.findViewById(R.id.imageSlotObject);
        MyObject obj = getItem(position);
        obj.setImage(image);
        image.setImageResource(obj.getIdImage());
        if(obj.isVisible()) {
            image.setVisibility(View.VISIBLE);
        }
        else {
            image.setVisibility(View.INVISIBLE);
        }
       return view;
    }

}
