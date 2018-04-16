package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LooseScreen extends AppCompatActivity {
    private Button retour_choix_niveau;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loose_screen);
        retour_choix_niveau=findViewById(R.id.perdu_retour_niveau);
        TextView looseMessage=findViewById(R.id.looseMessage);
        looseMessage.setText(GameState.getGameState().looseMessage);

    }

    protected void onClickRetour(View view){
        Intent intent=new Intent(this, Menu.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {

    }
}
