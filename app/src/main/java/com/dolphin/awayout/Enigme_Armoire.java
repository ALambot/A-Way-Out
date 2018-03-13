package com.dolphin.awayout;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Enigme_Armoire extends AppCompatActivity {

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
    private ListView inventaire;
    private InventoryListView inventair;
    private String code="";
    private String reponse="ILCFSMPB";
    private InventoryAdapt inventoryAdapt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme__armoire);
        buttonA=findViewById(R.id.buttonA);
        buttonB=findViewById(R.id.buttonB);
        buttonC=findViewById(R.id.buttonC);
        buttonV=findViewById(R.id.buttonV);
        buttonD=findViewById(R.id.buttonD);
        buttonI=findViewById(R.id.buttonI);
        buttonT=findViewById(R.id.buttonT);
        buttonO=findViewById(R.id.buttonO);
        buttonF=findViewById(R.id.buttonF);
        buttonQ=findViewById(R.id.buttonQ);
        buttonX=findViewById(R.id.buttonX);
        buttonH=findViewById(R.id.buttonH);
        buttonL=findViewById(R.id.buttonL);
        buttonP=findViewById(R.id.buttonP);
        buttonU=findViewById(R.id.buttonU);
        inventaire=findViewById(R.id.listView_enigme_armoir);

        inventoryAdapt = GameState.getGameState().getInventoryAdapt();

        inventair=new InventoryListView(Enigme_Armoire.this,inventaire,inventoryAdapt, true);
    }


    public void onButtonLClick(View vieuw){
        Button p=(Button) vieuw;
        p.setBackgroundColor(Color.parseColor("#E6E6FA"));
        code=code+p.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
        //Toast.makeText(this, code,Toast.LENGTH_LONG).show();
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
            //interactionManager.enigmeFail("Armoir");
            Toast.makeText(this, "Mauvais Code ! Vous perdez 3 minutes", Toast.LENGTH_LONG).show();

        }
    }

}
