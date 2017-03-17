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

/**
 * Represent a element of the game
 */
public abstract class GameObject {

    /**
     * The frame will change every 3 runs
     */
    private final byte frameTime = 3;
    /**
     * Current frametime count
     */
    private int frameTimeCounter;

    /**
     * Position X
     */
    protected int x;
    /**
     * Position Y
     */
    protected int y;
    /**
     * List of sprites
     */
    protected List<Bitmap> gameObjectSprites;
    /**
     * Current sprites to display
     */
    protected int currentSprite;
    /**
     * Collision box
     */
    protected Rect hitbox;
    /**
     * GameView
      */
    protected GameView view;
    /**
     * Context
     */
    protected Context context;
    /**
     * AudioGame
     */
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

    /**
     * Get the current position of the collision box
     * @return
     */
    public Rect getHitbox() {
        return hitbox;
    }

    /**
     * Get current position on X axe
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * Change frame
     */
    protected void changeToNextFrame() {
        this.frameTimeCounter++;
        if(this.frameTimeCounter >= this.frameTime){
            //TODO Change frame
            this.frameTimeCounter = 0;
        }
    }

    /**
     * Select next sprite to display
     */
    protected void nextSprite() {
        if (currentSprite < gameObjectSprites.size() - 1) {
            this.currentSprite++;
        }
        else {
            this.currentSprite = 0;
        }
    }

    /**
     * Draw the game object into the canvas
     * @param canvas
     */
    public abstract void draw(Canvas canvas);

    /**
     * Move the game object
     */
    public abstract void move();
}
