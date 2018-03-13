package com.dolphin.awayout;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.util.Log;

/**
 * Created by antoine on 22.02.18.
 */

public class InteractionManager {

    private Interaction[][] combiTable;
    private HashMap<String, Interaction> enigmeWIN;
    private HashMap<String, Interaction> enigmeLOSE;
    private HashMap<String, Interaction> qr;
    private Interaction timeOut;

    public InteractionManager(){
        this.combiTable = new Interaction[20][20];
        this.enigmeWIN = new HashMap<String, Interaction>();
        this.enigmeLOSE = new HashMap<String, Interaction>();
        this.qr = new HashMap<String, Interaction>();
        this.timeOut = null;
    }

    public void init(){
        // Ajouter les Interactions a la main pour le moment

        addQR("Anneau unique", new Interaction("PENALITE", "30")); //pas touche

        addQR("clé", new Interaction("ADD_GOB", "cle"));
        addQR("miroir", new Interaction("ADD_GOB", "miroir"));
        addQR("vase", new Interaction("ADD_GOB", "vase"));
        addQR("bol vide", new Interaction("ADD_GOB", "bol"));
        addQR("tiroir", new Interaction("ADD_GOB", "tiroir"));
        addQR("armoire", new Interaction("ADD_GOB", "armoire"));
        addQR("clou", new Interaction("ADD_GOB", "clou"));
        addQR("statue", new Interaction("ADD_GOB", "statue"));

        addCombi("vase","bol", new Interaction("ADD_GOB", "boule transparente")); //vase+bol =boule transparente
        addCombi("boule transparente", "statue", new Interaction("UNLOCK_ENIGME", "?"));  // boule+statue= cypherKey TODO
        addCombi("clou","tiroir", new Interaction("ADD_GOB", "medusa"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("clou","tiroir", new Interaction("ADD_GOB", "victoria"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("miroir","medusa", new Interaction("ADD_GOB", "14"));//miroir+medusa=code TODO
        addCombi("vase","bol", new Interaction("REMOVE_GOB", "vase")); //vase+bol =boule transparente
        addCombi("vase","bol", new Interaction("REMOVE_GOB", "bol")); //vase+bol =boule transparente
        addCombi("clou", "tiroir", new Interaction("REMOVE_GOB", "clou"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("clou","tiroir", new Interaction("REMOVE_GOB", "tiroir"));  // clou+tiroir= photo reine Victoria+photo medusa

        addEnigmeWIN("Armoir",new Interaction("ADD_GOB", "15")); //Armoir +code TODO
        addEnigmeWIN("Armoir",new Interaction("WIN",null)); //Armoir +code

        addEnigmeLOSE("Armoir", new Interaction("PENALITE", "180"));

        addTimeOut(new Interaction("launch activity", "loose_screen.class"));

    }

    private void addCombi(String name1, String name2, Interaction interaction){
        GameObject gob1 = GameState.getGameState().getObjectByName(name1);
        GameObject gob2 = GameState.getGameState().getObjectByName(name2);
        int ID1 = gob1.getID();
        int ID2 = gob2.getID();
        Interaction ir = combiTable[ID1][ID2];
        if(ir == null){
            combiTable[ID1][ID2] = interaction;
        }
        else{
            ir.nextInteraction = interaction;
        }
    }

    private void addEnigmeWIN(String enigme, Interaction interaction){
        Interaction ir = enigmeWIN.get(enigme);
        if(ir == null){
            enigmeWIN.put(enigme, interaction);
        }
        else{
            ir.nextInteraction = interaction;
        }
    }

    private void addEnigmeLOSE(String enigme, Interaction interaction){
        Interaction ir = enigmeLOSE.get(enigme);
        if(ir == null){
            enigmeLOSE.put(enigme, interaction);
        }
        else{
            ir.nextInteraction = interaction;
        }
    }

    private void addQR(String str, Interaction interaction){
        Interaction ir = this.qr.get(str);
        if(ir == null){
            qr.put(str, interaction);
        }
        else{
            ir.nextInteraction = interaction;
        }
    }

    private void addTimeOut(Interaction interaction){
        Interaction ir = this.timeOut;
        if(ir == null){
            timeOut = interaction;
        }
        else{
            ir.nextInteraction = interaction;
        }
    }

    public void combine(GameObject obj1, GameObject obj2){
        combiTable[obj1.getID()][obj2.getID()].run();
    }

    public void enigmeSuccess(String enigme){
        enigmeWIN.get(enigme).run();
    }

    public void enigmeFail(String enigme){
        enigmeLOSE.get(enigme).run();
    }

    public void QRresult(String result){
        Interaction ir = qr.get(result);
        if(ir != null){
            ir.run();
        }
    }

    public void timeOut(){
        if(this.timeOut != null){
            this.timeOut.run();
        }
    }


    public class Interaction {

        public final String action; // ADD_GOB REMOVE_GOB LOCK_ENIGME UNLOCK_ENIGME HIDE_ENIGME SHOW_ENIGME GAMEOVER WIN PENALITE
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
            else if(this.action.equals("LOCK_ENIGME")){
                // ...
            }
            else if(this.action.equals("UNLOCK_ENIGME")){
                // ...
            }
            else if(this.action.equals("HIDE_ENIGME")){
                // ...
            }
            else if(this.action.equals("SHOW_ENIGME")){
                // ...
            }
            else if(this.action.equals("GAMEOVER")){
                // TODO
            }
            else if(this.action.equals("WIN")){
                // TODO
            }
            else if(this.action.equals("PENALITE")){
                int penne = Integer.parseInt(this.arg);
                GameState.getGameState().penalize(penne);
            }
            else if(this.action.equals("launch activity")){
                Intent intent=new Intent(GameState.getGameState().ctx, loose_screen.class);
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
