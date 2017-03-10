package me.sofianehamadi.flyingbird.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sofianehamadi.flyingbird.views.GameView;

/**
 * Created by MISTERSOFT on 10/03/2017.
 */

public class Enemy extends GameObject {
    private final float MAX_SPEED = 21.2f;
    private final float INCREASE_SPEED = 0.1f;
    private float currentSpeed;

    private Random random;

    public Enemy(Context context, GameView view, List<Bitmap> sprites) {
        super(context, view, sprites);

        this.random = new Random();
        this.currentSpeed = 80.0f; // start speed
        this.x = -200;
        this.y = 200;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.gameObjectSprites.get(this.currentSprite), this.x, this.y, null);
        // Debug - Show player hitbox
//        Paint p = new Paint();
//        p.setColor(Color.argb(125, 50, 50, 50));
//        canvas.drawRect(this.hitbox, p);

        this.nextSprite();
    }

    @Override
    public void move() {
        this.hitbox.set(
            this.x,
            this.y,
            this.x + this.gameObjectSprites.get(this.currentSprite).getWidth(),
            this.y + this.gameObjectSprites.get(this.currentSprite).getHeight());

        if (this.currentSpeed < MAX_SPEED) {
            this.currentSpeed = MAX_SPEED;
        }
        this.x += this.getSpeed();
    }

    private float getSpeed() {
        return -super.view.getWidth() / this.currentSpeed;
    }

    public void increaseSpeed() {
        this.currentSpeed -= INCREASE_SPEED;
    }

    public void resetPosition() {
        int minOutsideWidth = view.getWidth() + 20;
        int minOutsideHeight = 50;
        int maxOutsideWidth = minOutsideWidth * 2;
        int maxOutsideHeight = view.getHeight() - 100;
        this.y = random.nextInt(maxOutsideHeight - minOutsideHeight) + minOutsideHeight;
        this.x = random.nextInt(maxOutsideWidth - minOutsideWidth) + minOutsideWidth;
    }
}
