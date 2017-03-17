package me.sofianehamadi.flyingbird.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import me.sofianehamadi.flyingbird.views.GameView;
import me.sofianehamadi.flyingbird.common.Util;

/**
 * Created by MISTERSOFT on 16/02/2017.
 */

/**
 * Represent a Game User Interface element
 */
public abstract class GUIObject {
    /**
     * Current position X
     */
    protected int x;
    /**
     * Current position Y
     */
    protected int y;
    /**
     * Image to display
     */
    protected Bitmap image;
    /**
     * View
     */
    protected GameView view;
    /**
     * Context
     */
    protected Context context;

    /**
     * Constructor
     * @param context Context
     * @param view View
     * @param imageId Resource id of the image
     * @param height Height to the image
     * @param width Width to the image
     */
    public GUIObject(Context context, GameView view, int imageId, int height, int width) {
        this.image = Util.getScaledBitmapAlpha8(context, imageId, height, width);
        this.view = view;
    }

    public abstract void draw(Canvas canvas);
}