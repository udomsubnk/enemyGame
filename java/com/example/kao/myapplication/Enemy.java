package com.example.kao.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**
 * Created by kao on 9/25/2017 AD.
 */

public class Enemy {
    private String TAG = "mx enemy";
    private String bitmap;

    private int x;
    private int y;

    private int maxX;
    private int maxY;

    public Enemy(Context context, int screenWid, int screenHeight, int id){
        this.maxX = screenWid;
        this.maxY = screenHeight;

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        Random generator = new Random();
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public Bitmap getBitmap(){
        return Bitmap;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
