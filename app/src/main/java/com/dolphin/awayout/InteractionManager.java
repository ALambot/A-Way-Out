package com.dolphin.awayout;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
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

    public InteractionManager(int gobCount){
        this.start = null;
        this.combiTable = new Interaction[gobCount][gobCount];
        this.enigmeWIN = new HashMap<String, Interaction>();
        this.enigmeLOSE = new HashMap<String, Interaction>();
        this.qr = new HashMap<String, Interaction>();
        this.timeOut = null;
    }

    public void init(){

        /*
        // DEBUG
        addQR("Pain", new Interaction("PENALITE", "30"));
        addQR("Sesame", new Interaction("SHOW_ENIGME", "cypherRoll"));
        addQR("Roll", new Interaction("ADD_GOB", "le small one"));
        addQR("Roll", new Interaction("ADD_GOB", "le big one"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "miroir"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "vase"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "bol vide"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "tiroir"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "armoire"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "clou"));
        addQR("Jackpot", new Interaction( "ADD_GOB", "statue"));
        addQR("Jackpot", new Interaction( "SHOW_ENIGME", "Armoire mysterieuse"));

        for(int ii = 0; ii<6; ii++) {
            addCombi("le small one", "le big one", new Interaction("PENALITE", "10"));
        }
        // Interactions lancees au debut
        //addStart(new Interaction("ADD_GOB","cle"));

        // QR
        addQR("miroir", new Interaction("ADD_GOB", "miroir"));
        addQR("vase", new Interaction("ADD_GOB", "vase"));
        addQR("bol vide", new Interaction("ADD_GOB", "bol vide"));
        addQR("tiroir", new Interaction("ADD_GOB", "tiroir"));
        addQR("armoire", new Interaction("ADD_GOB", "armoire"));
        addQR("armoire", new Interaction("SHOW_ENIGME", "Armoire mysterieuse"));
        addQR("clou", new Interaction("ADD_GOB", "clou"));
        addQR("statue", new Interaction("ADD_GOB", "statue"));
        GameState.getGameState().remainingQR = 7; //TODO TEMPORARY

        // COMBI
        addCombi("vase","bol vide", new Interaction("ADD_GOB", "boule transparente")); //vase+bol =boule transparente
        addCombi("vase","bol vide", new Interaction("LAUNCH_POPUP", "Vous videz le vase dans le bol. Dans l'eau du vase était cachée une boule transparente."));
        addCombi("boule transparente", "statue", new Interaction("SHOW_ENIGME", "cypherRoll"));  // boule+statue= cypherKey TODO
        addCombi("boule transparente", "statue", new Interaction("REMOVE_GOB", "statue"));
        addCombi("boule transparente", "statue", new Interaction("REMOVE_GOB", "boule transparente"));
        addCombi("boule transparente", "statue", new Interaction("LAUNCH_POPUP", "Lorsque vous posez la boule dans la paume de la statue, le socle de celle-ci s'ouvre pour dévoiler un cypher roll. Il est disponible dans vos énigmes"));
        addCombi("clou","tiroir", new Interaction("ADD_GOB", "medusa"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("clou","tiroir", new Interaction("ADD_GOB", "victoria"));  // clou+tiroir= photo reine Victoria+photo medusa
        addCombi("clou","tiroir", new Interaction("LAUNCH_POPUP", "Vous utilisez le clou comme poignée de tiroir. A l'intérieur de celui-ci se trouvent deux photos."));
        addCombi("miroir","medusa", new Interaction("ADD_GOB", "feuille"));//miroir+medusa=code TODO
        addCombi("medusa","miroir", new Interaction("ADD_GOB", "feuille"));
        addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "miroir"));
        addCombi("medusa","miroir", new Interaction("REMOVE_GOB", "medusa"));
        addCombi("medusa","miroir", new Interaction("LAUNCH_POPUP", "En mettant la photo de la Méduse devant le miroir, elle s'éface pour laisser apparaitre un code !"));

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
        */
    }

    // ADDERS ------

    public void addStart(Interaction interaction){
        Interaction ir = this.start;
        interaction.nextInteraction = ir;
        this.start = interaction;
    }

    public void addCombi(String name1, String name2, Interaction interaction){
        GameObject gob1 = GameState.getGameState().getObjectByName(name1);
        GameObject gob2 = GameState.getGameState().getObjectByName(name2);
        int ID1 = gob1.getID();
        int ID2 = gob2.getID();

        Interaction ir = this.combiTable[ID1][ID2];
        interaction.nextInteraction = ir;
        this.combiTable[ID1][ID2] = interaction;
    }

    public void addEnigmeWIN(String enigme, Interaction interaction){
        Interaction ir = this.enigmeWIN.get(enigme);
        interaction.nextInteraction = ir;
        this.enigmeWIN.put(enigme, interaction);
    }

    public void addEnigmeLOSE(String enigme, Interaction interaction){
        Interaction ir = this.enigmeLOSE.get(enigme);
        interaction.nextInteraction = ir;
        this.enigmeLOSE.put(enigme, interaction);
    }

    public void addQR(String str, Interaction interaction){
        Interaction ir = this.qr.get(str);
        interaction.nextInteraction = ir;
        this.qr.put(str,interaction);
    }

    public void addTimeOut(Interaction interaction){
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
        GameObject gob = GameState.getGameState().getObjectByName(result);
        if (gob != null && !gob.isFound()) {
            if (ir != null) {
                GameState.getGameState().getObjectByName(result).setFound();
                GameState.getGameState().remainingQR--; // TODO TEMPORARY
                ir.run();
            }
            return true;
        }
        else{
            if(ir != null && gob == null){
                ir.run(); //DEBUG ONLY
                GameState.getGameState().remainingQR--; // TODO TEMPORARY
            }
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

}
