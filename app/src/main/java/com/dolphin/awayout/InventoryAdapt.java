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


public class InventoryAdapt extends ArrayAdapter<GameObject>{


    public InventoryAdapt(@NonNull Context context, ArrayList<GameObject> ImageNames) {
        super(context, R.layout.inventory_slot,ImageNames);
    }

    @Nullable
    @Override
    public GameObject getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public void add(@Nullable GameObject object) {
        super.add(object);
    }

    @Override
    public void remove(@Nullable GameObject object){
        super.remove(object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.inventory_slot, parent , false);
        ImageView image = (ImageView) view.findViewById(R.id.imageSlotObject);
        GameObject obj = getItem(position);
        image.setImageResource(obj.getIdImage());
        obj.setImage(image);
       return view;
    }

}
