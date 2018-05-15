package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by DimiS on 17/04/18.
 */

public class Chapter1 implements Chapter {

    private String title;
    private String description;
    private String location;
    private int imageID;
    private int duration;

    public Chapter1() {
        this.title = "Chapitre 1 : \n Le Poudrier Ensanglanté";
        this.description = "\"Lady Doubtshire a été assassinée ! \n Son corps sans vie a été retrouvé ce matin.\n Tous s'interrogent.\n Qui aurait bien pu vouloir s'en prendre à elle ?\n" +
                "On a fait appel à vos services pour résoudre ce mystère.\n \n" +
                "En arrivant, vous trouvez le corps ensanglanté de Lady Doubtshire étendu sur le sol. Dans sa main, se trouve un poudrier. Vous le prenez mais impossible de l'ouvrir ! \n \n" +
                "Parviendrez vous à percer le secret de ce mystérieux poudrier.\n";
        this.location = "Réaumur : intel";
        this.imageID = R.drawable.ch2_start;
        this.duration = 2700;
    }

    public void load(){

        ArrayList<GameObject> gobs = new ArrayList<>();


        //gobs.add(new GameObject("Poudrier", "Le poudrier trouvé dans la main de la victime. Il est fermé par un code à quatre chiffres", R.drawable.ch2_poudrier));
        //gobs.add(new GameObject("Boîte", "Une boite fermée par un code à dix chiffres. Que pourrait-elle contenir ?", R.drawable.ch2_boite));
        gobs.add(new GameObject("Petit poster ", "Un grand poster du ballet : le lac des signes.",R.drawable.ch2_poster1));
        gobs.add(new GameObject("Encyclopedie", "L'Encyclopedie de Gutenberg.", R.drawable.ch2_encycopedie));
        gobs.add(new GameObject("Bol de noix", "Un bol de noix. Lady Doubtsire en raffolait.", R.drawable.ch2_nuts));
        gobs.add(new GameObject("Morceau de papier", "Un papier avec un code mystérieux.", R.drawable.ch2_papier_code2));
        gobs.add(new GameObject("Veste", "La veste de Lady Doubtshire. Il semblerait que quelque chose soit coincé dans la doublure.", R.drawable.ch2_veste));
        gobs.add(new GameObject("Miroir", "Une miroir posé sur la cheminée.", R.drawable.ch2_mirroir2));
        gobs.add(new GameObject("Feu de bois", "Un bon feu brûle dans la cheminée.", R.drawable.ch2_feu));
        gobs.add(new GameObject("Dragées", "Des dragées de couleurs différentes.", R.drawable.ch2_dragees));
        gobs.add(new GameObject("Clé", "Une clé.\n Que pourrait-elle ouvrir ?", R.drawable.ch2_cle));
        gobs.add(new GameObject("Armoire encastrée dans le mur", "Une armoire secrète cachée dans un mur. Elle est fermée à clé.", R.drawable.ch2_coffre));
        gobs.add(new GameObject("Ciseaux", "Une paire de petits ciseaux.", R.drawable.ch2_ciseaux));
        gobs.add(new GameObject("Bol d'eau", "Un bol rempli d'eau.", R.drawable.chamber_pot_filled));
        gobs.add(new GameObject("Bol d'eau bouillante", "Un bol rempli d'eau bouillante. De la valeur d'eau s'en échappe.", R.drawable.ch2_bol_fumant));
        gobs.add(new GameObject("Parfums", "Les parfums de Lady Doubtshire", R.drawable.ch2_parfums));
        gobs.add(new GameObject("Mystérieuses inscriptions", "De mystérieuses inscriptions transcrites depuis celles du miroir.", R.drawable.ch2_papier_code));
        gobs.add(new GameObject("Poster dessiné", "Un poster du ballet de Casse Noisette. Un des préférés de Lady Doubtshire",R.drawable.ch2_poster2));


        //// TODO
        ArrayList<EnigmeObject> enigmes =new ArrayList<>();

        enigmes.add(new EnigmeObject("Boîte fermée par un code","..........","ilovepigpen",R.drawable.ch2_boite));
        enigmes.add(new EnigmeObject("Poudrier","_ _ _ _","3518",R.drawable.ch2_poudrier));

        ArrayList<Hint> hints =new ArrayList<>();

        hints.add(new Hint("Il vous manque peut-être encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));

        String looseMessage = "Votre temps est écoulé ! Le majordome vous escorte hors de la maison. Vous devrez recommencer du début";
        String winMessage= "A l'intérieur du poudrier se trouvait un petit mot indiquant \"Tu vas mourir ! signé: L.D\". Ce sont les initiales de la fille ainée de Lady Doubtshire ! Mais pourquoi vouloir tuer sa propre mère ?";//TODO

        GameState.getGameState().init(this.duration,gobs,enigmes,hints,this,looseMessage, winMessage);

        // --------------------------------------


        InteractionManager im = new InteractionManager(gobs.size());


       // im.addQR("Poudrier", new Interaction( "ADD_GOB","Poudrier"));
        im.addQR("Poudrier", new Interaction("SHOW_ENIGME", "Poudrier"));
        //im.addQR("Poudrier", new Interaction("ADD_GOB", "Poudrier"));
        im.addQR("Ciseaux", new Interaction( "ADD_GOB", "Ciseaux"));
        im.addQR("Veste", new Interaction( "ADD_GOB", "Veste"));
        im.addQR("Encyclo", new Interaction( "ADD_GOB", "Encyclopedie"));
        im.addQR("PosterBelle", new Interaction( "ADD_GOB", "Poster dessiné"));
        im.addQR("PosterCN", new Interaction( "ADD_GOB", "Petit poster "));
        im.addQR("Bol", new Interaction( "ADD_GOB", "Bol d'eau"));
        //im.addQR("Boîte", new Interaction( "ADD_GOB", "Boîte"));
        im.addQR("Boite", new Interaction( "SHOW_ENIGME", "Boîte fermée par un code"));
        im.addQR("Noix", new Interaction( "ADD_GOB", "Bol de noix"));
        im.addQR("Feu", new Interaction( "ADD_GOB", "Feu de bois"));
        im.addQR("Mirroir", new Interaction( "ADD_GOB", "Miroir"));

        //im.addQR("Jackpot", new Interaction("ADD_GOB","Poudrier"));
        im.addQR("Jackpot", new Interaction( "SHOW_ENIGME", "Boîte fermée par un code"));
        im.addQR("Jackpot", new Interaction( "SHOW_ENIGME", "Poudrier"));
        //im.addQR("Poudrier", new Interaction("ADD_GOB", "Poudrier"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Ciseaux"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Veste"));
        //im.addQR("Jackpot", new Interaction("ADD_GOB", ""))
        im.addQR("Jackpot", new Interaction("ADD_GOB","Encyclopedie"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Poster dessiné"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Petit poster "));
        //im.addQR("Jackpot", new Interaction("ADD_GOB","Boîte"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Bol de noix"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Bol d'eau"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Feu de bois"));
        im.addQR("Jackpot", new Interaction("ADD_GOB","Miroir"));

        GameState.getGameState().remainingQR = 11; //gob+enigmes


        im.addCombi("Clé", "Armoire encastrée dans le mur", new Interaction("REMOVE_GOB", "Armoire encastrée dans le mur"));
        im.addCombi("Clé", "Armoire encastrée dans le mur", new Interaction("REMOVE_GOB", "Clé"));
        im.addCombi("Clé", "Armoire encastrée dans le mur", new Interaction("REMOVE_GOB", "Ciseaux"));
        im.addCombi("Clé", "Armoire encastrée dans le mur", new Interaction("ADD_GOB", "Parfums"));


        im.addCombi("Ciseaux","Poster dessiné", new Interaction("ADD_GOB", "Armoire encastrée dans le mur"));
        im.addCombi("Ciseaux","Poster dessiné", new Interaction("REMOVE_GOB", "Poster dessiné"));
        im.addCombi("Ciseaux","Poster dessiné", new Interaction("LAUNCH_POPUP"," Vous découpez le poster, derrière vous découvrez une armoire encastrée dans un mur. Malheureusement, elle est fermée à clé"));

        im.addCombi("Encyclopedie","Bol de noix", new Interaction("ADD_GOB", "Morceau de papier")); //ch1_vase+bol =boule transparente
        im.addCombi("Encyclopedie","Bol de noix", new Interaction("LAUNCH_POPUP", "Vous écrasez les noix avec l'encyclopédie. A l'intérieur de l'une d'entre elles se trouve un morceau de papier."));
        im.addCombi("Encyclopedie","Bol de noix", new Interaction("REMOVE_GOB", "Encyclopedie"));
        im.addCombi("Encyclopedie","Bol de noix", new Interaction("REMOVE_GOB", "Bol de noix"));
        im.addCombi("Encyclopedie","Bol de noix", new Interaction("REMOVE_GOB", "Petit poster "));

        im.addCombi("Ciseaux","Veste", new Interaction("ADD_GOB", "Clé"));
        im.addCombi("Ciseaux","Veste", new Interaction("REMOVE_GOB", "Veste"));
        im.addCombi("Ciseaux","Veste", new Interaction("LAUNCH_POPUP"," Vous découpez la doublure de la veste. Vous y trouvez une clé !"));


        /*im.addCombi("Bol d'eau","Feu de bois", new Interaction("ADD_GOB", "Mystérieuses inscriptions"));
        im.addCombi("Bol d'eau","Feu de bois", new Interaction("REMOVE_GOB", "Feu de bois"));
        im.addCombi("Bol d'eau","Feu de bois", new Interaction("REMOVE_GOB", "Bol d'eau"));
        im.addCombi("Bol d'eau","Feu de bois", new Interaction("REMOVE_GOB", "Miroir"));
        im.addCombi("Bol d'eau","Feu de bois", new Interaction("LAUNCH_POPUP","E        n versant de l'eau sur le feu, de la vapeur révèle un dessin sur le Miroir"));*/


        im.addCombi("Bol d'eau","Feu de bois", new Interaction("ADD_GOB", "Bol d'eau bouillante"));
        im.addCombi("Bol d'eau","Feu de bois", new Interaction("REMOVE_GOB", "Feu de bois"));
        im.addCombi("Bol d'eau","Feu de bois", new Interaction("REMOVE_GOB", "Bol d'eau"));
        im.addCombi("Bol d'eau","Feu de bois", new Interaction("LAUNCH_POPUP","Vous faites bouillir l'eau sur le feu"));
        //im.addCombi("Bol d'eau","Feu de bois", new Interaction("REMOVE_GOB", "Miroir"));
        //im.addCombi("Bol d'eau","Feu de bois", new Interaction("ADD_GOB", "Mystérieuses inscriptions"));
        //im.addCombi("Bol d'eau","Feu de bois", new Interaction("REMOVE_GOB", "Miroir"));


        im.addCombi("Bol d'eau bouillante","Miroir", new Interaction("ADD_GOB", "Mystérieuses inscriptions"));
        im.addCombi("Bol d'eau bouillante","Miroir", new Interaction("REMOVE_GOB", "Miroir"));
        im.addCombi("Bol d'eau bouillante","Miroir", new Interaction("REMOVE_GOB", "Bol d'eau bouillante"));
        im.addCombi("Bol d'eau bouillante","Miroir", new Interaction("LAUNCH_POPUP","Vous approchez le bol du miroir, la vapeur révèle un dessin sur le miroir. Vous le transcrivez sur une feuille."));




        // ENIGME WIN
        im.addEnigmeWIN("Boîte fermée par un code",new Interaction("ADD_GOB", "Dragées"));
        //im.addEnigmeWIN("Boîte fermée par un code",new Interaction("REMOVE_GOB", "Boîte"));
        im.addEnigmeWIN("Boîte fermée par un code",new Interaction("REMOVE_GOB", "Mystérieuses inscriptions"));
        im.addEnigmeWIN("Boîte fermée par un code",new Interaction("REMOVE_GOB", "Morceau de papier"));
        im.addEnigmeWIN("Boîte fermée par un code",new Interaction("LAUNCH_POPUP_ENIGME", "A l'intérieur de la boîte, vous trouvez quelques dragées"));
        im.addEnigmeWIN("Boîte fermée par un code", new Interaction("HIDE_ENIGME", "Boîte fermée par un code"));



        im.addEnigmeWIN("Poudrier",new Interaction("WIN",null)); //Armoir +code

        // ENIGME LOSE
        im.addEnigmeLOSE("Boîte fermée par un code", new Interaction("PENALITE", "60"));
        im.addEnigmeLOSE("Poudrier", new Interaction("PENALITE", "240"));

        //im.addTimeOut(new Interaction("launch activity", "LooseScreen.class"));
        im.addTimeOut(new Interaction("GAMEOVER",null));

        GameState.getGameState().addIM(im);

    }

    //Boîte avec code  10
    //postercasse noissette
    // poster belle aux bois dorman
    //Encyclopediepedie de G.
    // Bol de noix
    // Papier  code + X
    // Veste
    // Mirroir
    // Feu de bois
    // Dragee
    // Clé
    //Ciseaux
    //bol eau
    //pourdrier
    //Armoire à parfums
    //ch1_armoire
    //Parfums
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
