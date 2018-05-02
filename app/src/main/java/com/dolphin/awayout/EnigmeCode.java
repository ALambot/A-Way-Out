package com.dolphin.awayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EnigmeCode extends AppCompatActivity{

    private EditText editText;
    private ListView inventaire;
    private InventoryListView inventair;
    private InventoryAdapt inventoryAdapt;
    public static EnigmeObject enigme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_code_coffret);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        GameState.getGameState().enigmeCoffret=this;

        inventaire=findViewById(R.id.listView_enigme_coffret);
        inventoryAdapt = GameState.getGameState().getInventoryAdapt();
        inventair=new InventoryListView(EnigmeCode.this,inventaire,inventoryAdapt, true);
        editText = (EditText)findViewById(R.id.editTextCoffret);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                InteractionManager interactionManager= GameState.getGameState().getInteractions();
                //if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    String reponse = editText.getText().toString();
                    if(reponse.length() == 3) {
                        if (reponse.equals(enigme.getReponse())) {
                            interactionManager.enigmeSuccess(enigme.getTitle());
                            //TODO creer interaction succes et fail pour coffret
                        } else {
                            interactionManager.enigmeFail(enigme.getTitle());
                            editText.setText("");
                            Toast.makeText(EnigmeCode.this, "Mauvais Code ! Vous perdez 30 secondes", Toast.LENGTH_LONG).show();
                        }
                    }
                //}
                return false;
            }
        });


    }



}
