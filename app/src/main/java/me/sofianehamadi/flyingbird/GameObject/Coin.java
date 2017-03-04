package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import me.sofianehamadi.flyingbird.views.GameView;

/**
 * Created by MISTERSOFT on 18/02/2017.
 */

public class Coin extends GameObject {

    public static final int COIN_SIZE = 64;

    private static final int SPEED = 20;
    private static final int NUMBER_COIN_ROWS = 4;

    private static int[] rowsHeight;
    private static Random random;

    public Coin(Context context, GameView view, ArrayList<Bitmap> sprites) {
        super(context, view, sprites);
        rowsHeight = new int[0];
        random = new Random();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.gameObjectSprites.get(this.currentSprite), this.x, this.y, null);

//        Log.i("Coin hitbox size", "[x,y]=" + this.x + "," + this.y + " - "+this.hitbox.width() + " w | "+this.hitbox.height() + " h");
        // Debug - Show coin hitbox
//        Paint p = new Paint();
//        p.setColor(Color.argb(125, 50, 50, 50));
//        canvas.drawRect(this.hitbox, p);
    }

    @Override
    public void move() {
//        Log.i("Coin position", "X : " + x + " | Y : " + y);

        this.x -= SPEED;

        // Refresh hitbox position
        this.hitbox.set(
                this.x,
                this.y,
                this.x + this.gameObjectSprites.get(this.currentSprite).getWidth(),
                this.y + this.gameObjectSprites.get(this.currentSprite).getHeight());

        this.nextSprite();
    }

    public void generateRandomPosition(Canvas canvas) {
        int minOutsideWidth = canvas.getWidth() + 20;
        int maxOutsideWidth = minOutsideWidth * 2;
        if (rowsHeight.length == 0) {
            rowsHeight = this.generateRows(canvas.getHeight());
        }

        this.x = random.nextInt(maxOutsideWidth - minOutsideWidth) + minOutsideWidth;
        this.y = rowsHeight[random.nextInt(NUMBER_COIN_ROWS)];
    }

    private int[] generateRows(int canvasHeight) {
        int[] rowsHeight = new int[NUMBER_COIN_ROWS];

        int rowHeight = canvasHeight/ NUMBER_COIN_ROWS;
        for (int i = 0; i < NUMBER_COIN_ROWS; i++) {
            rowsHeight[i] = rowHeight * i;
        }

        return rowsHeight;
    }

//    private int[] generateColumns(int canvasWidth) {
//        int[] columnsWidth = new int[NUMBER_COIN_ROWS];
//
//        int columnWidth = canvasWidth / NUMBER_COIN_ROWS;
//        for (int i = 0; i < NUMBER_COIN_ROWS; i++) {
//            columnsWidth[i] = columnWidth * i;
//        }
//
//        return columnsWidth;
//    }

}
