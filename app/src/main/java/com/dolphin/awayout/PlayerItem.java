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

/**
 * Created by siasn on 17-02-18.
 */

public class PlayerItem implements Comparable{
    private int slotId; // Where your item is in inventory
    private String name;
    private String description;
    private String msg ="LOG PLAYER ITEM";
    private ImageView img;
    private boolean visible;
    private boolean draggable;
    private ImageView imgGoal = null; //Image triggered when collision after drag'n'drop

    public PlayerItem(int slotId, String title, String description, ImageView img,
                      boolean visible, boolean draggable, ImageView imgGoal) {
        this.slotId = slotId;
        this.name = title;
        this.description = description;
        this.img = img;
        this.visible = visible;
        if(isVisible()) {
            img.setVisibility(View.VISIBLE);
        }
        else {
            img.setVisibility(View.INVISIBLE);
        }
        this.draggable = draggable;
        if(isDraggable()) {
            this.imgGoal = imgGoal;
            combineWith();
        }
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public boolean isVisible(){
        return visible;
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

    private void setListenerDragNDrop() {
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
                        if (checkCollision(event.getX(), event.getY(), img, imgGoal)) {
                            Log.d(msg, "Collision detected : goalX =" + imgGoal.getX() + "goalY =" + imgGoal.getY());
                            Toast.makeText(imgGoal.getContext(), "GAGNEEEEE", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(imgGoal.getContext(), "Perdu", Toast.LENGTH_SHORT).show();
                        }
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


    //TO DO : avoid error if v2 is null
    public boolean checkCollision(float x, float y, ImageView v1, ImageView v2) {
        if(v2.getVisibility() == View.INVISIBLE)
            return false;
        Rect r1 = new Rect(Math.round(x), Math.round(y), Math.round(x + v1.getWidth()), Math.round(y + v1.getHeight()));
        Rect r2 = new Rect();
        v2.getHitRect(r2);
        return r1.intersect(r2);
    }



    @Override
    public int compareTo(@NonNull Object o) {
        return slotId - ((PlayerItem)o).getSlotId();
    }
}