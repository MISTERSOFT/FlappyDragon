package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by MISTERSOFT on 10/02/2017.
 */

public abstract class AppView extends SurfaceView implements Runnable {

    protected View view;
    protected SurfaceHolder holder;
    protected Background background;
    protected Canvas canvas;

    public AppView(Context context) {
        super(context);
        this.holder = getHolder();
    }

    @Override
    public abstract void run();

    protected void draw() {
        while(!holder.getSurface().isValid()){
			/*wait*/
            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
        }
//        Canvas canvas = holder.lockCanvas();
        this.canvas = holder.lockCanvas();
        if (canvas != null) {
            drawCanvas(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
    }

//    protected void drawCanvas(Canvas canvas) {
//        background.draw(canvas);
//    }

    protected void drawCanvas(Canvas canvas) {
        this.background.draw(canvas);
    }
}
