package me.sofianehamadi.flyingbird.GameObject;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

import me.sofianehamadi.flyingbird.GameView;
import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.Util;

/**
 * Created by MISTERSOFT on 18/02/2017.
 */

public class Coin extends GameObject {

    private static final int SPEED = 20;
    private static final int NUMBER_COIN_ROWS = 4;

//    private static int LEFT_OUTSIDE_MIN_SCREEN_WIDTH;
//    private static int LEFT_OUTSIDE_MAX_SCREEN_WIDTH;
//    private static int LEFT_OUTSIDE_MIN_SCREEN_HEIGHT;
//    private static int LEFT_OUTSIDE_MAX_SCREEN_HEIGHT;
    private static int COIN_SIZE = 64;
    private Rect bounds; // coin area

    private static ArrayList<Bitmap> sprites;
    private int currentSprite;
    private static Random random;

    public Coin(Context context, GameView view, @Nullable ArrayList<Bitmap> _sprites) {
        super(context, view);
        sprites = new ArrayList<>();
        random = new Random();
//        LEFT_OUTSIDE_MIN_SCREEN_WIDTH = view.getWidth() + 20;
//        LEFT_OUTSIDE_MAX_SCREEN_WIDTH = LEFT_OUTSIDE_MIN_SCREEN_WIDTH * 2;
//        LEFT_OUTSIDE_MIN_SCREEN_HEIGHT = 0;
//        LEFT_OUTSIDE_MAX_SCREEN_HEIGHT = view.getHeight();
        this.bounds = new Rect(0, y, 0, y);
        // Generate default sprites
        if (_sprites == null) {
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin1, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin2, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin3, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin4, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin5, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin6, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin7, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin8, COIN_SIZE, COIN_SIZE));
            sprites.add(Util.getScaledBitmapAlpha8(context, R.drawable.coin9, COIN_SIZE, COIN_SIZE));
        }
        this.currentSprite = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprites.get(this.currentSprite), this.x, this.y, null);
        if (currentSprite < sprites.size() - 1) {
            this.currentSprite++;
        }
        else {
            this.currentSprite = 0;
        }
    }

    @Override
    public void move() {
        this.x -= SPEED;
    }

    public void generateRandomPosition(Canvas canvas) {
        int minOutsideWidth = canvas.getWidth() + 20;
        int maxOutsideWidth = minOutsideWidth * 2;
//        int maxOutsideHeight = canvas.getHeight();
        int[] rowsHeight = this.generateRows(canvas.getHeight());

        this.x = random.nextInt(maxOutsideWidth - minOutsideWidth) + minOutsideWidth;
//        this.y = random.nextInt(maxOutsideHeight);
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

    public Rect getBounds() {
        return bounds;
    }
}
