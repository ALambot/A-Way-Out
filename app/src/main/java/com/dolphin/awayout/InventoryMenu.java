package com.dolphin.awayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



public class InventoryMenu extends AppCompatActivity {

    final InventoryAdapt adapt = null;
    public static TextView desc;
    public static TextView title;
    public static ImageView img;
    public static GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_menu);

        GameState.getGameState().inventaire=this;

        title = (TextView) findViewById(R.id.textInventTitle);
        desc = (TextView) findViewById(R.id.textInventDesc);
        img = (ImageView) findViewById(R.id.imageInventMenu);
        gridView = (GridView) findViewById(R.id.gridInventoryMenu);

        final InventoryAdapt adapt = GameState.getGameState().getInventoryAdapt();
        final InventoryGridView inventory = new InventoryGridView(gridView, adapt, true);
        final InventoryMenuDescription menuTriggered = new InventoryMenuDescription(title,
                desc, img, inventory);

    }


    protected static void onUpdate(){
        InventoryAdapt adapt = GameState.getGameState().getInventoryAdapt();
        final InventoryGridView inventory = new InventoryGridView(gridView, adapt, true);
        final InventoryMenuDescription menuTriggered = new InventoryMenuDescription(title,
                desc, img, inventory);

    }



}



