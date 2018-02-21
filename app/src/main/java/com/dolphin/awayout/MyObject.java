package com.dolphin.awayout;

import android.view.View;
import android.widget.ImageView;


public class MyObject {
    private String name = null;
    private String description;
    private int idImage;
    private ImageView img = null;
    private boolean visible = true;

    public MyObject(String name, int imageRef) {
        this.name = name;
        this.idImage = imageRef;
    }

    public MyObject(String name,String description, int imageRef) {
        this.name = name;
        this.description = description;
        this.idImage = imageRef;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr) {
        this.description = descr;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public ImageView getImage() {
        return img;
    }

    public void setImage(ImageView image) {
        this.img = image;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        if(visible) show();
        else hide();
    }


    public void show(){
        visible = true;
        img.setVisibility(View.VISIBLE);
    }

    public void hide(){
        visible = false;
        img.setVisibility(View.INVISIBLE);

    }


}