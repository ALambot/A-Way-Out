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
                ir.run();
            }
            return true;
        }
        else{
            if(ir != null && gob == null){
                ir.run(); //DEBUG ONLY
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
