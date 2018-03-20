package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class fin_niveau extends AppCompatActivity {
    private Button retourChoixNiveau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_niveau);
        retourChoixNiveau=findViewById(R.id.retour_choix_niveau);
    }
    public void onButtonChoixNiveauClick(View view){
        Intent intent=new Intent(this, Level_choice.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
