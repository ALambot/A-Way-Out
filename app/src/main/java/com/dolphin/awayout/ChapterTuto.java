package com.dolphin.awayout;

import java.util.ArrayList;


public class ChapterTuto implements Chapter {

    public ChapterTuto() {
        //...
    }

    public void load(){
        long gameduration = 600;

        // GameObjects
        ArrayList<GameObject> gobs = new ArrayList<GameObject>();

        gobs.add(new GameObject("Montre", "Ma montre, j'y tient bien quelle soit à l'arrêt depuis longtemps", R.drawable.tuto_montre));
        gobs.add(new GameObject("Lentille", "TO DO", R.drawable.mirror));
        gobs.add(new GameObject("Morceau de loupe", "Un morceau de loupe, il manque la lentille", R.drawable.tuto_loupe));
        gobs.add(new GameObject("Coffret", "TO DO", R.drawable.tuto_coffret));


        // Enigmes
        ArrayList<EnigmeObject> enigmes = new ArrayList<EnigmeObject>();
        enigmes.add(new EnigmeObject("Coffret",4,"953"));

        // Hints
        ArrayList<Hint> hints = new ArrayList<Hint>();

        hints.add(new Hint("Il vous manque peut-être encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));

        GameState.getGameState().init(gameduration,gobs,enigmes,hints,this);

        // --------------------------------------

        //Interactions
        InteractionManager im = new InteractionManager(gobs.size());

        // DEBUG
        im.addQR("Pain", new Interaction("PENALITE", "30"));
        im.addQR("Jackpot", new Interaction("SHOW_ENIGME", "Coffret"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "Morceau de loupe"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "Montre"));
        //im.addQR("Jackpot", new Interaction( "ADD_GOB", "Lentille"));

        // QR
        im.addQR("Coffret", new Interaction("SHOW_ENIGME", "Coffret"));
        im.addQR("Morceau de loupe", new Interaction( "ADD_GOB", "Morceau de loupe"));
        im.addQR("Montre", new Interaction( "ADD_GOB", "Montre"));
        GameState.getGameState().remainingQR = 3; //TODO TEMPORARY

        // COMBI
        im.addCombi("Morceau de loupe","Lentille", new Interaction("REMOVE_GOB", "Morceau de loupe"));
        im.addCombi("Morceau de loupe","Lentille", new Interaction("REMOVE_GOB", "Lentille"));
        im.addCombi("Morceau de loupe","Lentille", new Interaction("WIN", null));
        im.addCombi("Lentille","Morceau de loupe", new Interaction("REMOVE_GOB", "Morceau de loupe"));
        im.addCombi("Lentille","Morceau de loupe", new Interaction("REMOVE_GOB", "Lentille"));
        im.addCombi("Lentille","Morceau de loupe", new Interaction("Win", null));


        // ENIGME WIN
        im.addEnigmeWIN("Coffret",new Interaction("ADD_GOB", "Lentille"));
        im.addEnigmeLOSE("Coffret", new Interaction("PENALITE", "30"));


        // ENIGME LOSE
        im.addEnigmeLOSE("Coffret", new Interaction("PENALITE", "30"));

        im.addTimeOut(new Interaction("launch activity", "LooseScreen.class"));
        im.addTimeOut(new Interaction("GAMEOVER",null));

        GameState.getGameState().addIM(im);

    }

}

