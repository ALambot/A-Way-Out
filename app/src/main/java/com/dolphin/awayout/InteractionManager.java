package com.dolphin.awayout;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.widget.Toast;

/**
 * Created by antoine on 22.02.18.
 */

public class InteractionManager {

    private Interaction start;
    private Interaction[][] combiTable;
    private HashMap<String, Interaction> enigmeWIN;
    private HashMap<String, Interaction> enigmeLOSE;
    private HashMap<String, Interaction> qr;
    private Interaction timeOut;

    public InteractionManager(){
        this.start = null;
        this.combiTable = new Interaction[40][40];
        this.enigmeWIN = new HashMap<String, Interaction>();
        this.enigmeLOSE = new HashMap<String, Interaction>();
        this.qr = new HashMap<String, Interaction>();
        this.timeOut = null;
    }

    public void init(){
        // Ajouter les Interactions a la main pour le moment

        // DEBUG - pas touche
        addQR("Anneau unique", new Interaction("PENALITE", "30"));
        addQR("Sesame", new Interaction("SHOW_ENIGME", "cypherRoll"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "cle"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "miroir"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "vase"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "bol"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "tiroir"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "armoire"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "clou"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "statue"));
        addQR("Jackpot", new Interaction( "SHOW_ENIGME", "Armoire mysterieuse"));

        // Interactions lancees au debut
        //addStart(new Interaction("ADD_GOB","cle"));

        // QR
        addQR("clé", new Interaction("ADD_GOB", "cle"));
        addQR("miroir", new Interaction("ADD_GOB", "miroir"));
        addQR("vase", new Interaction("ADD_GOB", "vase"));
        addQR("bol vide", new Interaction("ADD_GOB", "bol vide"));
        addQR("tiroir", new Interaction("ADD_GOB", "tiroir"));
        addQR("armoire", new Interaction("ADD_GOB", "armoire"));
        addQR("armoire", new Interaction("SHOW_ENIGME", "Armoire mysterieuse"));
        addQR("clou", new Interaction("ADD_GOB", "clou"));
        addQR("statue", new Interaction("ADD_GOB", "statue"));

        // COMBI
        addCombi("vase","bol vide", new Interaction("ADD_GOB", "boule transparente")); //vase+bol =boule transparente
        addCombi("boule transparente", "statue", new Interaction("SHOW_ENIGME", "cypherRoll"));  // boule+statue= cypherKey TODO
        addCombi("boule transparente", "statue", new Interaction("REMOVE_GOB", "statue"));
        addCombi("boule transparente", "statue", new Interaction("REMOVE_GOB", "boule transparente"));
        addCombi("clou","tiroir", new Interaction("ADD_GOB", "medusa"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("clou","tiroir", new Interaction("ADD_GOB", "victoria"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("miroir","medusa", new Interaction("ADD_GOB", "feuille"));//miroir+medusa=code TODO
        addCombi("medusa","miroir", new Interaction("ADD_GOB", "feuille"));
        addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "miroir"));
        addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "medusa"));

        addCombi("vase","bol vide", new Interaction("REMOVE_GOB", "vase")); //vase+bol =boule transparente
        addCombi("vase","bol vide", new Interaction("REMOVE_GOB", "bol vide")); //vase+bol =boule transparente
        addCombi("clou", "tiroir", new Interaction("REMOVE_GOB", "clou"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("clou","tiroir", new Interaction("REMOVE_GOB", "tiroir"));  // clou+tiroir= photo reine Victoria+photo medusa


        // ENIGME WIN
        addEnigmeWIN("Armoir",new Interaction("ADD_GOB", "15")); //Armoir +code TODO
        addEnigmeWIN("Armoir",new Interaction("WIN",null)); //Armoir +code

        // ENIGME LOSE
        addEnigmeLOSE("Armoir", new Interaction("PENALITE", "180"));

        addTimeOut(new Interaction("launch activity", "LooseScreen.class"));
        addTimeOut(new Interaction("GAMEOVER",null));

    }

    // ADDERS ------

    private void addStart(Interaction interaction){
        Interaction ir = this.start;
        interaction.nextInteraction = ir;
        this.start = interaction;
    }

    private void addCombi(String name1, String name2, Interaction interaction){
        GameObject gob1 = GameState.getGameState().getObjectByName(name1);
        GameObject gob2 = GameState.getGameState().getObjectByName(name2);
        int ID1 = gob1.getID();
        int ID2 = gob2.getID();

        Interaction ir = this.combiTable[ID1][ID2];
        interaction.nextInteraction = ir;
        this.combiTable[ID1][ID2] = interaction;
    }

    private void addEnigmeWIN(String enigme, Interaction interaction){
        Interaction ir = this.enigmeWIN.get(enigme);
        interaction.nextInteraction = ir;
        this.enigmeWIN.put(enigme, interaction);
    }

    private void addEnigmeLOSE(String enigme, Interaction interaction){
        Interaction ir = this.enigmeLOSE.get(enigme);
        interaction.nextInteraction = ir;
        this.enigmeLOSE.put(enigme, interaction);
    }

    private void addQR(String str, Interaction interaction){
        Interaction ir = this.qr.get(str);
        interaction.nextInteraction = ir;
        this.qr.put(str,interaction);
    }

    private void addTimeOut(Interaction interaction){
        Interaction ir = this.timeOut;
        interaction.nextInteraction = ir;
        this.timeOut = interaction;
    }

    // ACTIONS -----

    public Interaction start() {
        if(this.start != null){
            this.start.run();
        }
        return this.start;
    }

    public Interaction combine(GameObject obj1, GameObject obj2){
        Interaction inter=combiTable[obj1.getID()][obj2.getID()];
         if (inter != null) {
             inter.run();
             return inter;
         }
         else{
             Toast.makeText(GameState.getGameState().ctx, "Rien ne se passe", Toast.LENGTH_SHORT).show();
             return null;
         }
    }

    public void enigmeSuccess(String enigme){
        enigmeWIN.get(enigme).run();
    }

    public void enigmeFail(String enigme){
        enigmeLOSE.get(enigme).run();
    }

    public boolean QRresult(String result){
        Interaction ir = qr.get(result);

        if (!GameState.getGameState().getObjectByName(result).isFound()) {
            if (ir != null) {
                GameState.getGameState().getObjectByName(result).setFound();
                ir.run();
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidQR(String result){
        return (qr.get(result) != null);
    }

    public void timeOut(){
        if(this.timeOut != null){
            this.timeOut.run();
        }
    }

    // INTERACTION ------

    public class Interaction {

        public final String action; // ADD_GOB REMOVE_GOB SOLVE_ENIGME HIDE_ENIGME SHOW_ENIGME GAMEOVER WIN PENALITE
        public final String arg;

        public Interaction nextInteraction;

        public Interaction(String action, String arg){
            this.action = action;
            this.arg = arg;
            this.nextInteraction = null;
        }

        public Interaction(String action){
            this.action = action;
            this.arg = null;
            this.nextInteraction = null;
        }

        public void run(){

            if(this.action.equals("ADD_GOB")){
                GameState.getGameState().getObjectByName(this.arg).activate();
            }
            else if(this.action.equals("REMOVE_GOB")){
                GameState.getGameState().getObjectByName(this.arg).deactivate();
            }
            else if(this.action.equals("SOLVE_ENIGME")){
                GameState.getGameState().getEnigmeByTitle(this.arg).solve();
            }
            else if(this.action.equals("HIDE_ENIGME")){
                GameState.getGameState().getEnigmeByTitle(this.arg).setVisible(false);
            }
            else if(this.action.equals("SHOW_ENIGME")){
                GameState.getGameState().getEnigmeByTitle(this.arg).setVisible(true);
                Toast.makeText(GameState.getGameState().ctx, "Vous avez débloqué une énigme !", Toast.LENGTH_SHORT).show();


            }
            else if(this.action.equals("GAMEOVER")){
                GameState.getGameState().finishTimer();
            }
            else if(this.action.equals("WIN")){
                GameState.getGameState().finishTimer();
            }
            else if(this.action.equals("PENALITE")){
                int penne = Integer.parseInt(this.arg);
                GameState.getGameState().penalize(penne);
            }
            else if(this.action.equals("launch activity")){
                Intent intent=new Intent(GameState.getGameState().ctx, LooseScreen.class);
                GameState.getGameState().ctx.startActivity(intent);

            }

            if(this.nextInteraction != null){
                this.nextInteraction.run();
            }
        }

        public String toString(){
            String ret = this.action + " " +this.arg;
            if(this.nextInteraction != null){
                ret = ret+this.nextInteraction.toString();
            }
            return ret;
        }
    }

}
