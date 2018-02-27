package com.dolphin.awayout;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by siasn on 20-02-18.
 */

public class GameState {

    private static GameState gameState;
    private InventoryAdapt inventory;

    // Timer vars
    private long startTime; //seconds
    private long gameDuration = 600; // seconds

    //enigimes variable
    private ArrayList<EnigmeObject>  enigmeObjectArrayList;


    private InteractionManager interactions;

    public boolean keyDEMO = true;

    /** A private Constructor prevents any other class from instantiating. */
    private GameState() {
        //Optionnal
    }

    public static synchronized GameState getGameState() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    public InventoryAdapt getInventory(){
        return inventory;
    }

    public void setInventory(InventoryAdapt inventory){
        this.inventory = inventory;
    }

    public void startTimer(){
        this.startTime = Calendar.getInstance().getTimeInMillis()/1000;
    }

    public long getRemainingTime(){
        long elapsed = Calendar.getInstance().getTimeInMillis()/1000 - startTime;
        return Math.max(0, gameDuration-elapsed);
    }

    public ArrayList<EnigmeObject> getEnigmeObjectArrayList() {
        return enigmeObjectArrayList;
    }

    public void setEnigmeObjectArrayList(ArrayList<EnigmeObject> enigmeObjectArrayList) {
        this.enigmeObjectArrayList = enigmeObjectArrayList;
    }


    public void setInteractions(){
        this.interactions = new InteractionManager();
    }

    public InteractionManager getInteractions(){
        return this.interactions;
    }



    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
