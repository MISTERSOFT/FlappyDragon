package me.sofianehamadi.flyingbird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.sofianehamadi.flyingbird.GUI.CoinScore;

public class GameView extends AppView {
    public static final long UPDATE_INTERVAL = 50; // = 20 FPS
    private final int DEFAULT_OFFSET_BACKGROUND_ONE = 0;
    private final int DEFAULT_OFFSET_BACKGROUND_TWO;
    private static final int BACKGROUND_PROGRESS_PER_TICK = 10;
    private final int BACKGROUND_WIDTH;

    private int offsetBackgroundOneX;
    private int offsetBackgroundTwoX;

    //    private SurfaceHolder holder;
    private boolean paused = true;
    private TimerTask timerTask;
    private Timer timer = new Timer();
    private ArrayList<Background> backgrounds;

    private Player player;
    private CoinScore coinScore;

    public GameView(Context context) {
        super(context);
        this.player = new Player(context, this);
        this.coinScore = new CoinScore(context, R.drawable.coin1, 20, 20);
        this.backgrounds = new ArrayList<>();
        this.backgrounds.add(new Background(context, this, R.drawable.game_background));
        this.backgrounds.add(new Background(context, this, R.drawable.game_background_revert));
        this.BACKGROUND_WIDTH = this.backgrounds.get(0).getBackground().getWidth();
        this.DEFAULT_OFFSET_BACKGROUND_TWO = -BACKGROUND_WIDTH;
        this.offsetBackgroundOneX = DEFAULT_OFFSET_BACKGROUND_ONE;
        this.offsetBackgroundTwoX = DEFAULT_OFFSET_BACKGROUND_TWO;
//        this.holder = getHolder();
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameView.this.run();
            }
        }).start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(paused) {
                resume();
            } else {
                Log.i("PLAYER", "PLAYER TAPPED");
                this.player.onTap();
            }
        }
        return true;
    }

    private void resume() {
        paused = false;
        startTimer();
    }

    private void startTimer() {
        Log.i("TIMER", "START TIMER");
        setUpTimerTask();
        timer = new Timer();
        timer.schedule(timerTask, UPDATE_INTERVAL, UPDATE_INTERVAL);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    private void setUpTimerTask() {
        stopTimer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                GameView.this.run();
            }
        };
    }

    @Override
    public void run() {
        player.move();
        this.draw();
    }

    @Override
    protected void draw() {
        super.draw();
        this.offsetBackgroundOneX += BACKGROUND_PROGRESS_PER_TICK;
        this.offsetBackgroundTwoX += BACKGROUND_PROGRESS_PER_TICK;
    }

    @Override
    protected void drawCanvas(Canvas canvas) {
        if (this.offsetBackgroundOneX < BACKGROUND_WIDTH) {
            this.backgrounds.get(0).draw(canvas, this.offsetBackgroundOneX);
        }
        // the player will begin to see a black background, so we draw the next background
        // that is fixed on the right side of the previous background
        if (this.offsetBackgroundTwoX < BACKGROUND_WIDTH) {
            this.backgrounds.get(1).draw(canvas, this.offsetBackgroundTwoX);
        }
        Log.i("offsets", "One : " + offsetBackgroundOneX + " | Two : " + offsetBackgroundTwoX);
        // reset position of the first background
        // Don't know why a little piece of screen still bugged with all good values
        // so I add 10px to the new position of each background and this has solved the problem
        if (this.offsetBackgroundOneX >= BACKGROUND_WIDTH) {
            this.offsetBackgroundOneX = DEFAULT_OFFSET_BACKGROUND_TWO + 10;
        }
        if (this.offsetBackgroundTwoX >= BACKGROUND_WIDTH) {
            this.offsetBackgroundTwoX = DEFAULT_OFFSET_BACKGROUND_TWO + 10;
        }

        player.draw(canvas);
        coinScore.draw(canvas);
        if (paused) {
            canvas.drawText("PAUSED", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
        }
    }

}
