package me.sofianehamadi.flyingbird.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

import me.sofianehamadi.flyingbird.common.Util;

/**
 * Represent the background of the game
 */
public class Background {
    /**
     * View
     */
    private SurfaceView view;
    /**
     * Image of the background
     */
    private Bitmap background;

    /**
     * Constructor
     * @param context Context
     * @param view View
     * @param backgroundId Resource id of the background
     */
    public Background(Context context, SurfaceView view, int backgroundId) {
        background = Util.getScaledBitmapAlpha8(context, backgroundId);
        this.view = view;
    }

    /**
     * Draw the background
     * @param canvas Canvas
     * @param offsetX Offset to give on X axe
     */
    public void draw(Canvas canvas, int offsetX) {
        Rect src = new Rect(0, 0, view.getWidth(), view.getHeight());
        src.offsetTo(offsetX, 0);
        Rect dest = new Rect(0, 0, view.getWidth(), view.getHeight());
        canvas.drawBitmap(this.background, src, dest, null);
    }

    /**
     * Draw the background
     * @param canvas Canvas
     */
    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, view.getWidth(), view.getHeight());
        Rect dest = new Rect(0, 0, view.getWidth(), view.getHeight());
        canvas.drawBitmap(this.background, src, dest, null);
    }

    /**
     * Get the background image
     * @return Bitmap
     */
    public Bitmap getBackground() {
        return background;
    }
}
