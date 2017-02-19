package me.sofianehamadi.flyingbird.GameObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import me.sofianehamadi.flyingbird.GameView;

/**
 * Created by MISTERSOFT on 12/02/2017.
 */

public abstract class GameObject {

    private final byte frameTime;
    private int frameTimeCounter;

    protected int x;
    protected int y;

    protected GameView view;
    protected Context context;

    public GameObject(Context context, GameView view) {
        this.context = context;
        this.view = view;

        this.frameTime = 3; // the frame will change every 3 runs
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    protected void changeToNextFrame() {
        this.frameTimeCounter++;
        if(this.frameTimeCounter >= this.frameTime){
            //TODO Change frame
            this.frameTimeCounter = 0;
        }
    }

    public abstract void draw(Canvas canvas);

    public abstract void move();
}
