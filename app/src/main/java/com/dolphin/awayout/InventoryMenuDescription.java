package com.dolphin.awayout;

import android.graphics.Rect;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by siasn on 21-02-18.
 */

public class InventoryMenuDescription extends InventoryObserver {
    private TextView title;
    private TextView description;
    private ImageView img;

    public InventoryMenuDescription(TextView title, TextView description, ImageView img,
                                    InventoryGridView inventoryGridView) {
        this.title = title;
        this.description = description;
        this.img = img;
        this.inventoryGridView = inventoryGridView;
        this.inventoryGridView.attach(this);
    }
    @Override
    public void update(int state, MyObject object, int x, int y) {
        if(state == 1) {
            title.setText(object.getName());
            description.setText(object.getDescription());
            img.setImageDrawable(object.getImage().getDrawable());
        }
        else {
            Log.d("UPDATE", "name"+ object.getName() +"x: "+ x + " y :" + y);
            if (checkCollision(x, y, object.getImage())) {

                //TEST INTERACTION
                if(object.getName().equals("cle2") && object.getName().equals("coffre2")) {
                    GameState.getGameState().getInteractions().doInteractionTEST();
                }

                Log.d("UPDATE", "Collision detected : goalX =" + img.getX() + "goalY =" + img.getY());
                Toast.makeText(img.getContext(), "Une collision a lieu entre " +object.getName()+ " et " + title.getText(),
                        Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(img.getContext(), "Veuillez déplacer l'objet vers l'objet décrit ci-dessus",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean checkCollision(int x, int y, ImageView v1) {
        Rect r1 = new Rect(x, y, x + v1.getWidth(), y + v1.getHeight());
        Rect r2 = new Rect();
        img.getHitRect(r2);
        return r1.intersect(r2);
    }

}
