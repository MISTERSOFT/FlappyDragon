package me.sofianehamadi.flyingbird.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import me.sofianehamadi.flyingbird.ui.Background;

/**
 * Created by MISTERSOFT on 10/02/2017.
 */

/**
 * Represent a base view
 */
public abstract class AppView extends SurfaceView implements Runnable, SurfaceHolder.Callback {
    /**
     * SurfaceView height
     */
    protected Integer surfaceViewHeight;
    /**
     * SurfaceView width
     */
    protected Integer surfaceViewWidth;
    /**
     * Thread that manage the surface drawable
     */
    protected Thread viewThread;
    /**
     * View
     */
    protected View view;
    /**
     * SurfaceHolder
     */
    protected SurfaceHolder holder;
    /**
     * Background image
     */
    protected Background background;
    /**
     * Context
     */
    protected Context context;

    /**
     * Constructor
     * @param context Context
     */
    public AppView(Context context) {
        super(context);
        this.context = context;
        this.holder = getHolder();
    }

    /**
     * Run the thread
     */
    @Override
    public abstract void run();

    /**
     * Draw on canvas
     */
    protected void draw() {
        while(!holder.getSurface().isValid()){
			/*wait*/
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            drawCanvas(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }

    /**
     * Draw background
     * @param canvas Canvas
     */
    protected void drawCanvas(Canvas canvas) {
        this.background.draw(canvas);
    }
}
