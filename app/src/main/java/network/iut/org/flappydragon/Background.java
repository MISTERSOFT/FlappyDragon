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

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background, new Rect(0, 0, background.getWidth(), background.getHeight()), new Rect(0, 0, view.getWidth(), view.getHeight()), null);
    }
}
