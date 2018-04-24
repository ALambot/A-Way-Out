package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class Menu extends AppCompatActivity {

    private Button demo_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_menu);
    }

    public void onButtonLevelChoiceClick(View view){
        Intent intent = new Intent(this, Level_choice.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
