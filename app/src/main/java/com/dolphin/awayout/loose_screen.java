package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loose_screen extends AppCompatActivity {
    private Button retour_choix_niveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loose_screen);
        retour_choix_niveau=findViewById(R.id.perdu_retour_niveau);

    }

    protected void onClickRetour(View view){
        Intent intent=new Intent(this, Level_choice.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {

    }
}
