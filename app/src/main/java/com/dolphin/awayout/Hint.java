package com.dolphin.awayout;

import android.util.Log;

/**
 * Created by antoine on 26.03.18.
 */

public class Hint {

    private boolean seen;
    private String text;
    private HintFlag[] flags;

    public Hint(String hint, HintFlag[] flags){
        this.text = hint;
        this.flags = flags;
    }

    public boolean valid(){
        for(int i = 0; i<this.flags.length; i++){
            if(!this.flags[i].val()){
                return false;
            }
        }
        return true;
    }

    public String getText(){
        Log.d("HINT",""+GameState.getGameState().remainingQR);
        return this.text;
    }

}
