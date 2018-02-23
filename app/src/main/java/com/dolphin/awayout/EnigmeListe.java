package com.dolphin.awayout;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EnigmeListe extends AppCompatActivity{

    ArrayList<EnigmeObject> enigmeListe = null;
    String[] enigmeTitre = {"Aucune enigme n'est diponible"};
    ListAdapter adapter = null;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.enigme_liste);

            final ListView list_enigme = (ListView) findViewById(R.id.listEnigme);

            enigmeListe = GameState.getGameState().getEnigmeObjectArrayList();
            for(int k= 0; k < enigmeListe.size(); k++){
                enigmeTitre[k] = enigmeListe.get(k).getTitle();
            }


            //adapter = new ArrayAdapter<EnigmeObject>(this, android.R.layout.simple_list_item_1, enigmeListe);
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, enigmeTitre);

            list_enigme.setAdapter(adapter);
        }


}
