package com.dolphin.awayout;

/**
 * Created by siasn on 20-02-18.
 */

public class GameState {
    private static GameState gameState;
    private InventoryAdapt inventory;
    /** A private Constructor prevents any other class from instantiating. */
    private GameState() {
        //Optionnal
    }
    public static synchronized GameState getGameState() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState; //LOL
    }

    public InventoryAdapt getInventory(){
        return inventory;
    }

    public void setInventory(InventoryAdapt inventory){
        this.inventory = inventory;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
