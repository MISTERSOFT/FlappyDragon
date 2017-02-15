package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceView;

public class Background {
    private SurfaceView view;
    private Bitmap background;

    public Background(Context context, SurfaceView view, int backgroundId) {

        background = Util.getScaledBitmapAlpha8(context, backgroundId);
        this.view = view;
    }

//    public void draw(Canvas canvas) {
//        canvas.drawBitmap(background, new Rect(0, 0, background.getWidth(), background.getHeight()), new Rect(0, 0, view.getWidth(), view.getHeight()), null);
//    }

    public void draw(Canvas canvas, int offsetX) {
        Rect src = new Rect(0, 0, view.getWidth(), view.getHeight());
        src.offsetTo(offsetX, 0);
        Rect dest = new Rect(0, 0, view.getWidth(), view.getHeight());
        canvas.drawBitmap(this.background, src, dest, null);
    }

    public void draw(Canvas canvas) {
        Rect src = new Rect(0, 0, view.getWidth(), view.getHeight());
        Rect dest = new Rect(0, 0, view.getWidth(), view.getHeight());
        canvas.drawBitmap(this.background, src, dest, null);
    }

    public Bitmap getBackground() {
        return background;
    }
}
