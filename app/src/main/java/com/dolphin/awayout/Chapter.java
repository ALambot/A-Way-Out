package com.dolphin.awayout;

/**
 * Created by antoine on 14.04.18.
 */

public interface Chapter {

    public void load();

    public String getTitle();
    public String getDescription();
    public String getLocation();
    public int getImageID();
    public int getDuration();

}
