package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Demo extends AppCompatActivity {

    private ImageView imgKey, imgChest;
    private Button demo_button;
    InventoryAdapt adapt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


        imgKey = (ImageView) findViewById(R.id.key_demo);
        imgChest = (ImageView) findViewById(R.id.chest_demo);

        ArrayList<MyObject> inventObject = new ArrayList<MyObject>();
        inventObject.add(new MyObject("cle", R.drawable.key_demo));
        inventObject.add(new MyObject("cle2", R.drawable.key_demo));
        inventObject.add(new MyObject("coffre", R.drawable.chest_demo));
        inventObject.add(new MyObject("coffre2", R.drawable.chest_demo));

        adapt = new InventoryAdapt(this, inventObject);
        final ListView view = (ListView) findViewById(R.id.listinventory);
        final InventoryListView inventor = new InventoryListView(view, adapt, true);

        GameState.getGameState().setInventory(adapt);

        demo_button=(Button) findViewById(R.id.buttonMenuInventory);

    }

    public void onButtonInventoryMenuClick(View view) {
        Intent intent = new Intent(Demo.this, InventoryMenu.class);
        startActivity(intent);
    }
}



