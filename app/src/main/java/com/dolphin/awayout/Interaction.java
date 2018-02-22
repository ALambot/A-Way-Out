package com.dolphin.awayout;

/**
 * Created by antoine on 22.02.18.
 */

public class Interaction {

    public final int obj1, obj2;
    public final String action;
    public final String arg;

    public Interaction(int obj1, int obj2, String action, String arg){
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.action = action;
        this.arg = arg;
    }
}
