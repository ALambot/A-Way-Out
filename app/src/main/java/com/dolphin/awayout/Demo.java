package com.dolphin.awayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.ArrayList;


public class Demo extends AppCompatActivity {

    private TextView timer;
    private Button inventory_button;
    private Button enigme_button;
    private Button loupe_button;
    private IntentIntegrator qrScan;
    InventoryAdapt adapt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        timer = (TextView) findViewById(R.id.timer);
        inventory_button = (Button) findViewById(R.id.buttonMenuInventory);
        enigme_button = (Button) findViewById(R.id.buttonMenuEnigme);
        loupe_button = (Button) findViewById(R.id.buttonMenuQRcode);

        GameState gameState = GameState.getGameState();
        gameState.init(this);
        gameState.startTimer();

        /*
        ArrayList<EnigmeObject> enigmeList = new ArrayList<>();
        String [] reponses = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Dauphin" ,"Qui est le prince des dauphins ? ", reponses, "Nico")));
        String [] reponses2 = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Surnom","A qui faut il trouver un surnom ? ", reponses2, "Sophie")));
        GameState.getGameState().setEnigmeObjectArrayList(enigmeList);
        */

        qrScan=new IntentIntegrator(this);

        timerLoop();
    }

    public void onButtonInventoryMenuClick(View view) {
        Intent intent = new Intent(Demo.this, InventoryMenu.class);
        startActivity(intent);
    }

    public void onButtonEnigmeListeClick(View view) {
        Intent intent = new Intent(Demo.this, EnigmeList.class);
        startActivity(intent);
    }

    public void onButtonQRClick(View view){
        qrScan.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result !=null){
            if(result.getContents()==null){
                Toast.makeText(this, "QR code illisible", Toast.LENGTH_LONG).show();
            }
            else{
                //qr has data
                InteractionManager im = GameState.getGameState().getInteractions();
                im.QRresult(result.getContents());
                showPopup(Demo.this, result.getContents());
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void showPopup(final Activity context, String object){
        LinearLayout viewGroup=(LinearLayout) context.findViewById(R.id.popup_QR);
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout=layoutInflater.inflate(R.layout.popup_postqr, viewGroup);

        final PopupWindow popup=new PopupWindow(context);
        popup.setContentView(layout);
        popup.setFocusable(true);

        popup.showAtLocation(layout, Gravity.CENTER,0,0);
        Button close = (Button) layout.findViewById(R.id.closePopUp);
        TextView title=layout.findViewById(R.id.textViewQRTitle);
        title.setText("Bien joué !");

        TextView textinPopUp=layout.findViewById(R.id.textQR);
        textinPopUp.setText("Vous avez trouvé l'objet " +object);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });


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
                            String neg = "";
                            if(time<0){
                                neg = "- ";
                                time = - time;
                            }
                            int sec = (int) (time%60);
                            int min = (int) ((time - sec)/60);
                            StringBuilder stringBuilder = new StringBuilder();
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



