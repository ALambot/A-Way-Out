package com.dolphin.awayout;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class InventoryMenu extends AppCompatActivity {

    final InventoryAdapt adapt = null;
    public static TextView desc;
    public static TextView title;
    public static ImageView img;
    public static Drawable inventoryLogo;
    public static GridView gridView;
    public static ImageView combiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_menu);

        GameState.getGameState().inventaire=this;

        title = (TextView) findViewById(R.id.textInventTitle);
        desc = (TextView) findViewById(R.id.textInventDesc);
        img = (ImageView) findViewById(R.id.imageInventMenu);
        inventoryLogo = img.getDrawable();
        gridView = (GridView) findViewById(R.id.gridInventoryMenu);
        combiner = (ImageView) findViewById(R.id.combine);

        final InventoryAdapt adapt = GameState.getGameState().getInventoryAdapt();
        final InventoryGridView inventory = new InventoryGridView(gridView, adapt, combiner);
        final InventoryMenuDescription menuTriggered = new InventoryMenuDescription(title,
                desc, img, inventory);

    }


    protected static void onUpdate(){
        InventoryAdapt adapt = GameState.getGameState().getInventoryAdapt();
        final InventoryGridView inventory = new InventoryGridView(gridView, adapt, combiner);
        title.setText("Votre inventaire");
        desc.setText("Appuyez sur un des éléments pour l'observer. Pour combiner un objet, appuyez sur un objet puis glissez un autre objet vers l'image principale de l'écran");
        img.setImageDrawable(inventoryLogo);
        final InventoryMenuDescription menuTriggered = new InventoryMenuDescription(title,
                desc, img, inventory);

    }



}



