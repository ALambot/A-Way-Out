package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by antoine on 27.03.18.
 */

public class  Chapter1 implements Chapter {

    private String title;
    private String description;
    private String location;
    private int imageID;
    private int duration;

    public Chapter1() {
        this.title = "Chapitre 1 : \n A la recherche de preuves";
        this.description = "Vous avez besoin de preuves pour étailler vos allégation.\n" +
                "Vous fouiller le bureau de Lady Doubthshire, à la recherche d'un quelconque objet ou document pouvant fournir un mobile de meutre. \n \n" +
                "Vous feriez mieux de vous depêcher avant que toute preuve ne disparaisse !\n";
        this.location = "Réaumur : salle Intel";
        this.imageID = R.drawable.chap1;
        this.duration = 1800;
    }

    public void load(){

        // GameObjects
        ArrayList<GameObject> gobs = new ArrayList<GameObject>();

        //gobs.add(new GameObject("cle", "Ceci est une clé", R.drawable.key_demo));
        gobs.add(new GameObject("miroir", "Un ancien mirroir posé sur la cheminée", R.drawable.ch1_mirror));
        gobs.add(new GameObject("ch1_vase", "Une ch1_vase avec des fleures fraiches", R.drawable.ch1_vase));
        gobs.add(new GameObject("bol vide", "Un ancien pot de chambre", R.drawable.ch1_chamber_pot));
        gobs.add(new GameObject("clou", "Un clou rouillé", R.drawable.ch1_nail));
        gobs.add(new GameObject("ch1_statue", "Une délicate ch1_statue posée sur le bureau", R.drawable.ch1_statue));
        gobs.add(new GameObject("cypherDisc", "Un disque utilisé pour encrypter et décrypter des codes. La partie du milieu est mobile", R.drawable.ch1_outside_cypher_roll));
        gobs.add(new GameObject("medusa", "Un papier d'une représentation de la Méduse", R.drawable.ch1_medusa_paper));
        gobs.add(new GameObject("ch1_victoria","Une photo de la reine Victoria", R.drawable.ch1_victoria));
        gobs.add(new GameObject("ch1_armoire", "Une ch1_armoire avec toutes les lettres engravées. Elle est verouillée. On peut appuyer sur les lettres.", R.drawable.ch1_armoire));
        gobs.add(new GameObject("boule transparente", "Une boule de verre transparente. Elle est assez lourde.", R.drawable.ch1_crystal_ball));
        gobs.add(new GameObject("feuille", "Des chiffres et flèches sont écrits dessus. ", R.drawable.ch1_password_paper));
        gobs.add(new GameObject("tiroir", "Un tiroir fermé. Il manque la poignée ! ", R.drawable.ch1_tirroir));
        //DEBUG
        gobs.add(new GameObject("le small one", "put me in le big one", R.drawable.ch1_inside_cypher_roll));
        gobs.add(new GameObject("le big one", "no touchy me", R.drawable.ch1_outside_cypher_roll));


        // Enigmes
        ArrayList<EnigmeObject> enigmes = new ArrayList<EnigmeObject>();

        enigmes.add(new EnigmeObject("Armoire mysterieuse",3,"ULPQXTOD"));
        enigmes.add(new EnigmeObject("cypherRoll",2,"Victoria"));


        // Hints
        ArrayList<Hint> hints = new ArrayList<Hint>();

        hints.add(new Hint("Il vous manque peut-être encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));
        hints.add(new Hint("Essayez de regarder dans l'eau du ch1_vase", new HintFlag[]{new HintFlag("HAS_GOB","ch1_vase"),new HintFlag("HAS_GOB","bol vide")}));
        hints.add(new Hint("Il y aurait-il un object qui permetrait d'ouvrir le tiroir ?", new HintFlag[]{new HintFlag("HAS_GOB","tiroir"), new HintFlag("HAS_GOB", "clou")}));
        hints.add(new Hint("Que crains la méduse ?", new HintFlag[]{new HintFlag("HAS_GOB","medusa"), new HintFlag("HAS_GOB", "miroir")}));

        String looseMessage="Votre temps est écoulé ! Le majordome vous escortes hors de la maison alors qu\'il vous manque des indices. Essayez de repasser  plus tard !";
        String winMessage="Dans l\'ch1_armoire se trouve la clé qui permet de sortir de la pièce mais aussi des mysterieux contrats leguant toute la fortune  de Lady Doubthshire à ses héritiers ! \n L\'un d\'entre eux en aurait-il eu marre d\'attendre ? Et comment Lady Doubtshire a-t-elle été tuée ? Fouillez la prochaine pièce pour le savoir !";

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
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "ch1_vase"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "bol vide"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "tiroir"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "ch1_armoire"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "clou"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "ch1_statue"));
        im.addQR("Jackpot", new Interaction( "SHOW_ENIGME", "Armoire mysterieuse"));

        for(int ii = 0; ii<6; ii++) {
            im.addCombi("le small one", "le big one", new Interaction("PENALITE", "10"));
        }

        // QR
        //im.addQR("cle", new Interaction("ADD_GOB", "cle"));
        im.addQR("miroir", new Interaction("ADD_GOB", "miroir"));
        im.addQR("ch1_vase", new Interaction("ADD_GOB", "ch1_vase"));
        im.addQR("bol vide", new Interaction("ADD_GOB", "bol vide"));
        im.addQR("tiroir", new Interaction("ADD_GOB", "tiroir"));
        im.addQR("ch1_armoire", new Interaction("ADD_GOB", "ch1_armoire"));
        im.addQR("ch1_armoire", new Interaction("SHOW_ENIGME", "Armoire mysterieuse"));
        im.addQR("clou", new Interaction("ADD_GOB", "clou"));
        im.addQR("ch1_statue", new Interaction("ADD_GOB", "ch1_statue"));
        GameState.getGameState().remainingQR = 7; //TODO TEMPORARY

        // COMBI
        im.addCombi("ch1_vase","bol vide", new Interaction("ADD_GOB", "boule transparente")); //ch1_vase+bol =boule transparente
        im.addCombi("ch1_vase","bol vide", new Interaction("LAUNCH_POPUP", "Vous videz le ch1_vase dans le bol. Dans l'eau du ch1_vase était cachée une boule transparente."));
        im.addCombi("boule transparente", "ch1_statue", new Interaction("SHOW_ENIGME", "cypherRoll"));  // boule+ch1_statue= cypherKey TODO
        im.addCombi("boule transparente", "ch1_statue", new Interaction("REMOVE_GOB", "ch1_statue"));
        im.addCombi("boule transparente", "ch1_statue", new Interaction("REMOVE_GOB", "boule transparente"));
        im.addCombi("boule transparente", "ch1_statue", new Interaction("LAUNCH_POPUP", "Lorsque vous posez la boule dans la paume de la ch1_statue, le socle de celle-ci s'ouvre pour dévoiler un cypher roll. Il est disponible dans vos énigmes"));
        im.addCombi("clou","tiroir", new Interaction("ADD_GOB", "medusa"));  // clou+tiroir= photo reine Victoria+photo medusa
        im.addCombi("clou","tiroir", new Interaction("ADD_GOB", "ch1_victoria"));  // clou+tiroir= photo reine Victoria+photo medusa
        im.addCombi("clou","tiroir", new Interaction("LAUNCH_POPUP", "Vous utilisez le clou comme poignée de tiroir. A l'intérieur de celui-ci se trouvent deux photos."));
        im.addCombi("miroir","medusa", new Interaction("ADD_GOB", "feuille"));//miroir+medusa=code TODO
        im.addCombi("medusa","miroir", new Interaction("ADD_GOB", "feuille"));
        im.addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "miroir"));
        im.addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "medusa"));
        im.addCombi("medusa","miroir", new Interaction("LAUNCH_POPUP", "En mettant la photo de la Méduse devant le miroir, elle s'éface pour laisser apparaitre un code !"));

        im.addCombi("ch1_vase","bol vide", new Interaction("REMOVE_GOB", "ch1_vase")); //ch1_vase+bol =boule transparente
        im.addCombi("ch1_vase","bol vide", new Interaction("REMOVE_GOB", "bol vide")); //ch1_vase+bol =boule transparente
        im.addCombi("clou", "tiroir", new Interaction("REMOVE_GOB", "clou"));  // clou+tiroir= photo reine Victoria+photo medusa
        im.addCombi("clou","tiroir", new Interaction("REMOVE_GOB", "tiroir"));  // clou+tiroir= photo reine Victoria+photo medusa


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
