package com.dolphin.awayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class EnigmeArmoire extends AppCompatActivity {

    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonV;
    private Button buttonD;
    private Button buttonI;
    private Button buttonT;
    private Button buttonO;
    private Button buttonF;
    private Button buttonQ;
    private Button buttonX;
    private Button buttonH;
    private Button buttonL;
    private Button buttonP;
    private Button buttonU;
    private Button buttonR;
    private ListView inventaire;
    private InventoryListView inventair;
    private String code="";
    private String reponse="ILCFSMPB";
    private InventoryAdapt inventoryAdapt;
    private ArrayList<Button> boutons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme__armoire);
        boutons=new ArrayList<>();
        buttonA=findViewById(R.id.buttonA);
        boutons.add(buttonA);
        buttonB=findViewById(R.id.buttonB);
        boutons.add(buttonB);
        buttonC=findViewById(R.id.buttonC);
        boutons.add(buttonC);
        buttonV=findViewById(R.id.buttonV);
        boutons.add(buttonV);
        buttonD=findViewById(R.id.buttonD);
        boutons.add(buttonD);
        buttonI=findViewById(R.id.buttonI);
        boutons.add(buttonI);
        buttonT=findViewById(R.id.buttonT);
        boutons.add(buttonT);
        buttonO=findViewById(R.id.buttonO);
        boutons.add(buttonO);
        buttonF=findViewById(R.id.buttonF);
        boutons.add(buttonF);
        buttonQ=findViewById(R.id.buttonQ);
        boutons.add(buttonQ);
        buttonX=findViewById(R.id.buttonX);
        boutons.add(buttonX);
        buttonH=findViewById(R.id.buttonH);
        boutons.add(buttonH);
        buttonL=findViewById(R.id.buttonL);
        boutons.add(buttonL);
        buttonP=findViewById(R.id.buttonP);
        boutons.add(buttonP);
        buttonU=findViewById(R.id.buttonU);
        boutons.add(buttonU);
        buttonR=findViewById(R.id.buttonR);
        boutons.add(buttonR);


        inventaire=findViewById(R.id.listView_enigme_armoir);

        inventoryAdapt = GameState.getGameState().getInventoryAdapt();

        inventair=new InventoryListView(EnigmeArmoire.this,inventaire,inventoryAdapt, true);
    }


    public void onButtonLClick(View vieuw){
        final Button p=(Button) vieuw;

        p.setTextColor(Color.parseColor("#E6E6FA"));
        code=code+p.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }



    }

    public void setButtonsColorBack(){
        for(Button b: boutons){
            b.setTextColor(Color.parseColor("#efd9be"));
        }
    }


    public void codeWritten(){
        InteractionManager interactionManager=GameState.getGameState().getInteractions();
        if (code.equals(reponse)){
            /*Intent de fin*/
            //interactionManager.enigmeSuccess("Armoir");
            Intent intent= new Intent(this, fin_niveau.class);
            startActivity(intent);

        }
        else{
            code="";
            interactionManager.enigmeFail("Armoir");
            setButtonsColorBack();
            Toast.makeText(this, "Mauvais Code ! Vous perdez 3 minutes", Toast.LENGTH_LONG).show();

        }
    }

}
