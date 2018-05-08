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
        levelList.add(new LevelObject(new ChapterTuto()));
        levelList.add(new LevelObject(new Chapter1()));
        levelList.add(new LevelObject(new Chapter2()));
        //levelList.add(new LevelObject(new ChapterTest()));

        ListView list_niveau = (ListView) findViewById(R.id.listNiveau);
        final TextAdapt adapter = new TextAdapt(this,levelList);
        list_niveau.setAdapter(adapter);
        this.ctx = this;


        list_niveau.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(levelList != null || levelList.size() >= 0){
                    LevelObject levelObject = (LevelObject) levelList.get(position);

                    LevelStart.myLevel = levelObject;
                    Intent intent = new Intent(Level_choice.this, LevelStart.class);
                    levelObject.load(); //ceci crée et initialise le GameState depuis le Chapitre
                    GameState.getGameState().addContext(ctx);
                    startActivity(intent);
                }
            }
        });
    }
}
