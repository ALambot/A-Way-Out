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

    private ImageView imgKey, imgChest;
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

        ArrayList<MyObject> inventObject = new ArrayList<>();
        ArrayList<EnigmeObject> enigmeList = new ArrayList<>();



        /**
         * Partie Demo
         */

        inventObject.add(new MyObject("Clé inutile", "Cette clé semble inutile", R.drawable.key_demo));
        inventObject.add(new MyObject("Coffre", "Ce coffre renferme peut-être la réponse à l'énigme", R.drawable.chest_demo));
        //inventObject.add(new MyObject("coffre2", "Ceci est un autre coffre", R.drawable.chest_demo));


        String [] reponses = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Dauphin" ,"Qui est le prince des dauphins ? ", reponses, "Nico")));
        String [] reponses2 = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Surnom","A qui faut il trouver un surnom ? ", reponses2, "Sophie")));
        /**
         * Partie menu de jeu
         */
        adapt = new InventoryAdapt(this, inventObject);
        final ListView invent_view = (ListView) findViewById(R.id.listinventory);
        final InventoryListView inventor = new InventoryListView(invent_view, adapt, true);

        GameState.getGameState().setInventory(adapt);
        GameState.getGameState().setEnigmeObjectArrayList(enigmeList);

        inventory_button=(Button) findViewById(R.id.buttonMenuInventory);
        enigme_button = (Button) findViewById(R.id.buttonMenuEnigme);
        loupe_button = (Button) findViewById(R.id.buttonMenuQRcode);
        qrScan=new IntentIntegrator(this);

        timer = (TextView) findViewById(R.id.timer);
        timerLoop();

        GameState.getGameState().setInteractions();
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
                if(result.getContents().equals("Bonne clé")) {
                        GameState.getGameState().getInventory().add(new MyObject("Bonne clé", "Ceci est la bonne clé pour ouvrir le coffre", R.drawable.key_demo));
                        showPopup(Demo.this, result.getContents());
                }

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
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                    }
                    timer.post(new Runnable() {
                        @Override
                        public void run(){
                            GameState gameState = GameState.getGameState();
                            long time = gameState.getRemainingTime();
                            int sec = (int) (time%60);
                            int min = (int) ((time - sec)/60);
                            timer.setText(min+":"+sec);
                            timer.postInvalidate();
                        }
                    });
                }
            }
        };
        Thread myThread = new Thread(Loop);
        myThread.start();
    }
}



