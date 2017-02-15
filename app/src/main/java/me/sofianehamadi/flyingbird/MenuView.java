package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by MISTERSOFT on 10/02/2017.
 */

public class MenuView extends AppView {

    public MenuView(Context context) {
        super(context);
        this.holder = getHolder();
        this.background = new Background(context, this, R.drawable.game_background);
        new Thread(new Runnable() {
            @Override
            public void run() {
                MenuView.this.run();
            }
        }).start();
    }

    @Override
    public void run() {
        draw();
    }

    @Override
    protected void drawCanvas(Canvas canvas) {
        super.drawCanvas(canvas);
        canvas.drawText("Start", getWidth() / 2, getHeight() / 2, new Paint());
//        this.background.draw(canvas);
    }
//    private void draw() {
//        while(!holder.getSurface().isValid()){
//			/*wait*/
//            try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
//        }
//        Canvas canvas = holder.lockCanvas();
//        if (canvas != null) {
//            drawCanvas(canvas);
//        }
//        holder.unlockCanvasAndPost(canvas);
//    }
}
