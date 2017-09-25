package com.example.kao.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable{
    static String TAG = "mxapp";
    int screenW, screenH;
    Thread gameThread = null;
    volatile boolean playing;
    SurfaceHolder surfaceHolder;

    Canvas canvas;
    Paint paint;

    double fps = 2.0;

    private Long timeThisFrame;

    Enemy [] enemies;
    int enemyCount = 2;

    public GameView(Context context){
        super(context);
    }

    public GameView(Context context, int screenWid, int screenHeight){
        super(context);
        this.screenW = screenWid;
        this.screenH = screenHeight;

        surfaceHolder = getHolder();
        paint = new Paint();

        playing = true;

//        enemies = new Enemy(enemyCount);
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new Enemy(context, screenWid, screenHeight, i);
        }
    }

    @Override
    public void run(){
        while(playing){
            Long startFrameTime = System.currentTimeMillis();
            update();
            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame > 0){
                fps = 1000 / 6 / timeThisFrame;
            }
            control();
        }
    }

    public void update(){
        Log.d(TAG, "working in update ");

        int movingSpeed = 10;
        for(int i=0; i<enemyCount; i++){
            enemies[i].update(movingSpeed);
        }
    }
    public void draw(){
        Log.d(TAG, "working in draw");
        if( surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 25, 128, 182));
            paint.setColor(Color.argb(255, 245, 129, 0));
            paint.setTextSize(30);
            canvas.drawText("FPS : "+ fps, 20, 40, paint);

            for(int i=0; i<enemyCount; i++){
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    public void control(){
        try{
            gameThread.sleep(140);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void pause(){
        playing = false;
        try{
            gameThread.join();
        } catch(InterruptedException e){
            Log.d("Error : ", "joining thread");
        }
    }
    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
