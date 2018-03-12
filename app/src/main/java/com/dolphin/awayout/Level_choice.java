package com.dolphin.awayout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Level_choice extends AppCompatActivity{

    ArrayList<Object> levelList = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_choice);

        levelList = new ArrayList<Object>();

        //Creer les niveau ici
        levelList.add(new LevelObject("Chapitre 1 : Dans le hall d'entrée ","Vous arriver dans un petit hall mal éclairé, le scénariste de l'escape room vous regarde le regard vide. Il n'a aucune idée de ce qu'il va mettre dans la description de ce niveau...",R.drawable.Chap1,900,"Réaumur"));
        //levelList.add(new LevelObject("Demo2"));

        ListView list_niveau = (ListView) findViewById(R.id.listNiveau);
        final TextAdapt adapter = new TextAdapt(this,levelList);
        list_niveau.setAdapter(adapter);



        list_niveau.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(levelList != null || levelList.size() >= 0){  // eviter le cas ou il n'y a pas d'enigme
                    LevelObject levelObject = (LevelObject) levelList.get(position);
                    //TO DO Initialiser le Gamestate prendre ou le faire dans la page suivante
                    LevelStart.myLevel = levelObject;
                    Intent intent = new Intent(Level_choice.this, LevelStart.class);
                    startActivity(intent);
                }
            }
        });
    }
}
