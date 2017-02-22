package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageButton;

import java.util.HashMap;

import me.sofianehamadi.flyingbird.GUI.ButtonStates;
import me.sofianehamadi.flyingbird.GUI.ButtonTypes;

/**
 * Created by MISTERSOFT on 10/02/2017.
 */

public class MenuView extends AppView {

//    private static HashMap<ButtonTypes, HashMap<ButtonStates, Bitmap>> buttons;
    private static final int SPACE_BETWEEN_BUTTONS = 100;
    private static final int BUTTONS_COUNT = 3;
    private static HashMap<ButtonStates, Bitmap> playButton;
    private static HashMap<ButtonStates, Bitmap> scoreboardButton;
    private static HashMap<ButtonStates, Bitmap> aboutButton;

    private static ImageButton btn;

    public MenuView(Context context) {
        super(context);
        /**
         * Load background menu
         */
        this.background = new Background(context, this, R.drawable.game_background);

        /**
         * Load button images
         */
//        btn = new ImageButton(context);
//        btn.setImageBitmap(Util.getScaledBitmapAlpha8(context, R.drawable.play_unpressed));
//        btn.setLeft((getWidth() / 2) - (playButton.get(ButtonStates.UNPRESSED).getWidth() / 2));
//        btn.setTop(getHeight() / BUTTONS_COUNT);

//        playButton = new HashMap<>();
//        playButton.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.play_pressed));
//        playButton.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.play_unpressed));
//
//        scoreboardButton = new HashMap<>();
//        scoreboardButton.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.scoreboard_pressed));
//        scoreboardButton.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.scoreboard_unpressed));
//
//        aboutButton = new HashMap<>();
//        aboutButton.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.about_pressed));
//        aboutButton.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.about_unpressed));
//        buttons = new HashMap<>();
//        HashMap<ButtonStates, Bitmap> temp = new HashMap<>();
        // Play button
//        temp.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.play_pressed));
//        temp.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.play_unpressed));
//        buttons.put(ButtonTypes.PLAY, temp);
//        // Scoreboard button
//        temp.clear();
//        temp.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.scoreboard_pressed));
//        temp.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.scoreboard_unpressed));
//        buttons.put(ButtonTypes.SCOREBOARD, temp);
//        // About button
//        temp.clear();
//        temp.put(ButtonStates.PRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.about_pressed));
//        temp.put(ButtonStates.UNPRESSED, Util.getScaledBitmapAlpha8(context, R.drawable.about_unpressed));
//        buttons.put(ButtonTypes.ABOUT, temp);

//        temp.clear();

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

        // Draw buttons in the center of the screen
//        canvas.drawBitmap(
//                playButton.get(ButtonStates.UNPRESSED), // button bitmap
//                (getWidth() / 2) - (playButton.get(ButtonStates.UNPRESSED).getWidth() / 2), // X axe position
//                getHeight() / BUTTONS_COUNT, // Y axe position
//                new Paint());
//        canvas.drawBitmap(
//                scoreboardButton.get(ButtonStates.UNPRESSED),
//                (getWidth() / 2) - (scoreboardButton.get(ButtonStates.UNPRESSED).getWidth() / 2),
//                getHeight() / BUTTONS_COUNT + SPACE_BETWEEN_BUTTONS,
//                new Paint());
//        canvas.drawBitmap(
//                aboutButton.get(ButtonStates.UNPRESSED),
//                (getWidth() / 2) - (aboutButton.get(ButtonStates.UNPRESSED).getWidth() / 2),
//                getHeight() / BUTTONS_COUNT + SPACE_BETWEEN_BUTTONS * 2,
//                new Paint());
    }

}
