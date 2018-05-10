package com.dolphin.awayout;


public class EnigmeObject {

    /*type
    * 1 : text choix
    * 2 : cypher roll
    * 3 : armoire
    * 4 : code
    * 5 : montre-gousset demo
    */
    private String title = null;
    private int type;
    private String text = null;
    private String[] proposition = null;
    private String hint = null;
    private String reponse =  null;
    private int imgId = R.drawable.inventory_button;
    private boolean hidden = true;
    private boolean solved = false;


    //type text
    public EnigmeObject(String title,int type , String reponse) {
        this.title = title;
        this.type = type;
        this.reponse = reponse;
        this.hidden = true;
        this.solved = false;
    }


    //type textChoix
    public EnigmeObject(String title, String text, String[] reponsePossible, String reponse) {
        this.title = title;
        this.type = 1;
        this.text = text;
        this.proposition = reponsePossible;
        this.reponse = reponse;
        this.hidden = true;
        this.solved = false;
    }

    //type code
    public EnigmeObject(String title,String hint, String reponse,  int img) {
        this.title = title;
        this.type = 4;
        this.hint = hint;
        this.reponse = reponse;
        this.hidden = true;
        this.solved = false;
        this.imgId = img;
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

    public boolean isHidden(){
        return this.hidden;
    }

    public boolean isSolved(){
        return this.solved;
    }

    public void setVisible(boolean visible){
        this.hidden = !visible;
    }

    public void solve(){
        this.solved = true;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }


}
