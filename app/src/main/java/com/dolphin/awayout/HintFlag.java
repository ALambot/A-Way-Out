package com.dolphin.awayout;

/**
 * Created by antoine on 26.03.18.
 */

class HintFlag {

    private String type; // QR_REM HAS_GOB ENIGME_UNSOLVED

    private String arg;

    public HintFlag(String type, String arg){
        this.type = type;
        this.arg = arg;
    }

    public boolean val(){ // if this NullPointers, then the problem is elsewhere
        if(type.equals("QR_REM")){
            return GameState.getGameState().remainingQR>0;
        }
        else if(type.equals("HAS_GOB")){
            return GameState.getGameState().getObjectByName(this.arg).isActive();
        }
        else if(type.equals("ENIGME_UNSOLVED")){
            return GameState.getGameState().getEnigmeByTitle(this.arg).isSolved();
        }
        else{
            //ERROR
            return false;
        }
    }

}
