package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by DimiS on 17/04/18.
 */

public class Chapter2 implements Chapter {

    private String title;
    private String description;
    private String location;
    private int imageID;
    private int duration;

    public Chapter2() {
        this.title = "Chapitre 2 : \n Le poudrier";
        this.description = "À côté de Lady Doubtshire se trouvait ce poudrier. Parviendrez-vous à l'ouvrir ?";
        this.location = "Réaumur : Siemens";
        this.imageID = R.drawable.gold;
        this.duration = 1800;
    }

    public void load(){

        ArrayList<GameObject> gobs = new ArrayList<>();


        gobs.add(new GameObject("poudrier", "Le poudrier trouvé dans la main de la victime. Il est fermé par un code à quatre chiffres", R.drawable.ch2_poudrier));
        gobs.add(new GameObject("boite", "Une boite fermée par un code à dix chiffres. Que pourrait-elle contenir ?", R.drawable.key_demo));
        gobs.add(new GameObject("Poster dessiné", "Un grand poster du ballet de la belle au bois dormant.",R.drawable.ch2_poster2));
        gobs.add(new GameObject("Encyclopedie", "L'Encyclopedie de Gutenberg.", R.drawable.ch2_encycopedie));
        gobs.add(new GameObject("bol de noix", "Un bol de noix. Lady Doubthsire en raffolait.", R.drawable.ch2_nuts));
        gobs.add(new GameObject("papier", "Un papier avec un code mystérieux.", R.drawable.key_demo));
        gobs.add(new GameObject("veste", "La veste de Lady Doubtshire.Il semblerait que quelquque chose soit coincé dans la doublure.", R.drawable.key_demo));
        gobs.add(new GameObject("miroir", "Une mirroir posé sur la cheminée.", R.drawable.ch2_mirroir2));
        gobs.add(new GameObject("feu", "Un bon feu brule dans la cheminée.", R.drawable.key_demo));
        gobs.add(new GameObject("dragees", "Des dragées de couleurs différentes.", R.drawable.ch2_dragees));
        gobs.add(new GameObject("cle", "Une clé. Que pourrait-elle ouvrir ?", R.drawable.key_demo));
        gobs.add(new GameObject("Armoire à parfums", "L'armoir des parfums de lady Doubtshire, elle est fermée à clé.", R.drawable.key_demo));
        gobs.add(new GameObject("ciseaux", "Une paire de petits ciseaux.", R.drawable.key_demo));
        gobs.add(new GameObject("bol", "Un bol rempli d'eau.", R.drawable.chamber_pot_filled));
        gobs.add(new GameObject("bol bouillant", "Un bol rempli d'eau bouillante. De la valeur d'eau s'en échappe.", R.drawable.chamber_pot_filled));
        gobs.add(new GameObject("parfums", "Les parfums de lady Doubtshire", R.drawable.key_demo));
        gobs.add(new GameObject("mirroir avec un code", "Des inscriptions mystérieuses écrites sur le miroir.", R.drawable.key_demo));
        gobs.add(new GameObject("Petit poster ", "Un poster du ballet de casse noissettes. Un des préférés de Lady Doubtshire",R.drawable.key_demo));



        //// TODO
        ArrayList<EnigmeObject> enigmes =new ArrayList<>();



        enigmes.add(new EnigmeObject("boite fermée par un code","..........","ilovepigpen",R.drawable.tuto_coffret));
        enigmes.add(new EnigmeObject("poudrier","_ _ _ _","5236",R.drawable.ch2_poudrier));

        ArrayList<Hint> hints =new ArrayList<>();

        hints.add(new Hint("Il vous manque peut-être encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));

        String looseMessage = "Votre temps est écoulé ! Le majordome vous escorte hors de la maison. Vous devrez recommencer du début";
        String winMessage= "A l'intérieur du poudrier se trouvait un petit mot indiquant \"Tu va mourir ! signé: L.D\". Ce sont les initiales de la fille ainée de Lady Doubtshire ! Mais pourquoi tuer sa propre mère ?";//TODO

        GameState.getGameState().init(this.duration,gobs,enigmes,hints,this,looseMessage, winMessage);

        // --------------------------------------


        InteractionManager im = new InteractionManager(gobs.size());


       // im.addQR("Poudrier", new Interaction( "ADD_GOB","poudrier"));
        im.addQR("Poudrier", new Interaction("SHOW_ENIGME", "poudrier"));
        im.addQR("Ciseaux", new Interaction( "ADD_GOB", "ciseaux"));
        im.addQR("Veste", new Interaction( "ADD_GOB", "veste"));
        im.addQR("Encyclopedie", new Interaction( "ADD_GOB", "Encyclopedie"));
        im.addQR("PosterBelle", new Interaction( "ADD_GOB", "Poster dessiné"));
        im.addQR("Petit poster ", new Interaction( "ADD_GOB", "Petit poster "));
        im.addQR("Bol", new Interaction( "ADD_GOB", "bol"));
        //im.addQR("Boite", new Interaction( "ADD_GOB", "boite"));
        im.addQR("Boite", new Interaction( "SHOW_ENIGME", "boite fermée par un code"));
        im.addQR("Noix", new Interaction( "ADD_GOB", "bol de noix"));
        im.addQR("Feu", new Interaction( "ADD_GOB", "feu"));
        im.addQR("Miroir", new Interaction( "ADD_GOB", "miroir"));

        //im.addQR("Jackpot", new Interaction("ADD_GOB","poudrier"));
        im.addQR("Jackpot", new Interaction( "SHOW_ENIGME", "boite fermée par un code"));
        im.addQR("Jackpot", new Interaction( "SHOW_ENIGME", "poudrier"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","ciseaux"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","veste"));
        //im.addQR("Jackpot", new Interaction("ADD_GOB", ""))
         im.addQR("Jackpot", new Interaction("ADD_GOB","Encyclopedie"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Poster dessiné"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Petit poster "));
        im.addQR("Jackpot", new Interaction("ADD_GOB","boite"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","bol de noix"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","bol"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","feu"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","miroir"));

        GameState.getGameState().remainingQR = 11;


        im.addCombi("cle", "Armoire à parfums", new Interaction("REMOVE_GOB", "Armoire à parfums"));
        im.addCombi("cle", "Armoire à parfums", new Interaction("REMOVE_GOB", "cle"));
        im.addCombi("cle", "Armoire à parfums", new Interaction("REMOVE_GOB", "ciseaux"));
        im.addCombi("cle", "Armoire à parfums", new Interaction("ADD_GOB", "parfums"));


        im.addCombi("ciseaux","Poster dessiné", new Interaction("ADD_GOB", "Armoire à parfums"));
        im.addCombi("ciseaux","Poster dessiné", new Interaction("REMOVE_GOB", "Poster dessiné"));
        im.addCombi("ciseaux","Poster dessiné", new Interaction("LAUNCH_POPUP"," Vous découpez le poster, derriere lui se trouve une Armoire à parfums fermée à clé"));

        im.addCombi("Encyclopedie","bol de noix", new Interaction("ADD_GOB", "papier")); //ch1_vase+bol =boule transparente
        im.addCombi("Encyclopedie","bol de noix", new Interaction("LAUNCH_POPUP", "Vous ecrasez les noix avec l'enclopégie. A l'interieur de l'une d'entre elle se trouve un papier."));
        im.addCombi("Encyclopedie","bol de noix", new Interaction("REMOVE_GOB", "Encyclopedie"));
        im.addCombi("Encyclopedie","bol de noix", new Interaction("REMOVE_GOB", "bol de noix"));
        im.addCombi("Encyclopedie","bol de noix", new Interaction("REMOVE_GOB", "Petit poster "));

        im.addCombi("ciseaux","veste", new Interaction("ADD_GOB", "cle"));
        im.addCombi("ciseaux","veste", new Interaction("REMOVE_GOB", "veste"));
        im.addCombi("ciseaux","veste", new Interaction("LAUNCH_POPUP"," Vous découpez la doublure de la veste. Vous y trouvez une clé !"));


        /*im.addCombi("bol","feu", new Interaction("ADD_GOB", "mirroir avec un code"));
        im.addCombi("bol","feu", new Interaction("REMOVE_GOB", "feu"));
        im.addCombi("bol","feu", new Interaction("REMOVE_GOB", "bol"));
        im.addCombi("bol","feu", new Interaction("REMOVE_GOB", "miroir"));
        im.addCombi("bol","feu", new Interaction("LAUNCH_POPUP","En versant de l'eau sur le feu, de la vapeur révèle un dessin sur le miroir"));*/


        im.addCombi("bol","feu", new Interaction("ADD_GOB", "bol bouillant"));
        im.addCombi("bol","feu", new Interaction("REMOVE_GOB", "feu"));
        im.addCombi("bol","feu", new Interaction("REMOVE_GOB", "bol"));
        im.addCombi("bol","feu", new Interaction("REMOVE_GOB", "miroir"));
        im.addCombi("bol","feu", new Interaction("LAUNCH_POPUP","Vous faites bouillir l'eau sur le feu"));



        im.addCombi("bol bouillant","miroir", new Interaction("ADD_GOB", "mirroir avec un code"));
        im.addCombi("bol bouillant","miroir", new Interaction("REMOVE_GOB", "miroir"));
        im.addCombi("bol bouillant","miroir", new Interaction("REMOVE_GOB", "bol bouillant"));
        im.addCombi("bol bouillant","miroir", new Interaction("LAUNCH_POPUP","Vous approchez le bol du mirroir, la vapeur révèle un dessin sur le miroir"));


        im.addCombi("bol","feu", new Interaction("ADD_GOB", "mirroir avec un code"));
        im.addCombi("bol","feu", new Interaction("REMOVE_GOB", "miroir"));

        // ENIGME WIN
        im.addEnigmeWIN("boite fermée par un code",new Interaction("ADD_GOB", "dragees"));
        im.addEnigmeWIN("boite fermée par un code",new Interaction("LAUNCH_POPUP_ENIGME", "Dans le boîte, vous trouvez quelques draguées"));
        im.addEnigmeWIN("boite fermée par un code", new Interaction("HIDE_ENIGME", "boite fermée par un code"));
        im.addEnigmeWIN("boite fermée par un code",new Interaction("REMOVE_GOB", "boite"));
        im.addEnigmeWIN("boite fermée par un code",new Interaction("REMOVE_GOB", "mirroir avec un code"));
       // im.addEnigmeWIN("boite fermée par un code",new Interaction("REMOVE_GOB", "papier"));


        im.addEnigmeWIN("poudrier",new Interaction("WIN",null)); //Armoir +code

        // ENIGME LOSE
        im.addEnigmeLOSE("boite fermée par un code", new Interaction("PENALITE", "180"));
        im.addEnigmeLOSE("poudrier", new Interaction("PENALITE", "180"));

        //im.addTimeOut(new Interaction("launch activity", "LooseScreen.class"));
        im.addTimeOut(new Interaction("GAMEOVER",null));

        GameState.getGameState().addIM(im);

    }

    //boite avec code  10
    //postercasse noissette
    // poster belle aux bois dorman
    //Encyclopediepedie de G.
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
    //Armoire à parfums
    //ch1_armoire
    //parfums
    //traductioncode

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
