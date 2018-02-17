package com.dolphin.awayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Demo extends AppCompatActivity {

    private ImageView imgKey, imgChest;
    private int windowWidth, windowHeight;
    private ConstraintLayout.LayoutParams layoutParams;

    String msg ="Drag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        imgKey = (ImageView) findViewById(R.id.key_demo);
        imgChest = (ImageView) findViewById(R.id.chest_demo);
        //windowWidth = getWindowWidth();
        //windowHeight = getWindowHeight();


        imgKey.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(imgKey);

                v.startDragAndDrop(dragData,myShadow,null,0);
                return true;
            }
        });

        imgKey.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch(event.getAction())
                {
                    case DragEvent.ACTION_DRAG_STARTED:
                        imgKey.setVisibility(View.INVISIBLE);
                        Log.d(msg, "Started");
                        layoutParams = (ConstraintLayout.LayoutParams)v.getLayoutParams();


                        // Do nothing
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(msg, "Entered");
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_EXITED :
                        Log.d(msg, "Exited");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION  :
                        Log.d(msg, "Location");
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED   :
                        Log.d(msg, "ENDED");
                        imgKey.setVisibility(View.VISIBLE);
                        if(checkCollision(event.getX(), event.getY(), imgKey, imgChest)){
                            Log.d(msg, "COLLIDED");
                            Toast.makeText(getApplicationContext(), "GAGNEEEEE", Toast.LENGTH_LONG).show();
                        }
                        break;

                    case DragEvent.ACTION_DROP:
                        break;
                    default: break;
                }
                return true;
            }
        });

        imgKey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(imgKey);
                    imgKey.startDragAndDrop(data, shadowBuilder, imgKey, 0);
                    return true;
                }
                else {
                    return false;
                }
            }
        });

    }

    public boolean checkCollision(float x, float y, ImageView v1, ImageView v2) {
        float midX = (x + v1.getWidth())/2;
        float midY = (y + v1.getHeight())/2;
        return ((Math.abs(midX - v2.getX()) < v2.getWidth() && Math.abs(midY - v2.getY()) < v2.getHeight()));
    }
}

