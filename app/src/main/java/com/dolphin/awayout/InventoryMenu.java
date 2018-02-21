package com.dolphin.awayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;



public class InventoryMenu extends AppCompatActivity {

    final InventoryAdapt adapt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_menu);

        final TextView title = (TextView) findViewById(R.id.textInventTitle);
        final TextView desc = (TextView) findViewById(R.id.textInventDesc);
        final ImageView img = (ImageView) findViewById(R.id.imageInventMenu);
        final GridView gridView = (GridView) findViewById(R.id.gridInventoryMenu);

        final InventoryAdapt adapt = GameState.getGameState().getInventory();
        final InventoryGridView inventory = new InventoryGridView(gridView, adapt, true);
        final InventoryMenuDescription menuTriggered = new InventoryMenuDescription(title,
                desc, img, inventory);

    }
}



