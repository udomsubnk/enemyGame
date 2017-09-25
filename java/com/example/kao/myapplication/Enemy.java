package com.example.kao.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by kao on 9/25/2017 AD.
 */

public class Enemy {
    private String TAG = "mx enemy";
    private static final float ICON_WIDTH_DIVIDE_FACTOR = (float) 4.25;
    private Bitmap bitmap;

    private int x;
    private int y;

    private int maxX;
    private int maxY;

    public Enemy(Context context, int screenWid, int screenHeight, int id){
        this.maxX = screenWid;
        this.maxY = screenHeight;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int scrWidth = metrics.widthPixels;
        int iconW = (int)(scrWidth / ICON_WIDTH_DIVIDE_FACTOR);
        int bmpW = bitmap.getWidth();
        float scaleFactor = (float) iconW/bmpW;

        bitmap = Bitmap.createScaledBitmap(bitmap, iconW, (int)(bitmap.getHeight() * scaleFactor), true);

        Random generator = new Random();
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed){
        y += playerSpeed;
        if(y > maxY - bitmap.getHeight()){
            y = 0;
        }
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
