package com.dolphin.awayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EnigmeTextChoix extends AppCompatActivity {

    //test 1
    public static EnigmeObject enigme;
    //private EnigmeObject enigme;
    private TextView title;
    private TextView question;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enigme_text_choix);

        title = (TextView) findViewById(R.id.ETCtitle);
        question = (TextView) findViewById(R.id.ETCquestion);
        b1 = (Button) findViewById(R.id.ETCreponse1);
        b2 = (Button) findViewById(R.id.ETCreponse2);
        b3 = (Button) findViewById(R.id.ETCreponse3);
        b4 = (Button) findViewById(R.id.ETCreponse4);
        b5 = (Button) findViewById(R.id.ETCreponse5);
        b6 = (Button) findViewById(R.id.ETCreponse6);

        //test 2 si 1 ne fonctione pas
        /*setContentView(R.layout.enigme_liste);
        Intent intent = getIntent();
        Toast.makeText(EnigmeTextChoix.this, intent.getStringExtra("position"), Toast.LENGTH_SHORT).show();
        //subtest2
        int position = Integer.valueOf(intent.getStringExtra("position"));
        enigme = GameState.getGameState().getEnigmeObjectArrayList().get(position);
*/

        //Toast.makeText(EnigmeTextChoix.this, enigme.getTitle(), Toast.LENGTH_SHORT).show();

        title.setText(enigme.getTitle());
        question.setText(enigme.getText());
        String [] proposition = enigme.getProposition();
        if(proposition != null && proposition.length > 1){
            b1.setOnClickListener(choix);
            b2.setOnClickListener(choix);
            b3.setOnClickListener(choix);
            b4.setOnClickListener(choix);
            b5.setOnClickListener(choix);
            b6.setOnClickListener(choix);

            switch (String.valueOf(proposition.length)){
                case "2" :
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    b4.setVisibility(View.INVISIBLE);
                    b5.setText(proposition[0]);
                    b6.setText(proposition[1]);
                    break;
                case "3":
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b6.setVisibility(View.INVISIBLE);
                    b3.setText(proposition[0]);
                    b4.setText(proposition[1]);
                    b5.setText(proposition[2]);
                    break;
                case "4":
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setText(proposition[0]);
                    b4.setText(proposition[1]);
                    b5.setText(proposition[2]);
                    b6.setText(proposition[3]);
                    break;
                case "5" :
                    b1.setText(proposition[0]);
                    b2.setText(proposition[1]);
                    b3.setText(proposition[2]);
                    b4.setText(proposition[3]);
                    b5.setText(proposition[4]);
                    b6.setVisibility(View.INVISIBLE);
                    break;
                case "6" :
                    b1.setText(proposition[0]);
                    b2.setText(proposition[1]);
                    b3.setText(proposition[2]);
                    b4.setText(proposition[3]);
                    b5.setText(proposition[4]);
                    b6.setText(proposition[5]);
                    break;
                default:
                    Toast.makeText(EnigmeTextChoix.this, "Erreur trop de proposition", Toast.LENGTH_LONG).show();
                    Toast.makeText(EnigmeTextChoix.this, String.valueOf(proposition.length), Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(EnigmeTextChoix.this, "Erreur pas de proposition de solution pour l'enigme", Toast.LENGTH_LONG).show();
        }
    }

    private View.OnClickListener choix = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            String Choix = (String) b.getText();
            if(Choix.equals(enigme.getReponse())){
                Toast.makeText(EnigmeTextChoix.this, "Bonne réponse", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(EnigmeTextChoix.this, "Mauvaise réponse", Toast.LENGTH_LONG).show();
            }
        }
    };


}
