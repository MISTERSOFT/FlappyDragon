package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.List;

import me.sofianehamadi.flyingbird.common.AudioGame;
import me.sofianehamadi.flyingbird.views.GameView;

/**
 * Created by MISTERSOFT on 12/02/2017.
 */

public abstract class GameObject {

    private final byte frameTime = 3; // the frame will change every 3 runs
    private int frameTimeCounter;

    protected int x;
    protected int y;
    protected List<Bitmap> gameObjectSprites;
    protected int currentSprite;
    protected Rect hitbox;

    protected GameView view;
    protected Context context;

    protected AudioGame audio;

    public GameObject(Context context, GameView view, List<Bitmap> sprites, AudioGame audioGame) {
        this.audio = audioGame;
        this.init(context, view, sprites);
    }

    public GameObject(Context context, GameView view, List<Bitmap> sprites) {
        this.init(context, view, sprites);
    }

    private void init(Context context, GameView view, List<Bitmap> sprites) {
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
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public int getX() {
        return x;
    }

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
