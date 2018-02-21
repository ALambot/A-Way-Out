package com.dolphin.awayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by siasn on 20-02-18.
 */

public class InventoryListView {
    InventoryAdapt inventoryAdapt;
    ListView listView;
    String msg = "LOG";

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
                    //ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                    String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                    //ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
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
                            Log.d(msg, "x :" + event.getX());
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
                if(obj != null)
                    Toast.makeText(listView.getContext(), obj.getName(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}

