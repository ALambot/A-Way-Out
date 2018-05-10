package com.dolphin.awayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by antoine on 08.05.18.
 */

public class MontreGousset extends AppCompatActivity{

    private ImageView aiguilleH;
    private ImageView aiguilleM;

    private int H = 6;
    private int M = 0;

    private ListView inventaire;
    private InventoryAdapt inventoryAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_montre_gousset_enigme);

        //aiguilleH=findViewById(R.id.TODO);
        //aiguilleM=findViewById(R.id.TODO);

        //inventaire=findViewById(R.id.inventaire_liste); TO CHECK

        inventoryAdapt = GameState.getGameState().getInventoryAdapt();


    }

}
