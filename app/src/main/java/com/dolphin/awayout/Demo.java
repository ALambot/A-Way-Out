package com.dolphin.awayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class Demo extends AppCompatActivity {

    private ImageView imgKey;
    private int windowWidth, windowHeight;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        imgKey = findViewById(R.id.key_demo);
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
                        layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();

                        // Do nothing
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_EXITED :
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        View view = (View) event.getLocalState();
                        view.setX(x_cord - (view.getWidth() / 2));
                        view.setY(y_cord - (view.getWidth() / 2));
                        view.setVisibility(View.VISIBLE);
                        break;

                    case DragEvent.ACTION_DRAG_LOCATION  :
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        break;

                    case DragEvent.ACTION_DRAG_ENDED   :
                        if (!event.getResult())
                            v.setVisibility(View.VISIBLE);
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
                    imgKey.setVisibility(View.INVISIBLE);
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setVisibility(View.VISIBLE);
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });

    }

/*
    public void onKeyClick(View v, MotionEvent event){
        LayoutParams layoutParams = (LayoutParams) v.getLayoutParams();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int x_cord = (int) event.getRawX();
                int y_cord = (int) event.getRawY();

                if (x_cord > windowWidth) {
                    x_cord = windowWidth;
                }
                if (y_cord > windowHeight) {
                    y_cord = windowHeight;
                }

                layoutParams.leftMargin = x_cord - 25;
                layoutParams.topMargin = y_cord - 75;
                v.setLayoutParams(layoutParams);
                break;
            default:
                break;
        }
    }

    private int getWindowWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        int version = android.os.Build.VERSION.SDK_INT;
        if (version >= 13)
        {
            Point size = new Point();
            display.getSize(size);
            windowWidth = size.x;
        }
        else
        {
            windowWidth = display.getWidth();
        }
        return windowWidth;
    }

    private int getWindowHeight(){
        Display display = getWindowManager().getDefaultDisplay();
        int version = android.os.Build.VERSION.SDK_INT;
        if (version >= 13)
        {
            Point size = new Point();
            display.getSize(size);
            windowHeight = size.y;
        }
        else
        {
            windowHeight = display.getHeight();
        }
        return windowHeight;
    }
    */
}

