package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class fin_niveau extends AppCompatActivity {
    //private Button retourMenu;
    private TextView winMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_niveau);
        TextView winMessage= findViewById(R.id.histoire_fin_niveau);
        winMessage.setText(GameState.getGameState().winMessage);
        //retourMenu=findViewById(R.id.retour_menu);
    }
    public void onButtonChoixNiveauClick(View view){
        Intent intent=new Intent(this, Menu.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
