package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


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


        /**
         * Inventaire
         */






        /*inventor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyObject obj = adapt.getItem(position);
                title.setText(obj.getName());
                Desc.setText(obj.getDescription());
                img.setImageResource(obj.getIdImage());

            }
        });*/


    }
}



