package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by DimiS on 17/04/18.
 */

public class Chapter2 implements Chapter {


    public Chapter2() {

    }

    public void load(){
        long gameduration=10000;

        ArrayList<GameObject> gobs = new ArrayList<>();


        gobs.add(new GameObject("poudrier", "Le poudrier trouvé dans la main de la victime. Il est fermé par un code à quatre chiffres", R.drawable.key_demo));
        gobs.add(new GameObject("boite", "Une boite fermée par un code à dix chiffres. Que pourrait-elle contenir ?", R.drawable.key_demo));
        gobs.add(new GameObject("posterBauBD", "Ung rand poster du ballet de la belle au bois dormant.",R.drawable.key_demo));
        gobs.add(new GameObject("encyclo", "L'encyclopédie de Gutenberg.", R.drawable.key_demo));
        gobs.add(new GameObject("bol de noix", "Un bol de noix. Lady Doubthsire en raffolait.", R.drawable.key_demo));
        gobs.add(new GameObject("papier", "Un papier avec un code mystérieux.", R.drawable.key_demo));
        gobs.add(new GameObject("veste", "La veste de Lady Doubtshire.Il semblerait que quelquque chose soit coincé dans la doublure.", R.drawable.key_demo));
        gobs.add(new GameObject("mirroir", "Une mirroir posé sur la cheminée.", R.drawable.key_demo));
        gobs.add(new GameObject("feu", "Un bon feu brule dans la cheminée.", R.drawable.key_demo));
        gobs.add(new GameObject("dragees", "Des dragées de couleurs différentes.", R.drawable.key_demo));
        gobs.add(new GameObject("cle", "Une clé. Que pourrait-elle ouvrir ?", R.drawable.key_demo));
        gobs.add(new GameObject("armoire", "L'armoir des parfums de lady Doubtshire, elle est fermée à clé.", R.drawable.key_demo));
        gobs.add(new GameObject("ciseaux", "Une paire de petits ciseaux.", R.drawable.key_demo));
        gobs.add(new GameObject("bol", "Un bol rempli d'eau.", R.drawable.key_demo));
        gobs.add(new GameObject("parfums", "Les parfums de lady Doubtshire", R.drawable.key_demo));
        gobs.add(new GameObject("traducteur", "Des inscriptions mystérieuses écites sur le miroir.", R.drawable.key_demo));
        gobs.add(new GameObject("posterCN", "Un poster du ballet de casse noissettes. Un des préférés de Lady Doubtshire",R.drawable.key_demo));



        //// TODO
        ArrayList<EnigmeObject> enigmes =new ArrayList<>();



        enigmes.add(new EnigmeObject("codeboite", 2, "ilovepigpen"));

        engimes.add(new EnigmeObject("poudrier", 2, "5236"));

        ArrayList<Hint> hints =new ArrayList<>();

        String looseMessage = "Votre temps est écoulé ! Le majordome vous escorte hors de la maison. Vous devrez recommencer du début";
        String winMessage= "IDFJGQIGSDGKDFJKEF";//TODO

        GameState.getGameState().init(gameduration,gobs,enigmes,hints,this,looseMessage, winMessage);

        // --------------------------------------


        InteractionManager im = new InteractionManager(gobs.size());

        im.addQR("Poudrier", new Interaction( "ADD_GOB","poudrier"));
        im.addQR("Ciseaux", new Interaction( "ADD_GOB", "ciseaux"));
        im.addQR("Veste", new Interaction( "ADD_GOB", "veste"));
        im.addQR("Encyclo", new Interaction( "ADD_GOB", "encyclo"));
        im.addQR("PosterBelle", new Interaction( "ADD_GOB", "posterBauBD"));
        im.addQR("PosterCN", new Interaction( "ADD_GOB", "posterCN"));
        im.addQR("Bol", new Interaction( "ADD_GOB", "bol"));
        im.addQR("Boite", new Interaction( "ADD_GOB", "boite"));
        im.addQR("Noix", new Interaction( "ADD_GOB", "bol de noix"));
        im.addQR("Feu", new Interaction( "ADD_GOB", "feu"));
        im.addQR("Mirroir", new Interaction( "ADD_GOB", "mirroir"));
        GameState.getGameState().remainingQR = 11;


        im.addCombi("ciseaux","posterBauBD", new Interaction("ADD_GOB", "armoire"));
        im.addCombi("ciseaux","posterBauBD", new Interaction("REMOVE_GOB", "posterBauBD"));
        im.addCombi("ciseaux","posterBauBD", new Interaction("LAUNCH_POPUP"," Vous découpez le poster, derriere lui se trouve une armoire fermée à clé"));

        im.addCombi("encyclo","bol de noix", new Interaction("ADD_GOB", "papier")); //vase+bol =boule transparente
        im.addCombi("encyclo","bol de noix", new Interaction("LAUNCH_POPUP", "Vous ecrasez les noix avec l'enclopégie. A l'interieur de l'une d'entre elle se trouve un papier."));
        im.addCombi("encyclo","bol de noix", new Interaction("REMOVE_GOB", "encyclo"));
        im.addCombi("encyclo","bol de noix", new Interaction("REMOVE_GOB", "bol de noix"));

        im.addCombi("ciseaux","veste", new Interaction("ADD_GOB", "cle"));
        im.addCombi("ciseaux","veste", new Interaction("REMOVE_GOB", "veste"));
        im.addCombi("ciseaux","veste", new Interaction("LAUNCH_POPUP"," Vous découpez la doublure de la veste. Vous y trouvez une clé"));


        im.addCombi("cruche","feu", new Interaction("ADD_GOB", "traduction"));
        im.addCombi("cruche","feu", new Interaction("REMOVE_GOB", "feu"));
        im.addCombi("cruche","feu", new Interaction("REMOVE_GOB", "cruche"));
        im.addCombi("cruche","feu", new Interaction("REMOVE_GOB", "mirroir"));
        im.addCombi("cruche","feu", new Interaction("LAUNCH_POPUP","En versant de l'eau sur le feu, de la vapeur révèle un dessin sur le miroir"));


        // ENIGME WIN
        im.addEnigmeWIN("codeboite",new Interaction("ADD_GOB", "dragees"));
        im.addEnigmeWIN("codeboite",new Interaction("REMOVE_GOB", "boite"));
        im.addEnigmeWIN("codeboite",new Interaction("REMOVE_GOB", "traduction"));
        im.addEnigmeWIN("codeboite",new Interaction("REMOVE_GOB", "papier"));

        im.addEnigmeWIN("poudrier",new Interaction("WIN",null)); //Armoir +code

        // ENIGME LOSE
        im.addEnigmeLOSE("codeboite", new Interaction("PENALITE", "180"));
        im.addEnigmeLOSE("poudrier", new Interaction("PENALITE", "180"));

        //im.addTimeOut(new Interaction("launch activity", "LooseScreen.class"));
        im.addTimeOut(new Interaction("GAMEOVER",null));

        GameState.getGameState().addIM(im);









    }

    //boite avec code  10
    //postercasse noissette
    // poster belle aux bois dorman
    //Encyclopedie de G.
    // Bol de noix
    // Papier  code + X
    // Veste
    // Mirroir
    // Feu de bois
    // Dragee
    // cle
    //ciseaux
    //bol eau
    //pourdrier
    //armoire
    //parfums
    //traductioncode




}
