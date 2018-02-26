package com.dolphin.awayout;

import android.util.Log;

/**
 * Created by antoine on 22.02.18.
 */

public class InteractionManager {

    private Interaction[][] interactions;

    public InteractionManager(){
        interactions = new Interaction[100][100]; //TODO
    }

    public void add(Interaction i){
        interactions[i.obj1][i.obj2] = i;
    }

    public Interaction get(int ID1, int ID2){
        return interactions[ID1][ID2];
    }


    public void doInteraction(int obj1, int obj2){
        Interaction actual = this.get(obj1, obj2);
        if(actual != null){
            //TEST
            GameState.getGameState().getInventory().add(new MyObject("Golde Dolphin","You have found the mighty Golden Dolphin !!!",R.drawable.gold));
        }
    }

    public void doInteractionTEST(){
        Log.d("INTERACTION", "TEST");
        InventoryAdapt inventoryAdapt = GameState.getGameState().getInventory();
        inventoryAdapt.add(new MyObject("Note de l'équipe","Une liste de proposition d'idées pour le surnom d'un de leur membre",R.drawable.note_team));
        inventoryAdapt.remove(inventoryAdapt.getItem(1));
        inventoryAdapt.remove(inventoryAdapt.getItem(1));

    }
}
