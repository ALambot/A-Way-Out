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
        this.title = "Chapitre 1 : \n Des débuts difficiles";
        this.description = "Lady Doubthshire a été assasinée ! \n Son corps sans vie a été retrouvé ce matin.\n Tous s'interroge sur ce qui a bien pu arriver à une si vertueuse personne.\n " +
                "On a fait appel à vos service pour résoudre cette mystère.\n \n" +
                "Vous feriez mieux de vous depêcher avant que toute preuve ne disparaisse !\n";
        this.location = "Réaumur : salle Intel";
        this.imageID = R.drawable.chap1;
        this.duration = 900;
    }

    public void load(){

        // GameObjects
        ArrayList<GameObject> gobs = new ArrayList<GameObject>();

        gobs.add(new GameObject("cle", "Ceci est une clé", R.drawable.key_demo));
        gobs.add(new GameObject("miroir", "Un ancien mirroir posé sur la cheminée", R.drawable.mirror));
        gobs.add(new GameObject("vase", "Une vase avec des fleures fraiches", R.drawable.vase));
        gobs.add(new GameObject("bol vide", "Un ancien pot de chambre", R.drawable.chamber_pot));
        gobs.add(new GameObject("clou", "Un clou rouillé", R.drawable.nail));
        gobs.add(new GameObject("statue", "Une délicate statue posée sur le bureau", R.drawable.statue));
        gobs.add(new GameObject("cypherDisc", "Un disque utilisé pour encrypter et décrypter des codes. La partie du milieu est mobile", R.drawable.outside_cypher_roll));
        gobs.add(new GameObject("medusa", "Un papier d'une représentation de la Méduse", R.drawable.medusa_paper));
        gobs.add(new GameObject("victoria","Une photo de la reine Victoria", R.drawable.victoria));
        gobs.add(new GameObject("armoire", "Une armoire avec toutes les lettres engravées. Elle est verouillée. On peut appuyer sur les lettres.", R.drawable.armoire));
        gobs.add(new GameObject("boule transparente", "Une boule de verre transparente. Elle est assez lourde.", R.drawable.crystal_ball));
        gobs.add(new GameObject("feuille", "Des chiffres et flèches sont écrits dessus. ", R.drawable.password_paper));
        gobs.add(new GameObject("tiroir", "Un tiroir fermé. Il manque la poignée ! ", R.drawable.tirroir));
        //DEBUG
        gobs.add(new GameObject("le small one", "put me in le big one", R.drawable.inside_cypher_roll));
        gobs.add(new GameObject("le big one", "no touchy me", R.drawable.outside_cypher_roll));


        // Enigmes
        ArrayList<EnigmeObject> enigmes = new ArrayList<EnigmeObject>();

        enigmes.add(new EnigmeObject("Armoire mysterieuse",3,"ULPQXTOD"));
        enigmes.add(new EnigmeObject("cypherRoll",2,"Victoria"));


        // Hints
        ArrayList<Hint> hints = new ArrayList<Hint>();

        hints.add(new Hint("Il vous manque peut-être encore des objets à découvrir...", new HintFlag[]{new HintFlag("QR_REM",null)}));
        hints.add(new Hint("Vazy vide l'eau du vase dans le bol", new HintFlag[]{new HintFlag("HAS_GOB","vase"),new HintFlag("HAS_GOB","bol vide")}));
        hints.add(new Hint("Essayez de regarder dans l'eau du vase", new HintFlag[]{new HintFlag("HAS_GOB","vase"),new HintFlag("HAS_GOB","bol vide")}));
        hints.add(new Hint("Il y aurait-il un object qui permetrait d'ouvrir le tiroir ?", new HintFlag[]{new HintFlag("HAS_GOB","tiroir"), new HintFlag("HAS_GOB", "clou")}));
        hints.add(new Hint("Que crains la méduse ?", new HintFlag[]{new HintFlag("HAS_GOB","medusa"), new HintFlag("HAS_GOB", "miroir")}));

        String looseMessage="Votre temps est écoulé ! Le majordome vous escortes hors de la maison alors qu\'il vous manque des indices. Essayez de repasser  plus tard !";
        String winMessage="Dans l\'armoire se trouve la clé qui permet de sortir de la pièce mais aussi des mysterieux contrats leguant toute la fortune  de Lady Doubthshire à ses héritiers ! \n L\'un d\'entre eux en aurait-il eu marre d\'attendre ? Et comment Lady Doubtshire a-t-elle été tuée ? Fouillez la prochaine pièce pour le savoir !";

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
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "armoire"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "clou"));
        im.addQR("Jackpot", new Interaction( "ADD_GOB", "statue"));
        im.addQR("Jackpot", new Interaction( "SHOW_ENIGME", "Armoire mysterieuse"));

        for(int ii = 0; ii<6; ii++) {
            im.addCombi("le small one", "le big one", new Interaction("PENALITE", "10"));
        }

        // QR
        im.addQR("cle", new Interaction("ADD_GOB", "cle"));
        im.addQR("miroir", new Interaction("ADD_GOB", "miroir"));
        im.addQR("vase", new Interaction("ADD_GOB", "vase"));
        im.addQR("bol vide", new Interaction("ADD_GOB", "bol vide"));
        im.addQR("tiroir", new Interaction("ADD_GOB", "tiroir"));
        im.addQR("armoire", new Interaction("ADD_GOB", "armoire"));
        im.addQR("armoire", new Interaction("SHOW_ENIGME", "Armoire mysterieuse"));
        im.addQR("clou", new Interaction("ADD_GOB", "clou"));
        im.addQR("statue", new Interaction("ADD_GOB", "statue"));
        GameState.getGameState().remainingQR = 7; //TODO TEMPORARY

        // COMBI
        im.addCombi("vase","bol vide", new Interaction("ADD_GOB", "boule transparente")); //vase+bol =boule transparente
        im.addCombi("vase","bol vide", new Interaction("LAUNCH_POPUP", "Vous videz le vase dans le bol. Dans l'eau du vase était cachée une boule transparente."));
        im.addCombi("boule transparente", "statue", new Interaction("SHOW_ENIGME", "cypherRoll"));  // boule+statue= cypherKey TODO
        im.addCombi("boule transparente", "statue", new Interaction("REMOVE_GOB", "statue"));
        im.addCombi("boule transparente", "statue", new Interaction("REMOVE_GOB", "boule transparente"));
        im.addCombi("boule transparente", "statue", new Interaction("LAUNCH_POPUP", "Lorsque vous posez la boule dans la paume de la statue, le socle de celle-ci s'ouvre pour dévoiler un cypher roll. Il est disponible dans vos énigmes"));
        im.addCombi("clou","tiroir", new Interaction("ADD_GOB", "medusa"));  // clou+tiroir= photo reine Victoria+photo medusa
        im.addCombi("clou","tiroir", new Interaction("ADD_GOB", "victoria"));  // clou+tiroir= photo reine Victoria+photo medusa
        im.addCombi("clou","tiroir", new Interaction("LAUNCH_POPUP", "Vous utilisez le clou comme poignée de tiroir. A l'intérieur de celui-ci se trouvent deux photos."));
        im.addCombi("miroir","medusa", new Interaction("ADD_GOB", "feuille"));//miroir+medusa=code TODO
        im.addCombi("medusa","miroir", new Interaction("ADD_GOB", "feuille"));
        im.addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "miroir"));
        im.addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "medusa"));
        im.addCombi("medusa","miroir", new Interaction("LAUNCH_POPUP", "En mettant la photo de la Méduse devant le miroir, elle s'éface pour laisser apparaitre un code !"));

        im.addCombi("vase","bol vide", new Interaction("REMOVE_GOB", "vase")); //vase+bol =boule transparente
        im.addCombi("vase","bol vide", new Interaction("REMOVE_GOB", "bol vide")); //vase+bol =boule transparente
        im.addCombi("clou", "tiroir", new Interaction("REMOVE_GOB", "clou"));  // clou+tiroir= photo reine Victoria+photo medusa
        im.addCombi("clou","tiroir", new Interaction("REMOVE_GOB", "tiroir"));  // clou+tiroir= photo reine Victoria+photo medusa


        // ENIGME WIN
        im.addEnigmeWIN("Armoir",new Interaction("ADD_GOB", "15")); //Armoir +code TODO
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
