package com.dolphin.awayout;

/**
 * Created by antoine on 22.02.18.
 */

public class InteractionManager {

    private Interaction[][] interactions;

    public InteractionManager(){
        interactions = new Interaction[100][100]; //TODO
    }

    public void combine(GameObject obj1, GameObject obj2){

    }

    public void enigmeSuccess(String enigme){

    }

    public void enigmeFail(String enigme){

    }

    public void QRresult(String result){

    }

    public void TimeOut(){

    }


    public class Interaction {

        public final String action;
        public final String arg;

        public Interaction nextInteraction;

        public Interaction(String action, String arg){
            this.action = action;
            this.arg = arg;
            this.nextInteraction = null;
        }
    }

}
