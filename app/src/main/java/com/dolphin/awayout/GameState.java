package com.dolphin.awayout;


import android.app.Activity;

import android.content.Context;

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

    // Timer
    private long startTime; //seconds
    private long gameDuration; // seconds
    private long penalite; //seconds
    private boolean finished;
    private long finish;

    // Gob ID
    private int nextID;

    // EscapeRoom
    private InteractionManager interactions;
    private HashMap<String,GameObject> gobs;
    private HashMap<String,EnigmeObject> enigmes;

    /** A private Constructor prevents any other class from instantiating. */
    private GameState() {
        //Optionnal
    }

    public static synchronized GameState getGameState() { // Do not touch
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    // SETTERS ----------

    public void init(Context context){ //init state from escape room file or save file
        this.initialized = true;
        this.ctx = context;

        this.gameDuration = 900;
        this.penalite = 0;

        nextID = 0; //pas touche



        this.gobs = new HashMap<String, GameObject>();

        addGob(new GameObject("cle", "Ceci est une clé", R.drawable.key_demo));
        addGob(new GameObject("miroir", "Un ancien mirroir posé sur la cheminée", R.drawable.mirror));
        addGob(new GameObject("vase", "Une vase avec des fleures fraiches", R.drawable.vase));
        addGob(new GameObject("bol vide", "Un ancien pot de chambre", R.drawable.chamber_pot));
        addGob(new GameObject("clou", "Un clou rouillé", R.drawable.nail));
        addGob(new GameObject("statue", "Une délicate statue posée sur le bureau", R.drawable.statue));
        addGob(new GameObject("cypherDisc", "Un disque utilisé pour encrypter et décrypter des codes. La partie du milieu est mobile", R.drawable.outside_cypher_roll));
        addGob(new GameObject("medusa", "Un papier d'une représentation de la Méduse", R.drawable.medusa_paper));
        addGob(new GameObject("victoria","Une photo de la reine Victoria", R.drawable.victoria));
        addGob(new GameObject("armoire", "Une armoire avec toutes les lettres engravées. Elle est verouillée. On peut appuyer sur les lettres.", R.drawable.armoire));
        addGob(new GameObject("boule transparente", "Une boule de verre transparente. Elle est assez lourde.", R.drawable.crystal_ball));
        addGob(new GameObject("feuille", "Des chiffres et flèches sont écrits dessus. ", R.drawable.password_paper));
        addGob(new GameObject("tiroir", "Un tiroir fermé. Il manque la poignée ! ", R.drawable.tirroir));

        //DEBUG
        addGob(new GameObject("le small one", "put me in le big one", R.drawable.inside_cypher_roll));
        addGob(new GameObject("le big one", "no touchy me", R.drawable.outside_cypher_roll));

        this.enigmes = new HashMap<String, EnigmeObject>();

        addEnigme((new EnigmeObject("Armoire mysterieuse",3,"ULPQXTOD")));
        addEnigme((new EnigmeObject("cypherRoll",2,"Victoria")));

        this.interactions = new InteractionManager();
        this.interactions.init();
        this.interactions.start();
    }

    // ADDERS ------
    private void addGob(GameObject gob){
        this.gobs.put(gob.getName(),gob);
    }
    private void addEnigme(EnigmeObject e){
        this.enigmes.put(e.getTitle(),e);
    }

    // GETTERS ----------

    public int getNextID(){
        return this.nextID++;
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
    }

    public void finishTimer(){
        this.finished = true;
        this.finish = Calendar.getInstance().getTimeInMillis()/1000;
    }

    public void penalize(long seconds){
        this.penalite += seconds;
    }

    // OTHER ------

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private class GameStateNotInitializedException extends RuntimeException{ }
}
