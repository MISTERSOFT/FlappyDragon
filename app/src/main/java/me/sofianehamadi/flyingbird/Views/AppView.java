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

public abstract class AppView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    protected Integer surfaceViewHeight;
    protected Integer surfaceViewWidth;
    protected Thread viewThread;
    protected View view;
    protected SurfaceHolder holder;
    protected Background background;
    protected Context context;

    public AppView(Context context) {
        super(context);
        this.context = context;
        this.holder = getHolder();
    }

    @Override
    public abstract void run();

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

    protected void drawCanvas(Canvas canvas) {
        this.background.draw(canvas);
    }
}
