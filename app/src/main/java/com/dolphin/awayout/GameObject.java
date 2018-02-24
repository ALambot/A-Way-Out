package com.dolphin.awayout;

import android.view.View;
import android.widget.ImageView;


public class GameObject {
    private final int ID;
    private static int nextID = 1;

    private String name;
    private String description;
    private int idImage;
    private ImageView img;
    private boolean visible;

    public GameObject(String name, int imageRef) {
        this.ID = this.nextID;
        this.nextID++;

        this.name = name;
        this.idImage = imageRef;
        this.visible = false;
    }

    public GameObject(String name, String description, int imageRef) {
        this.ID = this.nextID;
        this.nextID++;

        this.name = name;
        this.description = description;
        this.idImage = imageRef;
        this.visible = false;
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

    public int getID() {return this.ID;}

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

    public boolean equals(GameObject o){
        return this.ID == o.ID;
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