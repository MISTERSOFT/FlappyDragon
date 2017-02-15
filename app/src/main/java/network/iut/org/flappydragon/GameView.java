package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends AppView {
    public static final long UPDATE_INTERVAL = 50; // = 20 FPS
//    private SurfaceHolder holder;
    private boolean paused = true;
    private Timer timer = new Timer();
    private TimerTask timerTask;
    private Player player;
    private ArrayList<Background> backgrounds;
    private static final int BACKGROUND_WIDTH = 1920;
    private int offsetBackgroundOneX;
    private int offsetBackgroundTwoX;
    private static final int DEFAULT_OFFSET_BACKGROUND_ONE = 0;
    private static final int DEFAULT_OFFSET_BACKGROUND_TWO = -1920;
    private static final int BACKGROUND_PROGRESS_PER_TICK = 10;

    public GameView(Context context) {
        super(context);
        this.player = new Player(context, this);
        this.backgrounds = new ArrayList<>();
        this.backgrounds.add(new Background(context, this, R.drawable.game_background));
        this.backgrounds.add(new Background(context, this, R.drawable.game_background_revert));
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
        Background bg1 = this.backgrounds.get(0);
        if (this.offsetBackgroundOneX < BACKGROUND_WIDTH) {
            bg1.draw(canvas, this.offsetBackgroundOneX);
        }
        // the player will begin to see a black background, so we draw the next background
        // that is fixed on the right side of the previous background
        Background bg2 = this.backgrounds.get(1);
        if (this.offsetBackgroundTwoX < BACKGROUND_WIDTH) {
            bg2.draw(canvas, this.offsetBackgroundTwoX);
        }
        Log.i("offsets", "One : " + offsetBackgroundOneX + " | Two : " + offsetBackgroundTwoX);
        // reset position of the first background
        if (this.offsetBackgroundOneX > BACKGROUND_WIDTH) {
            Log.i("reset bg 1", "");
            this.offsetBackgroundOneX = DEFAULT_OFFSET_BACKGROUND_ONE;
//            this.backgrounds.set(0, bg2);
//            this.backgrounds.set(1, bg1);
        }
        if (this.offsetBackgroundOneX > BACKGROUND_WIDTH) {
            this.backgrounds.remove(0);
            this.backgrounds.add(bg1);
        }
        if (this.offsetBackgroundTwoX > BACKGROUND_WIDTH) {
            Log.i("reset bg 2", "");
            this.offsetBackgroundTwoX = DEFAULT_OFFSET_BACKGROUND_TWO;
//            this.backgrounds.set(0, bg1);
//            this.backgrounds.set(1, bg2);
        }
        if (this.offsetBackgroundTwoX > BACKGROUND_WIDTH) {
            this.backgrounds.remove(0);
            this.backgrounds.add(bg2);
        }

        player.draw(canvas);
        if (paused) {
            canvas.drawText("PAUSED", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
        }
    }

}
