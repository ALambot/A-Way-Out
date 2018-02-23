package com.dolphin.awayout;


public class EnigmeObject {
    private String title = null;
    private String type = null;
    private String texte = null;
    private String[] reponsePossible = null;
    private String reponse =  null;


    //type text
    public EnigmeObject(String title,String texte ,String type , String reponse) {
        this.title = title;
        this.texte = texte;
        this.type = type;
        this.reponse = reponse;
    }


    //type textChoix
    public EnigmeObject(String title, String type, String texte, String[] reponsePossible, String reponse) {
        this.title = title;
        this.type = type;
        this.texte = texte;
        this.reponsePossible = reponsePossible;
        this.reponse = reponse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String[] getReponsePossible() {
        return reponsePossible;
    }

    public void setReponsePossible(String[] reponsePossible) {
        this.reponsePossible = reponsePossible;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
