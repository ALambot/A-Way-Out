package com.dolphin.awayout;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class cypherRoll_enigme extends AppCompatActivity {

    private ImageButton droite;
    private ImageButton gauche;
    private ImageView insideCypher;
    private ListView inventaire;
    private InventoryListView inventair;
    private InventoryAdapt inventoryAdapt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cypher_roll_enigme);



        gauche=findViewById(R.id.button_versGauche);
        droite=findViewById(R.id.bouton_versdroite);
        insideCypher=findViewById(R.id.insidside_circle);
        inventaire=findViewById(R.id.inventaire_liste);

        inventoryAdapt = GameState.getGameState().getInventoryAdapt();

        inventair=new InventoryListView(cypherRoll_enigme.this,inventaire,inventoryAdapt, true);





    }

    public void onButtonGaucheClick(View vieuw){

        insideCypher.setRotation(insideCypher.getRotation() - 15);
    }
    public void onButtonDroiteClick(View vieuw){

        insideCypher.setRotation(insideCypher.getRotation() + 15);
    }


}
