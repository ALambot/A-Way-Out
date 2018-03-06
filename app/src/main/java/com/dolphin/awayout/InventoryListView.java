package com.dolphin.awayout;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
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
    private Activity activity = null;



    public InventoryListView(Activity activity,  ListView view, InventoryAdapt inventoryAdapt, boolean itemDraggable) {
        this.activity = activity;
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

                    showPopupInventaire(obj);

            }
        });
    }

    public void showPopupInventaire(GameObject obj){
        LinearLayout viewGroup=(LinearLayout) activity.findViewById(R.id.popup_Inventaire);
        LayoutInflater layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=layoutInflater.inflate(R.layout.popup_inventaire, viewGroup);

        final PopupWindow popup=new PopupWindow(activity);
        popup.setContentView(layout);
        popup.setFocusable(true);

        popup.showAtLocation(layout, Gravity.CENTER,0,0);
        Button close = (Button) layout.findViewById(R.id.closePopUpInventaire);
        TextView title=layout.findViewById(R.id.textViewObjectTitle);
        title.setText(obj.getName());

        TextView textinPopUp=layout.findViewById(R.id.textDescriptionInPopUp_inventaire);
        textinPopUp.setText(obj.getDescription());

        ImageView image=(ImageView) layout.findViewById(R.id.imageViewPop_up_Inventaire);

        image.setImageDrawable(obj.getImage().getDrawable());

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
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

