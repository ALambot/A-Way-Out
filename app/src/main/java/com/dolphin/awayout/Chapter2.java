package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by antoine on 27.03.18.
 */

public class Chapter2 implements Chapter {

    private String title;
    private String description;
    private String location;
    private int imageID;
    private int duration;

    public Chapter2() {
        this.title = "Chapitre 2 : \n Besoin de Preuves";
        this.description = "Vous avez besoin de preuves pour étayer vos allégations.\n" +
                "Vous fouillez le bureau de Lady Doubtshire, à la recherche d'un quelconque objet ou document pouvant fournir un mobile de meutre. \n \n" +
                "Vous feriez mieux de vous dépêcher avant que toute preuve ne disparaisse !\n";
        this.location = "Réaumur : Siemens";
        this.imageID = R.drawable.chap1;
        this.duration = 2700;
    }

    public void load(){

        // GameObjects
        ArrayList<GameObject> gobs = new ArrayList<GameObject>();

        //gobs.add(new GameObject("cle", "Ceci est une clé", R.drawable.key_demo));
        gobs.add(new GameObject("miroir", "Un ancien miroir posé sur la cheminée", R.drawable.ch1_mirror));
        gobs.add(new GameObject("vase", "Une vase avec des fleurs fraîches", R.drawable.ch1_vase));
        gobs.add(new GameObject("bol vide", "Un ancien pot de chambre", R.drawable.ch1_chamber_pot));
        gobs.add(new GameObject("clou", "Un clou rouillé", R.drawable.ch1_nail));
        gobs.add(new GameObject("statue", "Une délicate statue posée sur le bureau", R.drawable.ch1_statue));
        gobs.add(new GameObject("cypherDisc", "Un disque utilisé pour encrypter et décrypter des codes. La partie du milieu est mobile", R.drawable.ch1_outside_cypher_roll));
        gobs.add(new GameObject("Medusa", "Un papier d'une représentation de la Méduse", R.drawable.ch1_medusa_paper));
        gobs.add(new GameObject("Victoiria","Une photo de la reine Victoria", R.drawable.ch1_victoria));
        //gobs.add(new GameObject("armoire", "Une grande armoire avec toutes les lettres engravées. Elle est verrouillée. On peut appuyer sur les lettres.", R.drawable.ch1_armoire));
        gobs.add(new GameObject("Boule transparente", "Une boule de verre transparente. Elle est assez lourde.", R.drawable.ch1_crystal_ball));
        gobs.add(new GameObject("Feuille", "Des chiffres et des flèches sont écrits dessus.", R.drawable.ch1_password_paper));
        gobs.add(new GameObject("tiroir", "Un tiroir fermé. Il manque la poignée !", R.drawable.ch1_tirroir));

        //DEBUG
        gobs.add(new GameObject("le small one", "put me in le big one", R.drawable.ch1_inside_cypher_roll));
        gobs.add(new GameObject("le big one", "no touchy me", R.drawable.ch1_outside_cypher_roll));


        // Enigmes
        ArrayList<EnigmeObject> enigmes = new ArrayList<EnigmeObject>();

        enigmes.add(new EnigmeObject("Armoire mysterieuse",3,"ULPQXTOD"));
        enigmes.add(new EnigmeObject("cypherRoll",2,"Victoria"));


        // Hints
        ArrayList<Hint> hints = new ArrayList<Hint>();

        hints.add(new Hint("Il vous reste encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));
        hints.add(new Hint("Essayez de regarder dans l'eau du vase", new HintFlag[]{new HintFlag("HAS_GOB","vase"),new HintFlag("HAS_GOB","bol vide")}));
        hints.add(new Hint("Y aurait-il un objet qui permettrait d'ouvrir le tiroir ?", new HintFlag[]{new HintFlag("HAS_GOB","tiroir"), new HintFlag("HAS_GOB", "clou")}));
        hints.add(new Hint("Que craint la méduse ?", new HintFlag[]{new HintFlag("HAS_GOB","Medusa"), new HintFlag("HAS_GOB", "miroir")}));

        String looseMessage="Votre temps est écoulé ! Le majordome vous escorte hors de la maison alors qu\'il vous manque des indices. Essayez de repasser plus tard !";
        String winMessage="Dans l\'armoire se trouve la clé qui permet de sortir de la pièce, mais aussi des mystérieux contrats léguant toute la fortune de Lady Doubtshire à ses héritiers ! \n L\'un d\'entre eux en aurait-il eu marre d\'attendre ? Et comment Lady Doubtshire a-t-elle été tuée ? Fouillez la prochaine pièce pour le savoir !";

        GameState.getGameState().init(this.duration,gobs,enigmes,hints,this,looseMessage, winMessage);

        // --------------------------------------

        //Interactions
        InteractionManager im = new InteractionManager(gobs.size());

        // DEBUG
        im.addQR("Pain", new Interaction("PENALITE", "30"));
        im.addQR("Sesame", new Interaction("SHOW_ENIGME", "cypherRoll"));
        im.addQR("Roll", new Interaction("ADD_GOB", "le small one"));
        im.addQR("Roll", new Interaction("ADD_GOB", "le big one"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "miroir"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "vase"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "bol vide"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "tiroir"));
        //im.addQR("Jackpot", new Interaction( "ADD_GOB", "armoire"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "clou"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "statue"));
        im.addQR("Jackpot", new Interaction( "SHOW_ENIGME", "Armoire mysterieuse"));

        for(int ii = 0; ii<6; ii++) {
            im.addCombi("le small one", "le big one", new Interaction("PENALITE", "10"));
        }

        // QR
        //im.addQR("cle", new Interaction("ADD_GOB", "cle"));
        im.addQR("miroir", new Interaction("ADD_GOB", "miroir"));
        im.addQR("vase", new Interaction("ADD_GOB", "vase"));
        im.addQR("bol vide", new Interaction("ADD_GOB", "bol vide"));
        im.addQR("tiroir", new Interaction("ADD_GOB", "tiroir"));
        //im.addQR("armoire", new Interaction("ADD_GOB", "armoire"));
        im.addQR("armoire", new Interaction("SHOW_ENIGME", "Armoire mysterieuse"));
        //im.addQR("armoire", new Interaction("ADD_GOB", "Armoire mysterieuse"));
        im.addQR("clou", new Interaction("ADD_GOB", "clou"));
        im.addQR("statue", new Interaction("ADD_GOB", "statue"));
        GameState.getGameState().remainingQR = 7; //TODO TEMPORARY

        // COMBI
        im.addCombi("vase","bol vide", new Interaction("ADD_GOB", "Boule transparente")); //vase+bol =Boule transparente
        im.addCombi("vase","bol vide", new Interaction("LAUNCH_POPUP", "Vous videz le vase dans le bol. Dans l'eau du vase était cachée une boule transparente."));
        im.addCombi("Boule transparente", "statue", new Interaction("SHOW_ENIGME", "cypherRoll"));  // boule+Statue= cypherKey TODO
        im.addCombi("Boule transparente", "statue", new Interaction("REMOVE_GOB", "statue"));
        im.addCombi("Boule transparente", "statue", new Interaction("REMOVE_GOB", "Boule transparente"));
        im.addCombi("Boule transparente", "statue", new Interaction("LAUNCH_POPUP", "Lorsque vous posez la boule dans la paume de la statue, le socle de celle-ci s'ouvre pour dévoiler un cypher roll. Il est disponible dans vos énigmes"));
        im.addCombi("clou","tiroir", new Interaction("ADD_GOB", "Medusa"));  // Clou+tiroir= photo reine Victoria+photo Medusa
        im.addCombi("clou","tiroir", new Interaction("ADD_GOB", "Victoiria"));  // Clou+tiroir= photo reine Victoria+photo Medusa
        im.addCombi("clou","tiroir", new Interaction("LAUNCH_POPUP", "Vous utilisez le clou comme poignée pour le tiroir. A l'intérieur de celui-ci se trouvent deux photos."));
        im.addCombi("miroir","Medusa", new Interaction("ADD_GOB", "Feuille"));//miroir+Medusa=code TODO
        im.addCombi("Medusa","miroir", new Interaction("ADD_GOB", "Feuille"));
        im.addCombi("Medusa","miroir", new Interaction("REMOVE_GOB", "miroir"));
        im.addCombi("Medusa","miroir", new Interaction("REMOVE_GOB", "Medusa"));
        im.addCombi("Medusa","miroir", new Interaction("LAUNCH_POPUP", "En mettant la photo de la Méduse devant le miroir, elle s'éfface pour laisser apparaître un code !"));

        im.addCombi("vase","bol vide", new Interaction("REMOVE_GOB", "vase")); //vase+bol =Boule transparente
        im.addCombi("vase","bol vide", new Interaction("REMOVE_GOB", "bol vide")); //vase+bol =Boule transparente
        im.addCombi("clou", "tiroir", new Interaction("REMOVE_GOB", "clou"));  // Clou+tiroir= photo reine Victoria+photo Medusa
        im.addCombi("clou","tiroir", new Interaction("REMOVE_GOB", "tiroir"));  // Clou+tiroir= photo reine Victoria+photo Medusa


        // ENIGME WIN
        im.addEnigmeWIN("Armoir",new Interaction("WIN",null)); //Armoir +code

        // ENIGME LOSE
        im.addEnigmeLOSE("Armoir", new Interaction("PENALITE", "180"));

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
