package com.dolphin.awayout;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by antoine on 27.03.18.
 */

public class Interaction {

    public final String action; // ADD_GOB REMOVE_GOB SOLVE_ENIGME HIDE_ENIGME SHOW_ENIGME GAMEOVER WIN PENALITE
    public final String arg;

    public Interaction nextInteraction;

    public Interaction(String action, String arg){
        this.action = action;
        this.arg = arg;
        this.nextInteraction = null;
    }

    public Interaction(String action){
        this.action = action;
        this.arg = null;
        this.nextInteraction = null;
    }

    public void run(){

        if(this.action.equals("ADD_GOB")){
            GameObject gob = GameState.getGameState().getObjectByName(this.arg);
            gob.activate();
            gob.setFound();
        }
        else if(this.action.equals("REMOVE_GOB")){
            GameState.getGameState().getObjectByName(this.arg).deactivate();
        }
        else if(this.action.equals("SOLVE_ENIGME")){
            GameState.getGameState().getEnigmeByTitle(this.arg).solve();
        }
        else if(this.action.equals("HIDE_ENIGME")){
            GameState.getGameState().getEnigmeByTitle(this.arg).setVisible(false);
        }
        else if(this.action.equals("SHOW_ENIGME")){
            GameState.getGameState().getEnigmeByTitle(this.arg).setVisible(true);
            Toast.makeText(GameState.getGameState().ctx, "Vous avez débloqué une énigme !", Toast.LENGTH_SHORT).show();
        }
        else if(this.action.equals("GAMEOVER")){
            GameState.getGameState().finishTimer();
        }
        else if(this.action.equals("WIN")){
            GameState.getGameState().finishTimer();
        }
        else if(this.action.equals("PENALITE")){
            int penne = Integer.parseInt(this.arg);
            GameState.getGameState().penalize(penne);
        }
        else if(this.action.equals("launch activity")){
            Intent intent=new Intent(GameState.getGameState().ctx, LooseScreen.class);
            GameState.getGameState().ctx.startActivity(intent);

        }
        else if(this.action.equals("LAUNCH_POPUP")){
            LinearLayout viewGroupe= GameState.getGameState().inventaire.findViewById(R.id.popup_General);
            LayoutInflater layoutInflater=(LayoutInflater) GameState.getGameState().inventaire.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = layoutInflater.inflate(R.layout.popup_general, viewGroupe);
            final PopupWindow popup=new PopupWindow(GameState.getGameState().inventaire);
            popup.setContentView(layout);
            popup.setFocusable(true);

            popup.showAtLocation(layout, Gravity.CENTER,0,0);
            Button close=layout.findViewById(R.id.closePopUpGeneral);
            close.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    popup.dismiss();
                }
            });
            TextView title=layout.findViewById(R.id.titlePopUpGeneral);
            TextView textinPopUp=layout.findViewById(R.id.textPopUpGeneral);
            title.setText("Trouvé !");
            textinPopUp.setText(this.arg);


        }

        if(this.nextInteraction != null){
            this.nextInteraction.run();
        }
    }

    public String toString(){
        String ret = this.action + " " +this.arg;
        if(this.nextInteraction != null){
            ret = ret+this.nextInteraction.toString();
        }
        return ret;
    }
}
