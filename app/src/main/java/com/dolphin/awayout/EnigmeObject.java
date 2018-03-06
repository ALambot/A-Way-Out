package com.dolphin.awayout;


public class EnigmeObject {

    /*type
    * 1 : text choix
    * 2 : cypher roll
    */
    private String title = null;
    private int type;
    private String text = null;
    private String[] proposition = null;
    private String reponse =  null;


    //type text
    public EnigmeObject(String title,int type , String reponse) {
        this.title = title;
        this.type = type;
        this.reponse = reponse;
    }


    //type textChoix
    public EnigmeObject(String title, String text, String[] reponsePossible, String reponse) {
        this.title = title;
        this.type = 1;
        this.text = text;
        this.proposition = reponsePossible;
        this.reponse = reponse;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getProposition() {
        return proposition;
    }

    public void setProposition(String[] proposition) {
        this.proposition = proposition;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
