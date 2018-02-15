package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button demo_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Button demo
        demo_button = findViewById(R.id.demo_button);
    }

    public void onButtonDemoClick(View view){
        Intent intent = new Intent(this, Demo.class);
        startActivity(intent);
    }

}
