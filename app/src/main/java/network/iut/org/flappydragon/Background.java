package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
    private GameView view;
    private Bitmap background1;

    public Background(Context context, GameView view) {
         background1 = Util.getScaledBitmapAlpha8(context, R.drawable.layer1);
        this.view = view;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background1, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, view.getWidth(), view.getHeight()), null);
    }
}
