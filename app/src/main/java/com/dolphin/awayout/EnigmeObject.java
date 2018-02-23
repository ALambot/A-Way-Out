package com.dolphin.awayout;


public class EnigmeObject {
    private String title = null;
    private String type = null;
    private String text = null;
    private String[] proposition = null;
    private String reponse =  null;


    //type text
    public EnigmeObject(String title,String text ,String type , String reponse) {
        this.title = title;
        this.text = text;
        this.type = type;
        this.reponse = reponse;
    }


    //type textChoix
    public EnigmeObject(String title, String text, String[] reponsePossible, String reponse) {
        this.title = title;
        this.type = "textChoix";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
