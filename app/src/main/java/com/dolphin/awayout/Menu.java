package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Button demo
        final Button button = findViewById(R.id.demo_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchDemo();
            }
    });
    }

    public void launchDemo(){
        Intent intent = new Intent(this, Demo.class);
        startActivity(intent);
    }

}
