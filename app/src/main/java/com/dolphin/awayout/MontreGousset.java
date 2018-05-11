package com.dolphin.awayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Calendar;

/**
 * Created by antoine on 08.05.18.
 */

public class MontreGousset extends AppCompatActivity{

    private ImageView aiguilleH;
    private ImageView aiguilleM;

    private int H = 6;
    private int M = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_montre_gousset_enigme);

        aiguilleH=findViewById(R.id.imageMGH);
        aiguilleM=findViewById(R.id.imageMGM);

        H = (H+12)%12;
        M = (M+60)%60;

        aiguilleH.setRotation(H*360/12);
        aiguilleM.setRotation(M*360/12);
    }

    public void bHL(View view) {
        H--;
        H = (H+12)%12;
        aiguilleH.setRotation(H*360/12);
        //Log.d("KEK", "H : " + H + " " + aiguilleH.getRotation());
    }

    public void bHR(View view) {
        H++;
        H = (H+12)%12;
        aiguilleH.setRotation(H*360/12);
        //Log.d("KEK", "H : " + H + " " + aiguilleH.getRotation());
    }

    public void bML(View view) {
        M--;
        M = (M+60)%60;
        aiguilleM.setRotation(M*360/60);
        //Log.d("KEK", "M : " + M + " " + aiguilleM.getRotation());
    }

    public void bMR(View view) {
        M++;
        M = (M+60)%60;
        aiguilleM.setRotation(M*360/60);
        //Log.d("KEK", "M : " + M + " " + aiguilleM.getRotation());
    }

    public void OK(View view) {
        Calendar now = Calendar.getInstance();
        int curH = now.get(Calendar.HOUR);
        int curM = now.get(Calendar.MINUTE);
        Log.d("KEK","NOW - H : "+curH+" - M : "+curM);

        int mirrorH = 12 - H;
        int mirrorM = 60 - M;
        mirrorH = (mirrorH+12)%12;
        mirrorM = (mirrorM+60)%60;
        Log.d("KEK","APP - H : "+mirrorH+" - M : "+mirrorM);

        if(mirrorH == curH && mirrorM == curM){
            GameState.getGameState().getInteractions().enigmeSuccess("Montre gousset");
        }
        else{
            // TODO
        }
    }
}
