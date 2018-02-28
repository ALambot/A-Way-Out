package com.dolphin.awayout;

import java.util.ArrayList;
import java.util.HashMap;
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
        this.combiTable = new Interaction[10][10];
        this.enigmeWIN = new HashMap<String, Interaction>(0);
        this.enigmeLOSE = new HashMap<String, Interaction>(0);
        this.qr = new HashMap<String, Interaction>(1);
    }

    public void init(){
        // Ajouter les Interactions a la main pour le moment
        this.qr.put("Anneau unique", new Interaction("GAMEOVER",null));
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
        qr.get(result).run();
    }

    public void timeOut(){
        if(this.timeOut != null){
            this.timeOut.run();
        }
    }


    public class Interaction {

        public final String action; // ADD_GOB REMOVE_GOB LOCK_ENIGME UNLOCK_ENIGME HIDE_ENIGME SHOW_ENIGME GAMEOVER WIN
        public final String arg;

        public Interaction nextInteraction;

        public Interaction(String action, String arg){
            this.action = action;
            this.arg = arg;
            this.nextInteraction = null;
        }

        public void run(){
            if(this.action.equals("ADD_GOB")){
                ArrayList<GameObject> ar = GameState.getGameState().getGobs();
                GameObject gob = ar.get(ar.indexOf(new GameObject(this.arg)));
                gob.activate();
            }
            else if(this.action.equals("REMOVE_GOB")){
                ArrayList<GameObject> ar = GameState.getGameState().getGobs();
                GameObject gob = ar.get(ar.indexOf(new GameObject(this.arg)));
                gob.deactivate();
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
                // ...
            }
            else if(this.action.equals("WIN")){
                // ...
            }

            if(this.nextInteraction != null){
                this.nextInteraction.run();
            }
        }
    }

}
