package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;

import me.sofianehamadi.flyingbird.common.AudioGame;
import me.sofianehamadi.flyingbird.views.GameView;

public class Player extends GameObject {
    private float speedX;
    private float speedY;
    private boolean dead;
    // Note: Rotation of bitmap -> -45 to 45 degrees
//    private Matrix playerRotation;
//    private int rotation;

    public Player(Context context, GameView view, List<Bitmap> sprites, AudioGame audioGame) {
        super(context, view, sprites, audioGame);

        this.y = context.getResources().getDisplayMetrics().heightPixels / 2;	// Startposition in the middle of the screen
        this.x = view.getWidth() / 6;
        this.speedX = 0;
        this.dead = false;
//        this.playerRotation = new Matrix();
//        this.rotation = 0;
//        this.playerRotation.postRotate(this.rotation);
//        this.playerRotation.postTranslate(this.x, this.y);
//        this.audio = audioGame;
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
//            this.updateRotation();
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
//        Log.i("Player position", "X : " + x + " | Y : " + y);

        this.hitbox.set(
            this.x,
            this.y,
            this.x + this.gameObjectSprites.get(this.currentSprite).getWidth(),
            this.y + this.gameObjectSprites.get(this.currentSprite).getHeight());

        if(speedY < 0){
            // The character is moving up
//            Log.i("Move", "Moving up");
            speedY = speedY * 2 / 3 + getSpeedTimeDecrease() / 2;
        }else{
            // the character is moving down
//            Log.i("Move", "Moving down");
            this.speedY += getSpeedTimeDecrease();
        }
        if(this.speedY > getMaxSpeed()){
            // speed limit
            this.speedY = getMaxSpeed();
        }

        // player cannot fall over the visible screen
        if (this.y > this.view.getHeight() - 100) {
            // player died
//            Log.i("Move", "You died");
            this.dead = true;
        }
        else {
            // move
            this.x += speedX;
            this.y += speedY;
//            this.updateRotation();
        }
        // player cannot move upper than the visible screen
        if (this.y < 0) {
            this.y = 0;
//            this.updateRotation();
        }
//        Log.i("Move position", "X : " + this.x + " | Y : " + this.y);
//        Log.i("Player hitbox size", ""+this.hitbox.width() + " w | "+this.hitbox.height() + " h");

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

//    private void updateRotation() {
//        this.playerRotation = new Matrix();
//        this.playerRotation.postTranslate(this.x * 2, this.y);
//        this.playerRotation.postRotate(45);
//    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.gameObjectSprites.get(this.currentSprite), x, y , null);
//        canvas.drawBitmap(this.gameObjectSprites.get(this.currentSprite), this.playerRotation, null);

        // Debug - Show player hitbox
//        Paint p = new Paint();
//        p.setColor(Color.argb(125, 50, 50, 50));
//        canvas.drawRect(this.hitbox, p);

        this.nextSprite();
    }

}
