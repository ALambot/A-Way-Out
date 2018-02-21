package com.dolphin.awayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MyObject {
    private String name = null;
    private String description;
    private int idImage;
    //private int slotId; // Where your item is in inventory
    private String msg ="LOG PLAYER ITEM";
    private int imageId;
    private ImageView img = null;
    private boolean visible = true;
    private boolean draggable = false;
    //private ImageView imgGoal = null; //Image triggered when collision after drag'n'drop

    public MyObject(String name, int imageRef) {
        this.name = name;
        this.idImage = imageRef;
    }

    public MyObject(String name,String description, int imageRef) {
        this.name = name;
        this.description = description;
        this.idImage = imageRef;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = description;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public ImageView getImage() {
        return img;
    }

    public void setImage(ImageView image) {
        this.img = image;
    }



    //TO DO verifier si img not null
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if(visible) show();
        else hide();
    }


    public void show(){
        visible = true;
        img.setVisibility(View.VISIBLE);
    }

    public void hide(){
        visible = false;
        img.setVisibility(View.INVISIBLE);

    }



    public boolean isDraggable(){
        return draggable;
    }

    public void combineWith() {
        draggable = true;
        setListenerDragNDrop();
    }

    public void stopListenerDragNDrop(){
        img.setOnLongClickListener(null);
        img.setOnDragListener(null);
        img.setOnTouchListener(null);
    }

    public void setListenerDragNDrop() {
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(img);

                v.startDragAndDrop(dragData, myShadow, null, 0);
                return true;
            }
        });

        img.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        img.setVisibility(View.INVISIBLE);
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
                        img.setVisibility(View.VISIBLE);
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

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);
                    img.startDragAndDrop(data, shadowBuilder, img, 0);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

}
