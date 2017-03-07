package com.facebook.mahmud.rifat.mahmud.gamebarebone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by RAM on 1/22/2017.
 */public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;

    //adding the player to this class
    private Player player;

    //These objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    public GameView(Context context, int screenX, int screenY) {
        super(context);

        //initializing player object
        //this time also passing screen size to player constructor
//        player = new Player(context, (int)screenX, (int)screenY);
        player = new Player(context, screenX, screenY);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();
    }
    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        //updating player position
        player.update();
        Log.d("App","update");

    }

    private void draw() {
        //checking if surface is valid
        Log.d("App","draw");
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            //drawing a background color for canvas
            canvas.drawColor(Color.BLACK);
            //Drawing the player
            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);
            //Unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        Log.d("App","control");

        try {
            gameThread.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                //When the user presses on the screen
                //we will do something here
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                //When the user releases the screen
                //do something here
                Toast.makeText(getContext(),"down",Toast.LENGTH_SHORT).show();
                player.setBoosting();
                break;
        }
        return true;
    }
}
