package com.dolphin.awayout;

import android.content.ClipDescription;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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

    public InventoryGridView(GridView view, InventoryAdapt inventoryAdapt, boolean itemDraggable) {
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
                //ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                MyObject obj = inventoryAdapt.getItem(i);
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                //ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(obj.getImage());
                v.startDragAndDrop(null, myShadow, obj, 0) ;
                return true;
            }

        });

        listView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        //v.setVisibility(View.INVISIBLE);
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
                        notifyDropObservers((MyObject) event.getLocalState(), event.getX(), event.getY());
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
                MyObject obj = inventoryAdapt.getItem(position);
                if(obj != null){
                    notifyClickObservers(obj);
                }
            }
        });
    }

    public void attach(InventoryObserver observer){
        observers.add(observer);
    }

    public void notifyClickObservers(MyObject object) {
        for(InventoryObserver observer : observers) {
            observer.update(1, object, 0, 0);
        }
    }

    public void notifyDropObservers(MyObject object, float x, float y){
        for(InventoryObserver observer : observers) {
            observer.update(2, object, Math.round(x), Math.round(y));
        }
    }
}
