package me.sofianehamadi.flyingbird.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import me.sofianehamadi.flyingbird.views.GameView;
import me.sofianehamadi.flyingbird.common.Util;

/**
 * Created by MISTERSOFT on 16/02/2017.
 */

public abstract class GUIObject {
    private final byte frameTime;
    private int frameTimeCounter;

    protected int x;
    protected int y;
    protected Bitmap image;
    protected GameView view;
    protected Context context;

    public GUIObject(Context context, GameView view, int imageId) {
        this.context = context;
        this.image = Util.getScaledBitmapAlpha8(context, imageId);
        this.view = view;
        this.frameTime = 3;
    }

    public GUIObject(Context context, GameView view, int imageId, int height, int width) {
        this.image = Util.getScaledBitmapAlpha8(context, imageId, height, width);
        this.view = view;
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