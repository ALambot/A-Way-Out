package com.dolphin.awayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EnigmeTextChoix extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_liste);
        Intent intent = getIntent();
        Toast.makeText(EnigmeTextChoix.this, intent.getType(), Toast.LENGTH_SHORT).show();

    }
}
