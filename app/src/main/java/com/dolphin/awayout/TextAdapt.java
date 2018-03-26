package com.dolphin.awayout;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class TextAdapt extends ArrayAdapter<Object>{


    public TextAdapt(@NonNull Context context, ArrayList<Object> Names) {
        super(context, R.layout.text_slot,Names);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.text_slot, parent , false);
        TextView text = (TextView) view.findViewById(R.id.my_text);
        Object obj = getItem(position);
        if (obj instanceof EnigmeObject){
            EnigmeObject enigme = (EnigmeObject) obj;
            text.setText((enigme.getTitle()));
        }
        else if (obj instanceof LevelObject){
                LevelObject levelObject = (LevelObject) obj;
                text.setText((levelObject.getTitle()));
            }
        else{
                //si l'objet est un  string
                text.setText((String) obj);
        }
        return view;
    }

}