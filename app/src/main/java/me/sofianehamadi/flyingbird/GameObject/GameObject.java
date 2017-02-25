package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import me.sofianehamadi.flyingbird.views.GameView;

/**
 * Created by MISTERSOFT on 12/02/2017.
 */

public abstract class GameObject {

    protected final byte frameTime;
    protected int frameTimeCounter;

    protected int x;
    protected int y;
    protected ArrayList<Bitmap> gameObjectSprites;
    protected int currentSprite;
    protected Rect hitbox;

    protected GameView view;
    protected Context context;

    public GameObject(Context context, GameView view, ArrayList<Bitmap> sprites) {
        this.context = context;
        this.view = view;
        this.gameObjectSprites = sprites;
        this.currentSprite = 0;
        this.hitbox = new Rect(
            this.x,
            this.y,
            this.x + this.gameObjectSprites.get(this.currentSprite).getWidth(),
            this.y + this.gameObjectSprites.get(this.currentSprite).getHeight()
        );

        this.frameTime = 3; // the frame will change every 3 runs
    }

    public Rect getHitbox() {
        return hitbox;
    }

    // TODO - Remove
//    public void setHitbox(Rect hitbox) {
//        this.hitbox = hitbox;
//    }

    public int getX() {
        return x;
    }

    // TODO - Remove
//    public void setX(int x) {
//        this.x = x;
//    }

    // TODO - Remove
//    public int getY() {
//        return y;
//    }
    // TODO - Remove
//    public void setY(int y) {
//        this.y = y;
//    }

    protected void changeToNextFrame() {
        this.frameTimeCounter++;
        if(this.frameTimeCounter >= this.frameTime){
            //TODO Change frame
            this.frameTimeCounter = 0;
        }
    }

    protected void nextSprite() {
        if (currentSprite < gameObjectSprites.size() - 1) {
            this.currentSprite++;
        }
        else {
            this.currentSprite = 0;
        }
    }

    public abstract void draw(Canvas canvas);

    public abstract void move();
}
