package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.HashMap;

import me.sofianehamadi.flyingbird.GUI.ButtonStates;
import me.sofianehamadi.flyingbird.GUI.ButtonTypes;

/**
 * Created by MISTERSOFT on 10/02/2017.
 */

public class MenuView extends AppView {

    private static HashMap<ButtonTypes, HashMap<ButtonStates, Bitmap>> buttons;

    public MenuView(Context context) {
        super(context);
        /**
         * Load background menu
         */
        this.background = new Background(context, this, R.drawable.game_background);

        /**
         * Load button images
         */
        buttons = new HashMap<>();
        HashMap<ButtonStates, Bitmap> temp = new HashMap<>();
        // Play button
        temp.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.play_pressed));
        temp.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.play_unpressed));
        buttons.put(ButtonTypes.PLAY, temp);
        // Scoreboard button
        temp.clear();
        temp.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.scoreboard_pressed));
        temp.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.scoreboard_unpressed));
        buttons.put(ButtonTypes.SCOREBOARD, temp);
        // About button
        temp.clear();
        temp.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.about_pressed));
        temp.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.about_unpressed));
        buttons.put(ButtonTypes.ABOUT, temp);

        temp.clear();

        /**
         * Start the game menu in a new thread
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                MenuView.this.run();
            }
        }).start();
    }

    @Override
    public void run() {
        super.draw();
    }

    @Override
    protected void drawCanvas(Canvas canvas) {
        super.drawCanvas(canvas);
        this.background.draw(canvas);
        Bitmap b = buttons.get(ButtonTypes.PLAY).get(ButtonStates.PRESSED);
//        Bitmap b = Util.getScaledBitmapAlpha8(getContext(), R.drawable.play_unpressed);
        canvas.drawBitmap(b, getWidth() / 2, getHeight() / 3, new Paint());

//        canvas.drawText("Start", getWidth() / 2, getHeight() / 2, new Paint());
    }

}
