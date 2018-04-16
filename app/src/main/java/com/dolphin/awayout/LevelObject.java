package com.dolphin.awayout;


public class LevelObject{ //extends java.lang.Object{
    private String Title;
    //Boolean lock = false;
    private String description;
    private int imageId;
    private int Temps;   // ou int a voir
    private String Localisation;
    //private GameState gameState;
    //Et/ou fichier contenant le niveau

    public LevelObject(String title, String description, int imageId, int temps, String localisation) {
        Title = title;
        this.description = description;
        this.imageId = imageId;
        Temps = temps;
        Localisation = localisation;
    }

    public LevelObject(String title) {
        Title = title;
        this.description = "aucune description";
        this.imageId = R.drawable.gold;
        Temps = 1800;
        Localisation = "ici";
    }
    /*
        public LevelObject(String fichier) {
            Title = title;
            this.description = description;
            //this.gameState = gameState;
        }
    */
    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageId() {
        return imageId;
    }

    public int getTemps() {
        return Temps;
    }

    public String getStringTemps() {
        String min = Integer.toString(((getTemps() - getTemps()%60)/60));
        return (min + " min " );
    }

    public String getLocalisation() {
        return Localisation;
    }
}