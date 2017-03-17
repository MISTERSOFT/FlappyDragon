package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

import java.util.List;

import me.sofianehamadi.flyingbird.common.AudioGame;
import me.sofianehamadi.flyingbird.views.GameView;

public class Player extends GameObject {
    private float speedX;
    private float speedY;
    private boolean dead;
    // Note: Rotation of bitmap -> -45 to 45 degrees
    private static final float MAX_ROTATION = 45.0f;
    private static float ROTATION_SPEED;
    private Matrix playerRotation;
    private float rotation;

    public Player(Context context, GameView view, List<Bitmap> sprites, AudioGame audioGame) {
        super(context, view, sprites, audioGame);

        this.y = context.getResources().getDisplayMetrics().heightPixels / 2;	// Startposition in the middle of the screen
        this.x = view.getWidth() / 6;
        this.speedX = 0;
        this.dead = false;
        // Default rotation degres
        ROTATION_SPEED = getMaxSpeed() * 2;
        this.playerRotation = new Matrix();
        this.rotation = 0;
        this.playerRotation.postTranslate(-this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, -this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
        this.playerRotation.postRotate(this.rotation);
        this.playerRotation.postTranslate(this.x + this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, this.y + this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

//    public Matrix getPlayerRotation() {
//        return playerRotation;
//    }

    public void onTap() {
        if (!dead) {
            this.audio.playFX(AudioGame.PLAYER_JUMP);
            this.speedY = getTabSpeed();
            this.y += getPosTabIncrease();
            this.updateRotation();
        }
    }

    private float getPosTabIncrease() {
        return -super.view.getHeight() / 100;
    }

    private float getTabSpeed() {
        return -super.view.getHeight() / 16f;
    }

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

        // manage frames
/*        if(row != 3){
            // not dead
            if(speedY > getTabSpeed() / 3 && speedY < getMaxSpeed() * 1/3){
                row = 0;
            }else if(speedY > 0){
                row = 1;
            }else{
                row = 2;
            }
        }
*/
    }

    private float getSpeedTimeDecrease() {
        return super.view.getHeight() / 320;
    }

    private float getMaxSpeed() {
        return super.view.getHeight() / 51.2f;
    }

    public void pickUpCoin(Coin coin, Canvas canvas) {
        // give a new position to the coin outside of the screen
        coin.generateRandomPosition(canvas);
        coin.playSound();
    }

    private void updateRotation() {
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

        Log.i("SpeedY", "updateRotation: " + speedY);
        Log.i("MAX_SPEED", "updateRotation: " + getMaxSpeed());
        this.playerRotation.reset();
        this.playerRotation.postTranslate(-this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, -this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
        this.playerRotation.postRotate(this.rotation);
        this.playerRotation.postTranslate(this.x + this.gameObjectSprites.get(this.currentSprite).getWidth() / 2, this.y + this.gameObjectSprites.get(this.currentSprite).getHeight() / 2);
    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawBitmap(this.gameObjectSprites.get(this.currentSprite), x, y , null);
        canvas.drawBitmap(this.gameObjectSprites.get(this.currentSprite), this.playerRotation, null);

        // Debug - Show player hitbox
//        Paint p = new Paint();
//        p.setColor(Color.argb(125, 50, 50, 50));
//        canvas.drawRect(this.hitbox, p);

        this.nextSprite();
    }

}
