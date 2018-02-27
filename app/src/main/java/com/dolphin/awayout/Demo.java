package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Demo extends AppCompatActivity {

    private ImageView imgKey, imgChest;
    private TextView timer;
    private Button demo_button;
    InventoryAdapt adapt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        imgKey = (ImageView) findViewById(R.id.key_demo);
        imgChest = (ImageView) findViewById(R.id.chest_demo);
        demo_button = (Button) findViewById(R.id.buttonMenuInventory);
        timer = (TextView) findViewById(R.id.timer);
        final ListView view = (ListView) findViewById(R.id.listinventory);

        GameState.getGameState().init(this);
        adapt = GameState.getGameState().getInventory();
        final InventoryListView inventor = new InventoryListView(view, adapt, true);

        timerLoop();
    }

    public void onButtonInventoryMenuClick(View view) {
        Intent intent = new Intent(Demo.this, InventoryMenu.class);
        startActivity(intent);
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
                            String neg = "";
                            if(time<0){
                                neg = "- ";
                                time = - time;
                            }
                            int sec = (int) (time%60);
                            int min = (int) ((time - sec)/60);
                            timer.setText(neg+min+":"+sec);
                            //timer.postInvalidate(); //not needed because we use setText
                        }
                    });
                }
            }
        };
        Thread myThread = new Thread(Loop);
        myThread.start();
    }
}



