package com.dolphin.awayout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Level_choice extends AppCompatActivity{

    ArrayList<Object> levelList = null;
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_choice);

        levelList = new ArrayList<Object>();

        //Creer les niveau ici
        String UnfinishedText = "Vous arriver dans un petit hall mal éclairé, le scénariste de l'escape room vous regarde le regard vide. " +
                "Et vous dis avec une voix denuée de toute émotion \"This is an unpolished draft of the escape room\"";
        String description = "Lady Doubthshire a été assasinée ! \n Son corps sans vie a été retrouvé ce matin.\n Tous s'interroge sur ce qui a bien pu arriver à une si vertueuse personne.\n " +
                "On a fait appel à vos service pour résoudre cette mystère.\n \n" +
                "Vous feriez mieux de vous depêcher avant que toute preuve ne disparaisse !\n";
        levelList.add(new LevelObject("Tutoriel: \n Derniers préparatifs ","Vous allez perdre ! \n \n \n \n\n \n \n \n \n pas de message gentil \n Heloise",R.drawable.gold,600,"Partout", new ChapterTuto()));
        levelList.add(new LevelObject("Chapitre 1 : \n Des debut difficiles ",description,R.drawable.chap1,900,"Réaumur", new Chapter1()));
        levelList.add(new LevelObject("Test : \n Sombre histoire de Dauphins","OwO what's this",R.drawable.gold,60,"where am i ???", new ChapterTest()));

        ListView list_niveau = (ListView) findViewById(R.id.listNiveau);
        final TextAdapt adapter = new TextAdapt(this,levelList);
        list_niveau.setAdapter(adapter);
        this.ctx = this;


        list_niveau.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(levelList != null || levelList.size() >= 0){  // eviter le cas ou il n'y a pas d'enigme
                    LevelObject levelObject = (LevelObject) levelList.get(position);
                    //TO DO Initialiser le Gamestate prendre ou le faire dans la page suivante


                    LevelStart.myLevel = levelObject;
                    Intent intent = new Intent(Level_choice.this, LevelStart.class);
                    levelObject.load();
                    GameState.getGameState().addContext(ctx);
                    startActivity(intent);
                }
            }
        });
    }
}
