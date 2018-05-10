package com.dolphin.awayout;

import java.util.ArrayList;

/**
 * Created by antoine on 08.05.18.
 */

public class ChapterDemo implements Chapter {

    private String title;
    private String description;
    private String location;
    private int imageID;
    private int duration;

    public ChapterDemo() {
        this.title = "Démo P4 :\nUn défi aux assistants";
        this.description = "Vous profitiez d'un peu de temps libre dans le local quand on vous y a enfermé. " +
                "Malheureusement, vous avez cours dans 5 minutes, trouverez-vous un moyen de sortir de la pièce à temps ?";
        this.location = "QG du Baron \nMcLainez";
        this.imageID = R.drawable.gold;
        this.duration = 300;
    }

    public void load(){

        // GameObjects
        ArrayList<GameObject> gobs = new ArrayList<GameObject>();

        gobs.add(new GameObject( "Coffre", "Un solide coffre en bois. Qui sait ce qu'il renferme ?", R.drawable.gold));
        gobs.add(new GameObject( "Pied de biche", "Pour forcer une porte. Ou un bocal de mayonnaise particulièrement coriace.", R.drawable.gold));
        gobs.add(new GameObject( "Scie", "Ne sert plus à rien maintenant que tout le monde achète ses meubles à Ikea", R.drawable.gold));
        gobs.add(new GameObject( "Marteau", "Un simple marteau", R.drawable.gold));
        gobs.add(new GameObject( "Clé", "Elle était juste sous vos yeux", R.drawable.gold));

        // Enigmes
        ArrayList<EnigmeObject> enigmes = new ArrayList<EnigmeObject>();

        enigmes.add(new EnigmeObject("Montre gousset", 5, null));

        // Hints
        ArrayList<Hint> hints = new ArrayList<Hint>();

        hints.add(new Hint(
                "Aucun de ces objets ne semble utile pour ouvrir le coffre... Cherchez encore...",
                new HintFlag[]{
                        new HintFlag("HAS_GOB","Pied de biche"),
                        new HintFlag("HAS_GOB","Scie"),
                        new HintFlag("HAS_GOB","Marteau")
                }));

        GameState.getGameState().init(duration,gobs,enigmes,hints,this,"","");

        // --------------------------------------

        //Interactions
        InteractionManager im = new InteractionManager(gobs.size());

        im.addQR("Pied de biche", new Interaction("ADD_GOB", "Pied de biche"));
        im.addQR("Scie", new Interaction("ADD_GOB", "Scie"));
        im.addQR("Marteau", new Interaction( "ADD_GOB", "Marteau"));
        im.addQR("Clé", new Interaction( "ADD_GOB", "Clé"));

        im.addCombi("Clé", "Coffre", new Interaction("SHOW_ENIGME", "Montre gousset"));
        im.addCombi("Clé", "Coffre", new Interaction("REMOVE_GOB", "Coffre"));
        im.addCombi("Clé", "Coffre", new Interaction("REMOVE_GOB", "Clé"));
        im.addCombi("Clé", "Coffre", new Interaction("REMOVE_GOB", "Pied de biche"));
        im.addCombi("Clé", "Coffre", new Interaction("REMOVE_GOB", "Scie"));
        im.addCombi("Clé", "Coffre", new Interaction("REMOVE_GOB", "Marteau"));

        im.addStart(new Interaction("ADD_GOB", "Coffre"));
        im.addTimeOut(new Interaction("GAMEOVER",null));

        im.addEnigmeWIN("Montre gousset", new Interaction("WIN",null));

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
