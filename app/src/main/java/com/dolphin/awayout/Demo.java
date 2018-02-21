package com.dolphin.awayout;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class Demo extends AppCompatActivity {

    private ImageView imgKey, imgChest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        imgKey = (ImageView) findViewById(R.id.key_demo);
        imgChest = (ImageView) findViewById(R.id.chest_demo);
        PlayerItem key = new PlayerItem(1, "clé", "ceci est une clé", imgKey,
                true, true, imgChest);
    }//lol


}

