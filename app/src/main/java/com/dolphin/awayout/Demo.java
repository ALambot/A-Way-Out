package com.dolphin.awayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Demo extends AppCompatActivity {

    private ImageView imgKey, imgChest;
    private TextView timer;
    private Button demo_button;
    InventoryAdapt adapt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


        imgKey = (ImageView) findViewById(R.id.key_demo);
        imgChest = (ImageView) findViewById(R.id.chest_demo);


        ArrayList<MyObject> inventObject = new ArrayList<>();
        String longDesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean laoreet dui vitae leo imperdiet egestas non ut metus. Fusce id orci et lorem efficitur consequat quis quis nunc. Nam aliquet ante a ante convallis semper. Cras non elementum dolor. Aenean ornare nisl nec ex accumsan interdum. Sed eu libero eros. Pellentesque luctus, quam eget elementum auctor, nibh orci interdum quam, eget venenatis dui nunc sed ante. Etiam bibendum consectetur tortor eget finibus. Vestibulum ornare tincidunt tristique. In hac habitasse platea dictumst. Vivamus semper erat id leo feugiat, sagittis eleifend ipsum mollis. Sed cursus tincidunt lobortis. Sed consequat at justo sed sagittis. Fusce a tempus est, sed semper lacus.\n" +
                "\n" +
                "Maecenas laoreet augue eu massa convallis sollicitudin. Praesent lacinia mauris sed nisl ullamcorper interdum. Vestibulum ut lectus vitae justo rhoncus viverra vitae eget dui. Suspendisse potenti. Morbi fringilla tempor nibh id vehicula. In non dolor semper, blandit felis et, lacinia tellus. Nam quis eleifend ligula. Aliquam nec viverra lectus, in gravida ipsum. Aenean varius vitae purus vel feugiat. Aenean eleifend, nulla non fermentum eleifend, tortor dolor tristique tellus, in laoreet justo elit ut turpis. Maecenas id erat at lectus tempus laoreet sit amet aliquam ex.";
        inventObject.add(new MyObject("cle", "Ceci est une clé", R.drawable.key_demo));
        inventObject.add(new MyObject("cle2", "Ceci est une autre clé", R.drawable.key_demo));
        inventObject.add(new MyObject("coffre", longDesc, R.drawable.chest_demo));
        inventObject.add(new MyObject("coffre2", "Ceci est un autre coffre", R.drawable.chest_demo));

        adapt = new InventoryAdapt(this, inventObject);
        final ListView view = (ListView) findViewById(R.id.listinventory);
        final InventoryListView inventor = new InventoryListView(view, adapt, true);

        GameState.getGameState().setInventory(adapt);

        demo_button=(Button) findViewById(R.id.buttonMenuInventory);

        timer = (TextView) findViewById(R.id.timer);
        timerLoop();

    }

    public void onButtonInventoryMenuClick(View view) {
        Intent intent = new Intent(Demo.this, InventoryMenu.class);
        startActivity(intent);
    }

    public void timerLoop(){

        Runnable Loop = new Runnable(){
            @Override
            public void run(){
                while(true){
                    try {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                    }
                    timer.post(new Runnable() {
                        @Override
                        public void run(){
                            GameState gameState = GameState.getGameState();
                            long time = gameState.getRemainingTime();
                            int sec = (int) (time%60);
                            int min = (int) ((time - sec)/60);
                            timer.setText(min+":"+sec);
                            timer.postInvalidate();
                        }
                    });
                }
            }
        };
        Thread myThread = new Thread(Loop);
        myThread.start();
    }
}



