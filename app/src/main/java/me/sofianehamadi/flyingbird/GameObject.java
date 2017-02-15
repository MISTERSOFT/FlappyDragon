package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by MISTERSOFT on 12/02/2017.
 */

public abstract class GameObject {

    private final byte frameTime;
    private int frameTimeCounter;

    protected int healthpoints;
    protected final int width;
    protected final int height;
    protected int x;
    protected int y;
    protected float speedX;
    protected float speedY;

    protected GameView view;
    protected Context context;

    protected ArrayList<Bitmap> sprites;
    protected Bitmap currentSprite;


    public GameObject(Context context, GameView view, ArrayList<Bitmap> _sprites, int health) {
        this.context = context;
        this.view = view;
        this.sprites = _sprites;
        this.currentSprite = this.sprites.get(0);
        this.width = this.currentSprite.getWidth();
        this.height = this.currentSprite.getHeight();
        this.healthpoints = health;

        this.frameTime = 3; // the frame will change every 3 runs
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
