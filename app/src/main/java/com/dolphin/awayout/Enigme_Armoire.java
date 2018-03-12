package com.dolphin.awayout;

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
    private String code="";
    private String reponse="ULPQXTOD";


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
    }

    public void onButtonUClick(View vieuw){
        code=code+buttonU.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonPClick(View vieuw){
        code=code+buttonP.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonLClick(View vieuw){
        code=code+buttonL.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonHClick(View vieuw){
        code=code+buttonH.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonXClick(View vieuw){
        code=code+buttonX.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonQClick(View vieuw){
        code=code+buttonQ.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonFClick(View vieuw){
        code=code+buttonF.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonOClick(View vieuw){
        code=code+buttonO.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonTClick(View vieuw){
        code=code+buttonT.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonIClick(View vieuw){
        code=code+buttonI.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonDClick(View vieuw){
        code=code+buttonD.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonAClick(View view){

        code=code+buttonA.getText().toString();
        Toast.makeText(this, code, Toast.LENGTH_SHORT).show();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonBClick(View vieuw){
        code=code+buttonB.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonCClick(View vieuw){
        code=code+buttonC.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void onButtonVClick(View vieuw){
        code=code+buttonV.getText().toString();
        if( code.length()==reponse.length()){
            codeWritten();
        }
    }

    public void codeWritten(){
        InteractionManager interactionManager=new InteractionManager();
        if (code.equals(reponse)){
            interactionManager.enigmeSuccess("enigme armoire");

        }
        else{
            code="";
            interactionManager.enigmeFail("enigme armoire");
            /*Ajouter pénalité*/

        }
    }

}
