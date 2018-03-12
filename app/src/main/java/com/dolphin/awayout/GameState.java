package com.dolphin.awayout;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;

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

    // EscapeRoom
    private InteractionManager interactions;
    private ArrayList<GameObject> gobs;
    private ArrayList<EnigmeObject>  enigmeObjectArrayList;

    /** A private Constructor prevents any other class from instantiating. */
    private GameState() {
        //Optionnal
    }

    // SETTERS ----------

    public void init(Context context){ //init state from escape room file or save file
        this.initialized = true;
        this.ctx = context;

        this.gameDuration = 90;
        
        this.gobs = new ArrayList<GameObject>();

        String longDesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean laoreet dui vitae leo imperdiet egestas non ut metus. Fusce id orci et lorem efficitur consequat quis quis nunc. Nam aliquet ante a ante convallis semper. Cras non elementum dolor. Aenean ornare nisl nec ex accumsan interdum. Sed eu libero eros. Pellentesque luctus, quam eget elementum auctor, nibh orci interdum quam, eget venenatis dui nunc sed ante. Etiam bibendum consectetur tortor eget finibus. Vestibulum ornare tincidunt tristique. In hac habitasse platea dictumst. Vivamus semper erat id leo feugiat, sagittis eleifend ipsum mollis. Sed cursus tincidunt lobortis. Sed consequat at justo sed sagittis. Fusce a tempus est, sed semper lacus.\n" +
                "\n" +
                "Maecenas laoreet augue eu massa convallis sollicitudin. Praesent lacinia mauris sed nisl ullamcorper interdum. Vestibulum ut lectus vitae justo rhoncus viverra vitae eget dui. Suspendisse potenti. Morbi fringilla tempor nibh id vehicula. In non dolor semper, blandit felis et, lacinia tellus. Nam quis eleifend ligula. Aliquam nec viverra lectus, in gravida ipsum. Aenean varius vitae purus vel feugiat. Aenean eleifend, nulla non fermentum eleifend, tortor dolor tristique tellus, in laoreet justo elit ut turpis.";

        gobs.add(new GameObject(1,"cle", "Ceci est une clé", R.drawable.key_demo));
        gobs.add(new GameObject(2,"cle2", "Ceci est une autre clé", R.drawable.key_demo));
        gobs.add(new GameObject(3,"coffre", longDesc, R.drawable.chest_demo));
        gobs.add(new GameObject(4,"coffre2", "Ceci est un autre coffre", R.drawable.chest_demo));

        gobs.get(0).activate();
        gobs.get(2).activate();
        gobs.get(3).activate();

        ArrayList<EnigmeObject> enigmeList = new ArrayList<>();
        String [] reponses = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Dauphin" ,"Qui est le prince des dauphins ? ", reponses, "Nico")));
        String [] reponses2 = {"Sophie", "Héloise", "Nico", "Antoine"};
        enigmeList.add((new EnigmeObject("Surnom","A qui faut il trouver un surnom ? ", reponses2, "Sophie")));
        enigmeList.add((new EnigmeObject("cypherRoll",2,"Victoria")));
        this.enigmeObjectArrayList = enigmeList;

        this.interactions = new InteractionManager();
    }

    // GETTERS ----------

    public static synchronized GameState getGameState() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }

    public ArrayList<GameObject> getGobs(){
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return this.gobs;
    }

    public ArrayList<EnigmeObject> getEnigmeList(){
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        return this.enigmeObjectArrayList;
    }

    public InventoryAdapt getInventoryAdapt() throws GameStateNotInitializedException {
        if(initialized == false){
            throw new GameStateNotInitializedException();
        }
        ArrayList<GameObject> activeGobs = new ArrayList<>();
        for(GameObject gob : this.gobs){
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
        return gameDuration-elapsed;
    }

    public ArrayList<EnigmeObject> getEnigmeObjectArrayList() {
        return enigmeObjectArrayList;
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
