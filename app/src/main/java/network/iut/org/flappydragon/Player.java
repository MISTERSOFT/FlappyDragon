package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class Player {
    /** Static bitmap to reduce memory usage. */
    public static ArrayList<Bitmap> globalBitmap;
    private Bitmap bitmap;
    private final byte frameTime;
    private int frameTimeCounter;
    private final int width;
    private final int height;
    private int x;
    private int y;
    private float speedX;
    private float speedY;
    private GameView view;
    private int nextPlayerFrame;

    public Player(Context context, GameView view) {
        if(globalBitmap == null) {
            globalBitmap = new ArrayList<>();
            globalBitmap.add(Util.getScaledBitmapAlpha8(context, R.drawable.frame1));
            globalBitmap.add(Util.getScaledBitmapAlpha8(context, R.drawable.frame2));
        }
        this.bitmap = globalBitmap.get(1);
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
        this.frameTime = 3;		// the frame will change every 3 runs
        this.y = context.getResources().getDisplayMetrics().heightPixels / 2;	// Startposition in the middle of the screen

        this.view = view;
        this.x = this.view.getWidth() / 6;
        this.speedX = 0;
    }

    public void onTap() {
        this.speedY = getTabSpeed();
        this.y += getPosTabIncrease();
    }

    private float getPosTabIncrease() {
        return - view.getHeight() / 100;
    }

    private float getTabSpeed() {
        return -view.getHeight() / 16f;
    }

    public void move() {
        changeToNextFrame();

        if(speedY < 0){
            // The character is moving up
            Log.i("Move", "Moving up");
            speedY = speedY * 2 / 3 + getSpeedTimeDecrease() / 2;
        }else{
            // the character is moving down
            Log.i("Move", "Moving down");
            this.speedY += getSpeedTimeDecrease();
        }
        if(this.speedY > getMaxSpeed()){
            // speed limit
            this.speedY = getMaxSpeed();
        }

        // bottom of the screen
        if (this.y > 950) {
            // player died
            Log.i("Move", "You died");
        }
        else {
            // move
            this.x += speedX;
            this.y += speedY;
        }
        Log.i("Move position", "X : " + this.x + " | Y : " + this.y);

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

    protected void changeToNextFrame(){
        this.frameTimeCounter++;
        if(this.frameTimeCounter >= this.frameTime){
            //TODO Change frame
            this.frameTimeCounter = 0;
        }
    }

    private float getSpeedTimeDecrease() {
        return view.getHeight() / 320;
    }

    private float getMaxSpeed() {
        return view.getHeight() / 51.2f;
    }

    public void draw(Canvas canvas) {
        this.bitmap = globalBitmap.get(this.frameTimeCounter % 2);
        canvas.drawBitmap(bitmap, x, y , null);
    }
}
