package com.dolphin.awayout;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;



import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import org.w3c.dom.Text;

public class EcranAcueil extends AppCompatActivity implements  View.OnClickListener{

    private Button buttonLoupe;
    private Button buttonInventaire;
    private Button buttonEnigmes;
    private TextView timerMain;
    private Context mContext;
    private PopupWindow popUp;
    private TextView textInPopUp;
    private RelativeLayout relativeLayout;

    private IntentIntegrator qrScan;




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


        qrScan=new IntentIntegrator(this);
        buttonLoupe= (Button) findViewById(R.id.buttonLoupe);
        buttonEnigmes= (Button) findViewById(R.id.buttonEnigmes);
        buttonInventaire=(Button) findViewById(R.id.buttonInventaire);
        timerMain=(TextView) findViewById((R.id.timerMain)) ;
        relativeLayout=(RelativeLayout) findViewById(R.id.rl_popUp) ;
        mContext=getApplicationContext();
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

        buttonLoupe.setOnClickListener(this);

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

    //Partie scanner QR code
    //recuperation des resultats des scans
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result !=null){
            if(result.getContents()==null){
                Toast.makeText(this, "QR code illisible", Toast.LENGTH_LONG).show();
            }
            else{
                //qr has data

                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    //Bout de code pour faire le pop up. En travail.
                    /*LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context. LAYOUT_INFLATER_SERVICE);
                    View customView=inflater.inflate(R.layout.activity_pop_up__post_qr, null);
                    float density=EcranAcueil.this.getResources().getDisplayMetrics().density;
                    final PopupWindow popUp=new PopupWindow(customView, (int)density*240, (int) density*280);

                    Button buttonQuit=(Button) customView.findViewById(R.id.buttonquit_PopUp);
                    buttonQuit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popUp.dismiss();
                        }
                    });
                    textInPopUp=customView.findViewById(R.id.titlePoPUp);
                    textInPopUp.setText("Felicitations ! Vous avez débloqué : la clé ");


                    popUp.showAtLocation(relativeLayout, Gravity.CENTER,0,0);*/
                }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }






    @Override
    public void onClick(View view){
        qrScan.initiateScan();

    }

}
