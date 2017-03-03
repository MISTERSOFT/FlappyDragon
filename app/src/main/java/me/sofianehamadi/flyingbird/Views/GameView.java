package me.sofianehamadi.flyingbird.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.sofianehamadi.flyingbird.database.Database;
import me.sofianehamadi.flyingbird.gameobject.BirdFactory;
import me.sofianehamadi.flyingbird.models.BirdTypeEnum;
import me.sofianehamadi.flyingbird.models.User;
import me.sofianehamadi.flyingbird.ui.Background;
import me.sofianehamadi.flyingbird.gui.CoinScore;
import me.sofianehamadi.flyingbird.gameobject.Coin;
import me.sofianehamadi.flyingbird.gameobject.Player;
import me.sofianehamadi.flyingbird.R;
import me.sofianehamadi.flyingbird.common.Util;

public class GameView extends AppView {

    public static final long UPDATE_INTERVAL = 50; // = 20 FPS
    private static final int BACKGROUND_PROGRESS_PER_TICK = 10;
    private static final int GENERATE_COINS_NUMBER = 1;
    private final int DEFAULT_OFFSET_BACKGROUND_ONE;

    private final int DEFAULT_OFFSET_BACKGROUND_TWO;

    private final int BACKGROUND_WIDTH;
    private int offsetBackgroundOneX;
    private int offsetBackgroundTwoX;

    private boolean paused = true;
    private TimerTask timerTask;
    private Timer timer = new Timer();
    private ArrayList<Background> backgrounds;

    private Player player;
    private CoinScore coinScore;
    private ArrayList<Coin> coins;

    private static User userInfo;

    public GameView(Context context) {
        super(context);

        /**
         * Start game when the SurfaceView is ready
         */
        getHolder().addCallback(this);

        /**
         * Init GameObjects and GUI elements
         */
        userInfo = Database.getInstance(context).getUser();

        /**
         * Init GameView backgrounds
         */
        this.backgrounds = new ArrayList<>();
        this.backgrounds.add(new Background(context, this, R.drawable.game_background));
        this.backgrounds.add(new Background(context, this, R.drawable.game_background_revert));

        this.BACKGROUND_WIDTH = this.backgrounds.get(0).getBackground().getWidth();
        this.DEFAULT_OFFSET_BACKGROUND_ONE  = 0;
        this.DEFAULT_OFFSET_BACKGROUND_TWO = -BACKGROUND_WIDTH;
        this.offsetBackgroundOneX = DEFAULT_OFFSET_BACKGROUND_ONE;
        this.offsetBackgroundTwoX = DEFAULT_OFFSET_BACKGROUND_TWO;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(this.paused && !this.player.isDead()) {
                resume();
            } else {
//                Log.i("PLAYER", "PLAYER TAPPED");
                this.player.onTap();
            }
        }
        return true;
    }

    private void resume() {
        this.paused = false;
        startTimer();
    }

    private void startTimer() {
//        Log.i("TIMER", "START TIMER");
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

    private void pause() {
        this.paused = true;
        this.stopTimer();
    }

    private void gameOver() {
        userInfo.sum(this.coinScore.getTotalCoins());
        Database.getInstance(this.context).updateUser(userInfo);
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
        // Continu to draw if the player is not dead
        if (!this.player.isDead()) {
            if (this.offsetBackgroundOneX < BACKGROUND_WIDTH) {
                this.backgrounds.get(0).draw(canvas, this.offsetBackgroundOneX);
            }
            // the player will begin to see a black background, so we draw the next background
            // that is fixed on the right side of the previous background
            if (this.offsetBackgroundTwoX < BACKGROUND_WIDTH) {
                this.backgrounds.get(1).draw(canvas, this.offsetBackgroundTwoX);
            }
//        Log.i("offsets", "One : " + offsetBackgroundOneX + " | Two : " + offsetBackgroundTwoX);
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
            for (Coin c : this.coins) {
                if (c.getX() < 0) {
                    c.generateRandomPosition(canvas);
                }
                if (player.getHitbox().intersect(c.getHitbox())) {
                    this.player.pickUpCoin(c, canvas);
                    this.coinScore.add(1);
                }
                c.move();
                c.draw(canvas);
            }
            coinScore.draw(canvas);
        }
        else {
            pause();
        }

        if (this.paused) {
            // Game Over
            if (this.player.isDead()) {
                canvas.drawText("Game Over", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
                gameOver();
            }
            else {
                // Just pause the game
                canvas.drawText("Pause", canvas.getWidth() / 2, canvas.getHeight() / 2, new Paint());
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.surfaceViewHeight = getHeight();
        this.surfaceViewWidth = getWidth();
        init();

        /**
         * Start the game in a new thread
         */
        this.viewThread = new Thread(new Runnable() {
            @Override
            public void run() {
                GameView.this.run();
            }
        });
        this.viewThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                this.viewThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        /**
         * Init player
         */
        List<Integer> sprites = (List<Integer>) BirdFactory.getInstance(context).getBirdSprites(BirdTypeEnum.ANGRY);
        ArrayList<Bitmap> playerSprites = new ArrayList<>();
        // Get all resources with sprites ID
        for (Integer sprite : sprites) {
            playerSprites.add(Util.getAutoScaledBitmapAlpha8(context, sprite, this.surfaceViewHeight, this.surfaceViewWidth, 8));
        }
        this.player = new Player(context, this, playerSprites);

        /**
         * Init coin
         */
        // Init coinscore
        this.coinScore = new CoinScore(this.context, this, R.drawable.coin7, 20, 20);
        // Init coins
        this.coins = new ArrayList<>();
        ArrayList<Bitmap> coinSprites = new ArrayList<>();
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin1, Coin.COIN_SIZE, Coin.COIN_SIZE));
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin2, Coin.COIN_SIZE, Coin.COIN_SIZE));
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin3, Coin.COIN_SIZE, Coin.COIN_SIZE));
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin4, Coin.COIN_SIZE, Coin.COIN_SIZE));
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin5, Coin.COIN_SIZE, Coin.COIN_SIZE));
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin6, Coin.COIN_SIZE, Coin.COIN_SIZE));
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin7, Coin.COIN_SIZE, Coin.COIN_SIZE));
        coinSprites.add(Util.getScaledBitmapAlpha8(this.context, R.drawable.coin8, Coin.COIN_SIZE, Coin.COIN_SIZE));
        for (int i = 0; i < GENERATE_COINS_NUMBER; i++) {
            Coin c = new Coin(this.context, this, coinSprites);
            this.coins.add(c);
        }
    }
}
