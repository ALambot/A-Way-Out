package com.dolphin.awayout;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siasn on 21-02-18.
 */

public class InventoryGridView {

    private InventoryAdapt inventoryAdapt;
    private GridView listView;
    private String msg = "LOG";
    private List<InventoryObserver> observers = new ArrayList<InventoryObserver>();
    private ImageView combiner;
    private boolean isCombining;

    public InventoryGridView(GridView view, InventoryAdapt inventoryAdapt, ImageView combiner) {
        this.listView = view;
        this.inventoryAdapt = inventoryAdapt;
        this.combiner = combiner;
        this.isCombining = false;
        view.setAdapter(inventoryAdapt);
        onClickDisplayTitle();
        onClickCombine();
    }

    private void swapCombine() {
        if(isCombining) {
            isCombining = false;
            combiner.setImageResource(R.drawable.combine_button);
        }
        else {
            isCombining = true;
            combiner.setImageResource(R.drawable.combine_button_on);
        }

    }

    public void onClickCombine() {
        combiner.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapCombine();
            }
        });
    }

    private void onClickDisplayTitle() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameObject obj = inventoryAdapt.getItem(position);
                if(obj != null){
                    notifyClickObservers(obj);
                }
            }
        });
    }

    public void attach(InventoryObserver observer){
        observers.add(observer);
    }

    private void notifyClickObservers(GameObject object) {
        for(InventoryObserver observer : observers) {
            if(!isCombining)
                observer.update(1, object, 0, 0);
            else {
                observer.update(2, object, 0 , 0);
                swapCombine();
            }

        }
    }

}
