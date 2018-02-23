package com.dolphin.awayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class EnigmeListe extends AppCompatActivity{

    ArrayList<EnigmeObject> enigmeList = null;
    String[] enigmeTitre = {"Aucune enigme n'est diponible"};


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.enigme_liste);

            ListView list_enigme = (ListView) findViewById(R.id.listEnigme);

            enigmeList = GameState.getGameState().getEnigmeObjectArrayList();
            for(int k= 0; k < enigmeList.size(); k++){
                enigmeTitre[k] = enigmeList.get(k).getTitle();
            }

            final ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, enigmeTitre);

            list_enigme.setAdapter(adapter);

            list_enigme.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    EnigmeObject enigme = enigmeList.get(position);
                    String text = enigme.getType() ;

                    Toast.makeText(EnigmeListe.this, text, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EnigmeListe.this, EnigmeTextChoix.class);
                    intent.setType((String.valueOf(position)));
                    startActivity(intent);
                    //test add
                    //String [] reponses = {"Sophie", "Héloise", "Nico", "Antoine"};
                    //enigmeListe.add((new EnigmeObject("Surnom","textChoix" ,"A qui faut il trouver un surnom ? ", reponses, "Sophie")));
                    //adapt.notifyDataSetChanged();
                }
            });
        }
}
