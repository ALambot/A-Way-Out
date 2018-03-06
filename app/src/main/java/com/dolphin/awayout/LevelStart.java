package com.dolphin.awayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class LevelStart extends AppCompatActivity{

    public static LevelObject myLevel;
    private TextView title;
    private ImageView img;
    private TextView desc;
    private TextView localisation;
    private TextView temps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_start);

        title = (TextView) findViewById(R.id.textTitle);
        img = (ImageView) findViewById(R.id.imageNiveau);
        desc = (TextView) findViewById(R.id.textDescription);
        localisation = (TextView) findViewById(R.id.textLocalisation);
        temps = (TextView) findViewById(R.id.textTemps);

        title.setText(myLevel.getTitle());
        img.setImageResource(myLevel.getImageId());
        desc.setText(myLevel.getDescription());
        localisation.setText(myLevel.getLocalisation());
        temps.setText(myLevel.getStringTemps());

    }

    public void onButtonGameStartClick(View view){
        Intent intent = new Intent(this, Demo.class);
        startActivity(intent);
    }

}
