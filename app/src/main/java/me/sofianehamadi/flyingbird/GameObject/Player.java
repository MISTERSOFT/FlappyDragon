package me.sofianehamadi.flyingbird.GameObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

import me.sofianehamadi.flyingbird.GameView;
import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.Util;

public class Player extends GameObject {
    /** Static bitmap to reduce memory usage. */
    public static ArrayList<Bitmap> globalBitmap;
    private Bitmap bitmap;
    private Rect bounds; // define player area
    private float speedX;
    private float speedY;

    public Player(Context context, GameView view) {
        super(context, view);
        if(globalBitmap == null) {
            globalBitmap = new ArrayList<>();
            globalBitmap.add(Util.getScaledBitmapAlpha8(context, R.drawable.frame1));
            globalBitmap.add(Util.getScaledBitmapAlpha8(context, R.drawable.frame2));
        }
        this.bitmap = globalBitmap.get(1);
        this.y = context.getResources().getDisplayMetrics().heightPixels / 2;	// Startposition in the middle of the screen
        this.bounds = new Rect(bitmap.getHeight(), 0, bitmap.getWidth(), 0);
        this.x = view.getWidth() / 6;
        this.speedX = 0;
    }

    public void onTap() {
        this.speedY = getTabSpeed();
        this.y += getPosTabIncrease();
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
        Log.i("Position", "X : " + x + " | Y : " + y);
        int top = y;
        int bot = top + bitmap.getHeight();
        this.bounds.set(x, top, bitmap.getWidth(), bot);
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
        if (this.y > 950) {
            // player died
            Log.i("Move", "You died");
        }
        else {
            // move
            this.x += speedX;
            this.y += speedY;
        }
        // player cannot move upper than the visible screen
        if (this.y < 0) {
            this.y = 0;
        }
//        Log.i("Move position", "X : " + this.x + " | Y : " + this.y);
        Log.i("Bounds size", ""+bounds.width() + " w | "+bounds.height() + " h");
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
        // the GUI is updated in the GameView
    }

    @Override
    public void draw(Canvas canvas) {
        this.bitmap = globalBitmap.get(this.frameTimeCounter % 2);
        canvas.drawBitmap(bitmap, x, y , null);
        Paint p = new Paint();
        p.setColor(Color.argb(125, 50, 50, 50));
        canvas.drawRect(bounds, p);
    }

    public Rect getBounds() {
        return bounds;
    }
}
