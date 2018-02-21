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

        final TextView title = (TextView) findViewById(R.id.textInventTilte);
        final TextView desc = (TextView) findViewById(R.id.textInventDesc);
        final ImageView img = (ImageView) findViewById(R.id.imageInventMenu);
        Intent intent = getIntent();
        final InventoryAdapt adapt = GameState.getGameState().getInventory();


        String longDesc = "nkfbjd jrbvcjs vbs djfbesd kbr df cerdhfkj   irdhj,fd  fersbf ejdfsbefdj ve kfrheusbgver s erbsnv erd ufbersb df er f re ifbersj fe dsrn  fehfb rej feksbf er " +
                "fbejrvbfhrebfker fuegfbfkbrhfvk urfbebfkref  rhfeihfiehf rfgefef   fehfbbfbefkfbjrebfje";

        /**
         * Inventaire
         */

        final GridView gridView = (GridView) findViewById(R.id.gridInventoryMenu);
        final InventoryGridView inventor = new InventoryGridView(gridView, adapt, true);
        InventoryMenuDescription menuTriggered = new InventoryMenuDescription(title, desc, img, inventor);



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



