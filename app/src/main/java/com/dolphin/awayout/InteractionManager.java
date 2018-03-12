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
        this.combiTable = new Interaction[20][20];
        this.enigmeWIN = new HashMap<String, Interaction>(0);
        this.enigmeLOSE = new HashMap<String, Interaction>(0);
        this.qr = new HashMap<String, Interaction>();
        this.timeOut = null;
    }

    public void init(){
        // Ajouter les Interactions a la main pour le moment
        addQR("Anneau unique", new Interaction("GAMEOVER", null));
        this.qr.put("clé", new Interaction("ADD_GOB", "1"));
        this.qr.put("mirroir", new Interaction("ADD_GOB", "5"));
        this.qr.put("vase", new Interaction("ADD_GOB", "6"));
        this.qr.put("bol vide", new Interaction("ADD_GOB", "7"));
        this.qr.put("tiroir", new Interaction("ADD_GOB", "16"));
        this.qr.put("armoire", new Interaction("ADD_GOB", "13"));
        this.qr.put("clou", new Interaction("ADD_GOB", "8"));
        this.qr.put("statue", new Interaction("ADD_GOB", "9"));
        this.combiTable[2][6] = new Interaction("ADD_GOB", "14"); //vase+bol =boule transparente
        this.combiTable[14][9] = new Interaction("UNLOCK_ENIGME", "?");  // boule+statue= cypherKey
        this.combiTable[8][16] = new Interaction("ADD_GOB", "11");  // clou+tiroir= photo reine Victoria+photo medusa
        //this.combiTable[8][16] = new Interaction("ADD_GOB", "12");  // clou+tiroir= photo reine Victoria+photo medusa
        this.combiTable[5][11] = new Interaction("ADD_GOB", "14");//miroir+medusa=code
        this.enigmeWIN.put("Armoir",new Interaction("ADD_GOB", "15")); //Armoir +code
        //this.enigmeWIN.put("Armoir",new Interaction("WIN",null)); //Armoir +code

    }

    private void addCombi(int ID1, int ID2, Interaction interaction){
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

    private void addEnigmeLose(String enigme, Interaction interaction){
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
            enigmeWIN.put(str, interaction);
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

        public Interaction(String action){
            this.action = action;
            this.arg = null;
            this.nextInteraction = null;
        }

        public void run(){
            if(this.action.equals("ADD_GOB")){
                ArrayList<GameObject> ar = GameState.getGameState().getGobs();
                GameObject gob = ar.get(Integer.parseInt(this.arg));
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
