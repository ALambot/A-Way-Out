package com.dolphin.awayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;



public class EnigmeList extends AppCompatActivity{

    ArrayList<Object> enigmeList = null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.enigme_liste);

            enigmeList = GameState.getGameState().getEnigmeList2();

            ListView list_enigme = (ListView) findViewById(R.id.listEnigme);
            final TextAdapt adapter;
            if(enigmeList.size() == 0 ){
                ArrayList<Object> NoEnigme = new ArrayList<Object>();
                NoEnigme.add(new EnigmeObject("Aucune enigme n'est disponible pour le moment", 1 , null));
                adapter = new TextAdapt(this, NoEnigme);
            }
            else {
                adapter = new TextAdapt(this, enigmeList);
            }
            list_enigme.setAdapter(adapter);



            list_enigme.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(enigmeList != null &&  enigmeList.size() > 0){  // eviter le cas ou il n'y a pas d'enigme
                        EnigmeObject enigme = (EnigmeObject) enigmeList.get(position);
                        Intent intent;
                        switch (enigme. getType()) {
                            case 1:
                                EnigmeTextChoix.enigme = enigme;
                                intent = new Intent(EnigmeList.this, EnigmeTextChoix.class);
                                startActivity(intent);
                                break;
                            case 2 :
                                intent = new Intent(EnigmeList.this, cypherRoll_enigme.class);
                                startActivity(intent);
                                break;
                            case 3:
                                intent=new Intent(EnigmeList.this, EnigmeArmoire.class);
                                startActivity(intent);
                                break;
                            case 4:
                                EnigmeCode.enigme = enigme;
                                intent=new Intent(EnigmeList.this, EnigmeCode.class);
                                startActivity(intent);
                                break;
                        }
                    }
                }
            });
        }
}
