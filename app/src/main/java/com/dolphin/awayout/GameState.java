package com.dolphin.awayout;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by siasn on 20-02-18.
 */

public class GameState {

    private static GameState gameState;

    private boolean initialized = false;

    // Timer
    private long startTime; //seconds
    private long gameDuration; // seconds

    //
    private InteractionManager interactions;
    private ArrayList<GameObject> gobs;

    // Visuals
    private InventoryAdapt inventory;


    /** A private Constructor prevents any other class from instantiating. */
    private GameState() {
        //Optionnal
    }

    // SETTERS ----------

    public void init(){ //init state from escape room file or save file
        this.initialized = true;

        this.gameDuration = 600;
    }

    public void setInventory(InventoryAdapt inventory){
        this.inventory = inventory;
    }

    public void setInteractions(){
        this.interactions = new InteractionManager();
    }

    // GETTERS ----------

    public static synchronized GameState getGameState() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    public InventoryAdapt getInventory() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return inventory;
    }

    public long getRemainingTime() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        long elapsed = Calendar.getInstance().getTimeInMillis()/1000 - startTime;
        // return Math.max(0, gameDuration-elapsed); // stops at zero
        return gameDuration-elapsed;
    }

    public InteractionManager getInteractions() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return this.interactions;
    }

    // FUNCTIONS ----------

    public void startTimer(){
        this.startTime = Calendar.getInstance().getTimeInMillis()/1000;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private class GameStateNotInitializedException extends RuntimeException{ }
}
