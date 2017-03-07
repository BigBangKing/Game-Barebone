package com.facebook.mahmud.rifat.mahmud.gamebarebone;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //Initializing game view object
        Display display=getWindowManager().getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);

        gameView = new GameView(this, point.x, point.y);
        //adding it to contentview
        setContentView(gameView);
    }

    //pausing the game when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
