package com.dolphin.awayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EnigmeCoffret extends AppCompatActivity{

    private EditText editText;
    private ListView inventaire;
    private InventoryListView inventair;
    private InventoryAdapt inventoryAdapt;
    private String solution="635";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_code_coffret);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        inventaire=findViewById(R.id.listView_enigme_coffret);
        inventoryAdapt = GameState.getGameState().getInventoryAdapt();
        inventair=new InventoryListView(EnigmeCoffret.this,inventaire,inventoryAdapt, true);
        editText = (EditText)findViewById(R.id.editTextCoffret);
        /*editText.addTextChangedListener(new TextWatcher() {
            InteractionManager interactionManager=GameState.getGameState().getInteractions();
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });*/

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                InteractionManager interactionManager= GameState.getGameState().getInteractions();
                //if(keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    String reponse = editText.getText().toString();
                    if(reponse.length() == 3) {
                        if (reponse.equals(solution)) {
                            interactionManager.enigmeSuccess("Coffret");
                            //TODO creer interaction succes et fail pour coffret
                            Intent intent = new Intent(EnigmeCoffret.this, fin_niveau.class);
                            startActivity(intent);
                        } else {
                            interactionManager.enigmeFail("Coffret");
                            editText.setText("");
                            Toast.makeText(EnigmeCoffret.this, "Mauvais Code ! Vous perdez 30 secondes", Toast.LENGTH_LONG).show();
                        }
                    }
                //}
                return false;
            }
        });


    }



}
