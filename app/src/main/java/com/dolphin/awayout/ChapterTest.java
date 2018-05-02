package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by antoine on 27.03.18.
 */

public class ChapterTest implements Chapter {

    private String title;
    private String description;
    private String location;
    private int imageID;
    private int duration;

    public ChapterTest() {
        this.title = "Test : \n Sombre histoire de Dauphins";
        this.description = "Vous allez perdre !";
        this.location = "where am i ???";
        this.imageID = R.drawable.gold;
        this.duration = 42;
    }

    public void load(){

        // GameObjects
        ArrayList<GameObject> gobs = new ArrayList<GameObject>();

        gobs.add(new GameObject("fricadelle", "Cette fricadelle est garantie 100% sans viande de dauphin ! ;)", R.drawable.gold));

        // Enigmes
        ArrayList<EnigmeObject> enigmes = new ArrayList<EnigmeObject>();

        // Hints
        ArrayList<Hint> hints = new ArrayList<Hint>();

        //hints.add(new Hint("Il vous manque peut-être encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));
        //hints.add(new Hint("Vazy vide l'eau du ch1_vase dans le bol", new HintFlag[]{new HintFlag("HAS_GOB","ch1_vase"),new HintFlag("HAS_GOB","bol vide")}));

        GameState.getGameState().init(duration,gobs,enigmes,hints,this,"","");

        // --------------------------------------

        //Interactions
        InteractionManager im = new InteractionManager(gobs.size());

        // DEBUG
        im.addQR("Pain", new Interaction("PENALITE", "30"));
        im.addQR("miam", new Interaction("ADD_GOB", "fricadelle"));
        im.addQR("crash", new Interaction( "CRASH", null));

        //im.addTimeOut(new Interaction("launch activity", "LooseScreen.class"));
        im.addTimeOut(new Interaction("GAMEOVER",null));

        GameState.getGameState().addIM(im);

    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public int getImageID() {
        return this.imageID;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

}
