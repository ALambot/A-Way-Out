package com.dolphin.awayout;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siasn on 20-02-18.
 */

public class InventoryListView {
    private InventoryAdapt inventoryAdapt;
    private ListView listView;
    private String msg = "LOG";
    private List<InventoryObserver> observers = new ArrayList<InventoryObserver>();

    public InventoryListView(ListView view, InventoryAdapt inventoryAdapt, boolean itemDraggable) {
        this.listView = view;
        this.inventoryAdapt = inventoryAdapt;
        view.setAdapter(inventoryAdapt);
        onClickDisplayTitle();
        if(itemDraggable)
            setListenerDragNDrop();
    }

    private void setListenerDragNDrop () {
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View v, int i, long l) {
                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
                    v.startDragAndDrop(null, myShadow, null, 0) ;
                    return true;
                }

            });

            listView.setOnDragListener(new View.OnDragListener() {
                @Override
                public boolean onDrag(View v, DragEvent event) {
                    switch (event.getAction()) {
                        case DragEvent.ACTION_DRAG_STARTED:
                            v.setVisibility(View.VISIBLE);
                            Log.d(msg, "Started");
                            break;

                        case DragEvent.ACTION_DRAG_ENTERED:
                            Log.d(msg, "Entered");
                            int x_cord = (int) event.getX();
                            int y_cord = (int) event.getY();
                            break;

                        case DragEvent.ACTION_DRAG_EXITED:
                            Log.d(msg, "Exited");
                            break;

                        case DragEvent.ACTION_DRAG_LOCATION:
                            Log.d(msg, "Location");
                            x_cord = (int) event.getX();
                            y_cord = (int) event.getY();
                            break;

                        case DragEvent.ACTION_DRAG_ENDED:
                            Log.d(msg, "Ended");
                            notifyDropObservers((GameObject) event.getLocalState(), event.getX(), event.getY());
                            break;

                        case DragEvent.ACTION_DROP:
                            Log.d(msg, "Drop");
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
    }

    private void onClickDisplayTitle() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameObject obj = inventoryAdapt.getItem(position);
                if(obj != null)
                    Toast.makeText(listView.getContext(), obj.getName(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void attach(InventoryObserver observer){
        observers.add(observer);
    }

    private void notifyDropObservers(GameObject object, float x, float y){
        for(InventoryObserver observer : observers) {
            observer.update(2, object, Math.round(x), Math.round(y));
        }
    }

}

