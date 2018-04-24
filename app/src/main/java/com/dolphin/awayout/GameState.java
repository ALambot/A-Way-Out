package com.dolphin.awayout;


import android.app.Activity;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by siasn on 20-02-18.
 */

public class GameState {

    private static GameState gameState;
    private boolean initialized = false;
    public Context ctx;
    public Activity inventaire;
    public Activity enigmeCoffret;
    private static Chapter chapter;

    // Timer
    private long startTime; //seconds
    private long gameDuration; // seconds
    private long penalite; //seconds
    private boolean finished;
    private long finish;

    // Gob ID
    private int nextID;

    // Hints
    private ArrayList<Hint> hints;
    public int remainingQR;

    // EscapeRoom
    private InteractionManager interactions;
    private HashMap<String,GameObject> gobs;
    private HashMap<String,EnigmeObject> enigmes;
    public String winMessage;
    public String looseMessage;

    /** A private Constructor prevents any other class from instantiating. */
    private GameState() {

    }

    public static synchronized  void relaunch() {
        chapter.load();
    }

    public static synchronized GameState getGameState() { // Do not touch
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    // SETTERS ----------

    public void init(long gameDuration, ArrayList<GameObject> gobs, ArrayList<EnigmeObject> enigmes, ArrayList<Hint> hints, Chapter chapter, String looseMessage, String winMessage){ //init state from escape room file or save file

        this.chapter = chapter;

        this.initialized = true;
        this.finished = false;
        this.penalite = 0;
        this.nextID = 0; //pas touche
        this.remainingQR = 0;

        this.looseMessage=looseMessage;
        this.winMessage=winMessage;
        this.gameDuration = gameDuration;

        this.gobs = new HashMap<String, GameObject>();
        for(GameObject gob : gobs){
            addGob(gob);
        }

        this.enigmes = new HashMap<String, EnigmeObject>();
        for(EnigmeObject enigme : enigmes){
            addEnigme(enigme);
        }

        this.hints = hints;
    }

    // ADDERS ------
    private void addGob(GameObject gob){
        this.gobs.put(gob.getName(),gob);
    }
    private void addEnigme(EnigmeObject e){
        this.enigmes.put(e.getTitle(),e);
    }

    public void addIM(InteractionManager im){
        this.interactions = im;
        this.interactions.start();
    }
    public void addContext(Context ctx){
        this.ctx = ctx;
    }

    // GETTERS ----------

    public int getNextID(){
        return this.nextID++;
    }

    public String getRandomHint(){
        ArrayList<Hint> valid = new ArrayList<Hint>();
        for(Hint h : this.hints){
            if(h.valid()){
                valid.add(h);
            }
        }
        if(valid.size()>0){
            return valid.get((int) Math.floor(Math.random()*valid.size())).getText();
        }
        else{
            return "Aucun indice disponible mais vous perdez quand meme du temps ;-) ";
        }
    }

    public long getRemainingTime() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        if(finished){
            return this.finish - this.startTime - this.penalite;
        }
        long elapsed = Calendar.getInstance().getTimeInMillis()/1000 - startTime;

        // return Math.max(0, gameDuration-elapsed); // stops at zero
        return this.gameDuration-elapsed-this.penalite;
    }

    public ArrayList<GameObject> getGobs(){
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return new ArrayList<GameObject>(this.gobs.values());
    }

    public ArrayList<EnigmeObject> getEnigmeList(){
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        ArrayList<EnigmeObject> ret = new ArrayList<EnigmeObject>();
        for(EnigmeObject e : this.enigmes.values()){
            if(!e.isHidden()){
                ret.add(e);
            }
        }
        return ret;
    }

    public ArrayList<Object> getEnigmeList2(){   //renvoie une liste d'objets
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        ArrayList<Object> ret = new ArrayList<Object>();
        for(EnigmeObject e : this.enigmes.values()){
            if(!e.isHidden()){
                ret.add(e);
            }
        }
        return ret;
    }

    public InventoryAdapt getInventoryAdapt() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        ArrayList<GameObject> activeGobs = new ArrayList<GameObject>();
        for(GameObject gob : this.gobs.values()){
            if(gob.isActive()){
                activeGobs.add(gob);
            }
        }
        return new InventoryAdapt(this.ctx, activeGobs);
    }

    public InteractionManager getInteractions() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return this.interactions;
    }

    public EnigmeObject getEnigmeByTitle(String title){
        return this.enigmes.get(title);
    }

    public GameObject getObjectByName(String name){
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return this.gobs.get(name);
    }

    // FUNCTIONS ----------

    public void startTimer(){
        this.startTime = Calendar.getInstance().getTimeInMillis()/1000;
        Log.d("KEK", "STARTTIMER");
    }

    public void finishTimer(){
        this.finished = true;
        this.finish = Calendar.getInstance().getTimeInMillis()/1000;
    }

    public void penalize(long seconds){
        this.penalite += seconds;
    }

    public void crash(){
        //throw new KEKxception();
        int zero = 0;
        int a = 42/zero;
    }

    // OTHER ------

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private class GameStateNotInitializedException extends RuntimeException{ }
    private class KEKxception extends RuntimeException{ }
}
