package com.dolphin.awayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EcranAcueil extends AppCompatActivity {

    private Button buttonLoupe;
    private Button buttonInventaire;
    private Button buttonEnigmes;
    private TextView timerMain;
    public int counter;
    boolean timerHasStarted=false;
    CountDownTimer countDownTimer;
    long startTime=30*10000;
    long interval=1*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_acueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonLoupe= (Button) findViewById(R.id.buttonLoupe);
        buttonEnigmes= (Button) findViewById(R.id.buttonEnigmes);
        buttonInventaire=(Button) findViewById(R.id.buttonInventaire);
        timerMain=(TextView) findViewById((R.id.timerMain)) ;
/*
        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            timerMain.setText("Stop");
        } else {
            countDownTimer.cancel();
            timerHasStarted = false;
            timerMain.setText("Restart");
        }
*/
        countDownTimer = new CountDownTimer(startTime, interval) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerMain.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timerMain.setText("Time's Up!");
            }
        };

        buttonLoupe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Ce bouton n'a pas encore de role", Toast.LENGTH_LONG).show();
            }
        });

        buttonEnigmes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Ce bouton n'a pas encore de role", Toast.LENGTH_LONG).show();
            }
        });

        buttonInventaire.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EcranAcueil.this, Demo.class);
                startActivity(intent);
            }
        });



    }



}
