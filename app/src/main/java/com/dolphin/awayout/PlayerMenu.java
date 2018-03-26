package com.dolphin.awayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class PlayerMenu extends AppCompatActivity {

    private TextView timer;
    private Button inventory_button;
    private Button enigme_button;
    private Button loupe_button;
    private IntentIntegrator qrScan;
    private InventoryAdapt adapt = null;
    public boolean started=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_menu);

        timer = (TextView) findViewById(R.id.timer);
        inventory_button = (Button) findViewById(R.id.buttonMenuInventory);
        enigme_button = (Button) findViewById(R.id.buttonMenuEnigme);
        loupe_button = (Button) findViewById(R.id.buttonMenuQRcode);

        GameState gameState = GameState.getGameState();
        gameState.init(this);
        gameState.startTimer();

        qrScan=new IntentIntegrator(this);

        timerLoop();
    }

    public void onButtonInventoryMenuClick(View view) {
        Intent intent = new Intent(PlayerMenu.this, InventoryMenu.class);
        startActivity(intent);
    }

    public void onButtonEnigmeListeClick(View view) {
        Intent intent = new Intent(PlayerMenu.this, EnigmeList.class);
        startActivity(intent);
    }

    public void onButtonQRClick(View view){
        qrScan.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result !=null){
            if(result.getContents()==null || !GameState.getGameState().getInteractions().isValidQR(result.getContents())){
                Toast.makeText(this, "Veuillez scanner un QR code de la partie en cours", Toast.LENGTH_LONG).show();
            }
            else{
                //qr has data
                InteractionManager im = GameState.getGameState().getInteractions();
                boolean found=im.QRresult(result.getContents());
                showPopup(PlayerMenu.this, result.getContents(), found); //TODO
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void showPopup(final Activity context, String object, boolean found){
        LinearLayout viewGroup=(LinearLayout) context.findViewById(R.id.popup_QR);
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=layoutInflater.inflate(R.layout.popup_postqr, viewGroup);

        final PopupWindow popup=new PopupWindow(context);
        popup.setContentView(layout);
        popup.setFocusable(true);

        popup.showAtLocation(layout, Gravity.CENTER,0,0);
        Button close = (Button) layout.findViewById(R.id.closePopUp);
        TextView title=layout.findViewById(R.id.textViewQRTitle);
        TextView textinPopUp=layout.findViewById(R.id.textQR);
        if (found) {
            title.setText("Bien joué !");


            textinPopUp.setText("Vous avez trouvé l'objet " + object);
        }
        else {
            title.setText("Attention");
            textinPopUp.setText("Vous avez déja trouvé l'objet "+object);
        }
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
    }


    public void exitPopup(final Activity context){
        LinearLayout viewGroup=(LinearLayout) context.findViewById(R.id.popup_QR);
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=layoutInflater.inflate(R.layout.popup_exit, viewGroup);

        final PopupWindow popup=new PopupWindow(context);
        popup.setContentView(layout);
        popup.setFocusable(true);

        popup.showAtLocation(layout, Gravity.CENTER,0,0);
        Button no = (Button) layout.findViewById(R.id.exitPopUpNo);
        Button yes = (Button) layout.findViewById(R.id.exitPopUpYes);

        no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerMenu.this, Menu.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        exitPopup(PlayerMenu.this);
    }


   public void timerLoop(){

        Runnable Loop = new Runnable(){
            @Override
            public void run(){

                while(true){
                    timer.post(new Runnable() {
                        @Override
                        public void run(){
                            GameState gameState = GameState.getGameState();
                            long time = gameState.getRemainingTime();
                            StringBuilder stringBuilder = new StringBuilder();
                            String neg = "";
                            if(time<0){
                                if (started) {
                                    started=false;
                                    InteractionManager interactionManager = GameState.getGameState().getInteractions();
                                    interactionManager.timeOut();

                                }
                                neg = "- ";
                                time = - time;

                            }
                            stringBuilder.append(neg);
                            int sec = (int) (time%60);
                            int min = (int) ((time - sec)/60);

                            if(Integer.toString(min).length() == 1) {
                                stringBuilder.append("0");
                            }
                            stringBuilder.append(min);
                            stringBuilder.append(":");
                            if(Integer.toString(sec).length()==1) {
                                stringBuilder.append("0");
                            }
                            stringBuilder.append(sec);
                            timer.setText(stringBuilder.toString());
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                    }
                }
            }
        };
        Thread myThread = new Thread(Loop);
        myThread.start();
    }


}



