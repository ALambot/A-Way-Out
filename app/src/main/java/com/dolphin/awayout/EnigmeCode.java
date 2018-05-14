package com.dolphin.awayout;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.KeyEvent;
        import android.view.WindowManager;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

public class EnigmeCode extends AppCompatActivity{

    private EditText editText;
    private ListView inventaire;
    private InventoryListView inventair;
    private InventoryAdapt inventoryAdapt;
    private ImageView img;
    public static EnigmeObject enigme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_code);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        GameState.getGameState().enigmeCoffret=this;

        img = (ImageView) findViewById(R.id.imageCoffret);
        img.setImageResource(enigme.getImgId());
        inventaire=findViewById(R.id.listView_enigme_coffret);
        inventoryAdapt = GameState.getGameState().getInventoryAdapt();
        inventair=new InventoryListView(EnigmeCode.this,inventaire,inventoryAdapt, true);
        editText = (EditText)findViewById(R.id.editTextCoffret);
        try {
            Integer.parseInt(enigme.getReponse());
            editText.setInputType(2); //chiffre
        } catch (NumberFormatException e){
            editText.setInputType(1); //string
        }
        editText.setHint(enigme.getHint());

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                InteractionManager interactionManager= GameState.getGameState().getInteractions();
                String reponse = editText.getText().toString();
                if(reponse.length() == enigme.getReponse().length()) {
                    if (reponse.equals(enigme.getReponse())) {
                        Log.d("KEK", "ENIGME - SUCCESS1 - TPS REM : "+GameState.getGameState().getRemainingTime());
                        interactionManager.enigmeSuccess(enigme.getTitle());
                        //TODO creer interaction succes et fail pour coffret
                        Log.d("KEK", "ENIGME - SUCCESS2 - TPS REM : "+GameState.getGameState().getRemainingTime());
                    } else {
                        Log.d("KEK", "ENIGME - FAIL - TPS REM : "+GameState.getGameState().getRemainingTime());
                        interactionManager.enigmeFail(enigme.getTitle());
                        editText.setText("");
                        Toast.makeText(EnigmeCode.this, "Mauvais Code ! Vous perdez du temps", Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });


    }



}

