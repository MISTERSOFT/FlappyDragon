package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

import java.util.List;

import me.sofianehamadi.flyingbird.common.AudioGame;
import me.sofianehamadi.flyingbird.views.GameView;

public class Player extends GameObject {
    /**
     * Current speed on X axe
     */
    private float speedX;
    /**
     * Current speed on Y axe
     */
    private float speedY;
    /**
     * Define if the player is alive or not
     */
    private boolean dead;
    /**
     * Max rotation allowed 45 degres and -45 degres
     */
    private static final float MAX_ROTATION = 45.0f;
    /**
     * Matrix that manage the rotation
     */
    private Matrix playerRotation;
    /**
     * Current rotation
     */
    private float rotation;

    /**
     * Contructor
     * @param context Context
     * @param view View
     * @param sprites List of player sprites
     * @param audioGame AudioGame
     */
    public Player(Context context, GameView view, List<Bitmap> sprites, AudioGame audioGame) {
        super(context, view, sprites, audioGame);

        this.y = context.getResources().getDisplayMetrics().heightPixels / 2;	// Startposition in the middle of the screen
        this.x = view.getWidth() / 6;
        this.speedX = 0;
        this.dead = false;
        // Default rotation degres
        this.playerRotation = new Matrix();
        this.rotation = 0;
        this.playerRotation.postTranslate(-this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, -this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
        this.playerRotation.postRotate(this.rotation);
        this.playerRotation.postTranslate(this.x + this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, this.y + this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
    }

    /**
     * Check if the player is alive or not
     * @return boolean
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Set player death
     * @param dead boolean
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Event that need to be called when the screen is touched
     */
    public void onTap() {
        if (!dead) {
            this.audio.playFX(AudioGame.PLAYER_JUMP);
            this.speedY = getTapSpeed();
            this.y += getPosTapIncrease();
            this.updateRotation();
        }
    }

    /**
     * Get the position value to add on Y axe on each tap
     * @return
     */
    private float getPosTapIncrease() {
        return -super.view.getHeight() / 100;
    }

    /**
     * Get the decrease speed value (when the player is falling)
     * @return A speed value
     */
    private float getSpeedTimeDecrease() {
        return super.view.getHeight() / 320;
    }

    /**
     * Get the speed to add on each tap
     * @return A speed value
     */
    private float getTapSpeed() {
        return -super.view.getHeight() / 16f;
    }

    /**
     * Get maximum speed to reach
     * @return
     */
    private float getMaxSpeed() {
        return super.view.getHeight() / 51.2f;
    }

    /**
     * Move the player
     */
    @Override
    public void move() {
        super.changeToNextFrame();

        this.hitbox.set(
                this.x,
                this.y,
                this.x + this.gameObjectSprites.get(this.currentSprite).getWidth(),
                this.y + this.gameObjectSprites.get(this.currentSprite).getHeight());

        if(speedY < 0){
            // The character is moving up
            speedY = speedY * 2 / 3 + getSpeedTimeDecrease() / 2;
        }else{
            // the character is moving down
            this.speedY += getSpeedTimeDecrease();
        }
        if(this.speedY > getMaxSpeed()){
            // speed limit
            this.speedY = getMaxSpeed();
        }

        // player cannot fall over the visible screen
        if (this.y > this.view.getHeight() - 100) {
            // player died
            this.dead = true;
        }
        else {
            // move
            this.x += speedX;
            this.y += speedY;
            this.updateRotation();
        }
        // player cannot move upper than the visible screen
        if (this.y < 0) {
            this.y = 0;
            this.updateRotation();
        }
    }

    /**
     * Pick up a coin.
     * Then generate a new position for the coin and play the sound.
     * @param coin Coin
     * @param canvas Canvas
     */
    public void pickUpCoin(Coin coin, Canvas canvas) {
        // give a new position to the coin outside of the screen
        coin.generateRandomPosition(canvas);
        coin.playSound();
    }

    /**
     * Update player rotation
     */
    private void updateRotation() {
        // Calculate rotation value
        if (speedY > getMaxSpeed()) {
            this.rotation = MAX_ROTATION;
        } else if (speedY < -getMaxSpeed()) {
            this.rotation = -MAX_ROTATION;
        } else if (speedY == 0) {
            this.rotation = 0;
        } else if (speedY >  0) {
            this.rotation += MAX_ROTATION / getMaxSpeed();
        } else {
            this.rotation -= -(MAX_ROTATION / getMaxSpeed());
        }

        this.playerRotation.reset();
        this.playerRotation.postTranslate(-this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, -this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
        this.playerRotation.postRotate(this.rotation);
        this.playerRotation.postTranslate(this.x + this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, this.y + this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
    }

    /**
     * Draw player on canvas
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.gameObjectSprites.get(this.currentSprite), this.playerRotation, null);

        // Debug - Show player hitbox
//        Paint p = new Paint();
//        p.setColor(Color.argb(125, 50, 50, 50));
//        canvas.drawRect(this.hitbox, p);

        // Change current sprite to next
        this.nextSprite();
    }

}
