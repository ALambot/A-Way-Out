package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by antoine on 27.03.18.
 */

public class ChapterTest implements Chapter {

    public ChapterTest() {
        //...
    }

    public void load(){
        long gameduration = 42;

        // GameObjects
        ArrayList<GameObject> gobs = new ArrayList<GameObject>();

        gobs.add(new GameObject("fricadelle", "Cette fricadelle est garantie 100% sans viande de dauphin ! ;)", R.drawable.gold));

        // Enigmes
        ArrayList<EnigmeObject> enigmes = new ArrayList<EnigmeObject>();

        // Hints
        ArrayList<Hint> hints = new ArrayList<Hint>();

        //hints.add(new Hint("Il vous manque peut-être encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));
        //hints.add(new Hint("Vazy vide l'eau du vase dans le bol", new HintFlag[]{new HintFlag("HAS_GOB","vase"),new HintFlag("HAS_GOB","bol vide")}));

        GameState.getGameState().init(gameduration,gobs,enigmes,hints,this,"","");

        // --------------------------------------

        //Interactions
        InteractionManager im = new InteractionManager(gobs.size());

        // DEBUG
        im.addQR("Pain", new Interaction("PENALITE", "30"));
        im.addQR("miam", new Interaction("ADD_GOB", "fricadelle"));

        im.addTimeOut(new Interaction("launch activity", "LooseScreen.class"));
        im.addTimeOut(new Interaction("GAMEOVER",null));

        GameState.getGameState().addIM(im);

    }

}
