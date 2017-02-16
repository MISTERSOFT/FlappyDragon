package me.sofianehamadi.flyingbird.GUI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import me.sofianehamadi.flyingbird.GameView;
import me.sofianehamadi.flyingbird.Util;

/**
 * Created by MISTERSOFT on 16/02/2017.
 */

public abstract class GUIObject {
    private final byte frameTime;
    private int frameTimeCounter;

    protected int x;
    protected int y;
    protected Bitmap image;

    public GUIObject(Context context, int imageId) {
        this.image = Util.getScaledBitmapAlpha8(context, imageId);
        this.frameTime = 3;
    }

    public GUIObject(Context context, int imageId, int height, int width) {
        this.image = Util.getScaledBitmapAlpha8(context, imageId, height, width);
        this.frameTime = 3;
    }

    protected void changeToNextFrame() {
        this.frameTimeCounter++;
        if(this.frameTimeCounter >= this.frameTime){
            //TODO Change frame
            this.frameTimeCounter = 0;
        }
    }

    public abstract void draw(Canvas canvas);
}