package com.dolphin.awayout;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;



public class EnigmeList extends AppCompatActivity{

    ArrayList<EnigmeObject> enigmeList = null;
    ArrayList<String> enigmeTitre;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.enigme_liste);

            enigmeTitre = new ArrayList<String>();
            enigmeList = GameState.getGameState().getEnigmeList();
            if(enigmeList.size() == 0 ){ enigmeTitre.add("Aucune enigme n'est diponible");}
            for(int k= 0; k < enigmeList.size(); k++){
                enigmeTitre.add(enigmeList.get(k).getTitle());
            }

            ListView list_enigme = (ListView) findViewById(R.id.listEnigme);
            final ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, enigmeTitre){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    View view = super.getView(position, convertView, parent);
                    TextView txt = (TextView) view;
                    //txt.setTypeface(Typeface.createFromAsset(getAssets(),"precious.ttf"));
                    return view;
                }
            };
            list_enigme.setAdapter(adapter);



            list_enigme.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(enigmeList != null || enigmeList.size() >= 0){  // eviter le cas ou il n'y a pas d'enigme
                        EnigmeObject enigme = enigmeList.get(position);
                        Intent intent;
                        switch (enigme.getType()) {
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
                                intent=new Intent(EnigmeList.this, Enigme_Armoire.class);
                                startActivity(intent);
                        }
                    }
                }
            });
        }
}
