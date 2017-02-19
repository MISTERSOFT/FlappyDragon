package me.sofianehamadi.flyingbird.GUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import me.sofianehamadi.flyingbird.GameView;

/**
 * Created by MISTERSOFT on 16/02/2017.
 */

public class CoinScore extends GUIObject{

    private static final int IMAGE_SIZE = 64;
    private int totalCoins;

    public CoinScore(Context context, GameView view, int imageId, int posX, int posY) {
        super(context, view, imageId, IMAGE_SIZE, IMAGE_SIZE);
        this.totalCoins = 0;
        this.x = posX;
        this.y = posY;
    }

    public int getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(int totalCoins) {
        this.totalCoins = totalCoins;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, this.x, this.y, null);
        Paint text = new Paint();
        text.setTextSize(32);
        canvas.drawText(""+this.totalCoins, this.x + 50, this.y + 45, text);
    }
    
}
