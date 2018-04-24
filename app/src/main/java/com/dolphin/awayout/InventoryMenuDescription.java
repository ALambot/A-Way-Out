package com.dolphin.awayout;

import android.content.Intent;
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
    public void update(int state, GameObject object, int x, int y) {
        if(state == 1) {
            title.setText(object.getName());
            description.setText(object.getDescription());
            img.setImageDrawable(object.getImage().getDrawable());
        }
        else {
                try {
                    GameState.getGameState().getInteractions().combine(object, GameState.getGameState().getObjectByName(title.getText().toString()));
                    InventoryMenu.onUpdate();
                } catch(NullPointerException e) {
                    Toast.makeText(img.getContext(), "Vous ne pouvez combiner un objet avec votre inventaire",
                            Toast.LENGTH_SHORT).show();
                }

        }

    }


    public TextView getTitle() {
        return title;
    }

    public TextView getDescription() {
        return description;
    }

    public ImageView getImg() {
        return img;
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
