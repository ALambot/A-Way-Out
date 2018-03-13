package com.dolphin.awayout;

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
    private Context ctx;

    // Timer
    private long startTime; //seconds
    private long gameDuration; // seconds
    private long penalite; //seconds

    // Gob ID
    private int nextID;

    // EscapeRoom
    private InteractionManager interactions;
    private HashMap<String,GameObject> gobs;
    private ArrayList<EnigmeObject> enigmeObjectArrayList;

    /** A private Constructor prevents any other class from instantiating. */
    private GameState() {
        //Optionnal
    }

    // SETTERS ----------

    public void init(Context context){ //init state from escape room file or save file
        this.initialized = true;
        this.ctx = context;

        this.gameDuration = 900;
        this.penalite = 0;

        nextID = 0; //pas touche
        
        this.gobs = new HashMap<String,GameObject>();

        addGob(new GameObject("cle", "Ceci est une clé", R.drawable.key_demo));
        addGob(new GameObject("miroir", "Un ancien mirroir posé sur la cheminée", R.drawable.mirror));
        addGob(new GameObject("vase", "Une vase avec des fleures fraiches", R.drawable.vase));
        addGob(new GameObject("bol", "Un ancien pot de chambre", R.drawable.chamber_pot));
        addGob(new GameObject("clou", "Un clou rouillé", R.drawable.nail));
        addGob(new GameObject("statue", "Une délicate statue posée sur le bureau", R.drawable.statue));
        addGob(new GameObject("cypherDisr", "Un disque utilisé pour encrypter et décrypter des codes. La partie du milieu est mobile", R.drawable.outside_cypher_roll));
        addGob(new GameObject("medusa", "Un papier d'une représentation de la Méduse", R.drawable.medusa_paper));
        addGob(new GameObject("victoria","Une photo de la reine Victoria", R.drawable.victoria));
        addGob(new GameObject("armoire", "Une armoire avec toutes les lettres engravées. Elle est verouillée. On peut appuyer sur les lettres.", R.drawable.chest_demo));
        addGob(new GameObject("boule transparente", "Une boule de verre transparente. Elle est assez lourde.", R.drawable.crystal_ball));
        addGob(new GameObject("feuille", "Des chiffres et flèches sont écrits dessus. ", R.drawable.password_paper));
        addGob(new GameObject("tiroir", "Un tiroir fermé. Il manque la poignée ! ", R.drawable.tirroir));

        //gobs.get("cle").activate();

        ArrayList<EnigmeObject> enigmeList = new ArrayList<>();
        /*String [] reponses = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Dauphin" ,"Qui est le prince des dauphins ? ", reponses, "Nico")));
        String [] reponses2 = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Surnom","A qui faut il trouver un surnom ? ", reponses2, "Sophie")));*/
        //enigmeList.add((new EnigmeObject("cypherRoll",2,"Victoria")));
        enigmeList.add((new EnigmeObject("Armoire mystérieuse",3,"ULPQXTOD")));
        enigmeList.add((new EnigmeObject("cypherRoll",2,"Victoria")));
        this.enigmeObjectArrayList = enigmeList;

        this.interactions = new InteractionManager();
        interactions.init();
    }

    private void addGob(GameObject gob){
        this.gobs.put(gob.getName(),gob);
    }

    // GETTERS ----------

    public static synchronized GameState getGameState() { // Do not touch
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    public int getNextID(){
        return this.nextID++;
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
        return this.enigmeObjectArrayList;
    }

    public GameObject getObjectByName(String name){
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return this.gobs.get(name);
    }

    public InventoryAdapt getInventoryAdapt() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        ArrayList<GameObject> activeGobs = new ArrayList<>();
        for(GameObject gob : this.gobs.values()){
            if(gob.isActive()){
                activeGobs.add(gob);
            }
        }
        return new InventoryAdapt(this.ctx, activeGobs);
    }

    public long getRemainingTime() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        long elapsed = Calendar.getInstance().getTimeInMillis()/1000 - startTime;
      
        // return Math.max(0, gameDuration-elapsed); // stops at zero
        return this.gameDuration-elapsed-this.penalite;
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

    public void penalize(long seconds){
        this.penalite += seconds;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private class GameStateNotInitializedException extends RuntimeException{ }
}
