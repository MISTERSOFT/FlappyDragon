package me.sofianehamadi.flyingbird.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.sofianehamadi.flyingbird.GameActivity;
import me.sofianehamadi.flyingbird.MenuActivity;
import me.sofianehamadi.flyingbird.common.AudioGame;
import me.sofianehamadi.flyingbird.common.FontCache;
import me.sofianehamadi.flyingbird.database.Database;
import me.sofianehamadi.flyingbird.gameobject.Enemy;
import me.sofianehamadi.flyingbird.gameobject.SpriteFactory;
import me.sofianehamadi.flyingbird.models.Bird;
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
    private static final int GENERATE_ENEMIES_NUMBER = 2;

    private static List<Bitmap> playerSprites; // player sprites
    private static List<Bitmap> coinSprites; // coin sprites
    private static List<Bitmap> enemySprites; // enemy sprites
    private static Bitmap touchToPlay;

    private int DEFAULT_OFFSET_BACKGROUND_ONE;
    private int DEFAULT_OFFSET_BACKGROUND_TWO;
    private int BACKGROUND_WIDTH;
    private int offsetBackgroundOneX;
    private int offsetBackgroundTwoX;

    private boolean paused;
    private TimerTask timerTask;
    private Timer timer = new Timer();
    private static ArrayList<Background> backgrounds;

    private Player player;
    private CoinScore coinScore;
    private static List<Enemy> enemies;
    private static List<Coin> coins;

    private AudioGame audioGame;

    private static User userInfo;
    private static Bird equipedBird;

    public GameView(Context context, AudioGame audioGame) {
        super(context);
        this.audioGame = audioGame;
        /**
         * Start game when the SurfaceView is ready
         */
        getHolder().addCallback(this);

        /**
         * Get User
         */
        userInfo = Database.getInstance(context).getUser();
        /**
         * Get equiped bird
         */
        equipedBird = Database.getInstance(context).getEquipedBird();
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
        this.audioGame.stopAmbiantFX();
        this.audioGame.playFX(AudioGame.LOSE);
        userInfo.sum(this.coinScore.getTotalCoins());
        Database.getInstance(this.context).updateUser(userInfo);
        // Show dialog
        ((GameActivity)this.context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View restartDialogView = LayoutInflater.from(context).inflate(R.layout.restart_game_dialog, null);
                final AlertDialog restartDialog = new AlertDialog.Builder(context).create();
                restartDialog.setCancelable(false);

                TextView restartText = (TextView) restartDialogView.findViewById(R.id.restart_text);
                restartText.setTypeface(FontCache.getTypeface(context, FontCache.PixelOperatorMono8));

                Button yesRestartBtn = (Button) restartDialogView.findViewById(R.id.yes_restart);
                yesRestartBtn.setTypeface(FontCache.getTypeface(context, FontCache.PixelOperatorMono8));
                yesRestartBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pause();
//                        stopGame();
                        startGame();
                        resume();
                        restartDialog.dismiss();
                    }
                });

                Button noRestartBtn = (Button) restartDialogView.findViewById(R.id.no_restart);
                noRestartBtn.setTypeface(FontCache.getTypeface(context, FontCache.PixelOperatorMono8));
                noRestartBtn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopGame();
                        restartDialog.dismiss();
                        context.startActivity(new Intent(context, MenuActivity.class));
                    }
                });

                restartDialog.setView(restartDialogView);
                restartDialog.show();
            }
        });
    }

    private void stopGame() {
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

    /**
     * Init game components
     */
    private void startGame() {
        this.paused = true;
        this.audioGame.playLoopAmbiantFX();
        /**
         * First image on start
         */
        touchToPlay = Util.getAutoScaledBitmapAlpha8(context, R.drawable.touch_to_play, this.surfaceViewHeight, this.surfaceViewWidth, 4);
        /**
         * Init player
         */
        playerSprites = new ArrayList<>();
        // Get all resources with sprites ID
        for (Integer sprite : SpriteFactory.getInstance(context).getBirdSprites(equipedBird.getBirdType())) {
            playerSprites.add(Util.getAutoScaledBitmapAlpha8(context, sprite, this.surfaceViewHeight, this.surfaceViewWidth, 8));
        }
        this.player = new Player(context, this, playerSprites, audioGame);

        /**
         * Init enemies
         */
        if (enemySprites == null) {
            enemySprites = new ArrayList<>();
            for (Integer sprite : SpriteFactory.getInstance(context).getEnemySprites()) {
                enemySprites.add(Util.getAutoScaledBitmapAlpha8(context, sprite, this.surfaceViewHeight, this.surfaceViewWidth, 8));
            }
        }
        enemies = new ArrayList<>();
        for (int i = 0; i < GENERATE_ENEMIES_NUMBER; i++) {
            Enemy e = new Enemy(context, this, enemySprites);
            enemies.add(e);
        }

        /**
         * Init coin
         */
        // Init coinscore
        this.coinScore = new CoinScore(this.context, this, R.drawable.coin7, 20, 20);
        // Init coins
        if (coins == null) {
            coins = new ArrayList<>();
            coinSprites = new ArrayList<>();
            for (Integer sprite : SpriteFactory.getInstance(context).getCoinsSprites()) {
                coinSprites.add(Util.getScaledBitmapAlpha8(context, sprite, Coin.COIN_SIZE, Coin.COIN_SIZE));
            }
        }
        coins.clear();
        for (int i = 0; i < GENERATE_COINS_NUMBER; i++) {
            Coin c = new Coin(this.context, this, coinSprites, audioGame);
            coins.add(c);
        }
        /**
         * Init GameView backgrounds
         */
        if (backgrounds == null) {
            backgrounds = new ArrayList<>();
            backgrounds.add(new Background(context, this, R.drawable.game_background));
            backgrounds.add(new Background(context, this, R.drawable.game_background_revert));
        }
        BACKGROUND_WIDTH = backgrounds.get(0).getBackground().getWidth();
        DEFAULT_OFFSET_BACKGROUND_ONE  = 0;
        DEFAULT_OFFSET_BACKGROUND_TWO = -BACKGROUND_WIDTH;
        this.offsetBackgroundOneX = DEFAULT_OFFSET_BACKGROUND_ONE;
        this.offsetBackgroundTwoX = DEFAULT_OFFSET_BACKGROUND_TWO;

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
    public void run() {
//        if (!soundIsPlaying) {
//            this.soundIsPlaying = true;
//            this.audioGame.playLoopAmbiantFX(AudioGame.AMBIANCE);
//        }
        player.move();
        for (Enemy e : enemies) {
            e.move();
        }
        this.draw();
    }

    @Override
    protected void draw() {
        super.draw();
        this.offsetBackgroundOneX += BACKGROUND_PROGRESS_PER_TICK;
        this.offsetBackgroundTwoX += BACKGROUND_PROGRESS_PER_TICK;
        // increase enemies speed
        for (Enemy e : enemies) {
            e.increaseSpeed();
        }
    }

    @Override
    protected void drawCanvas(Canvas canvas) {
        // Continu to draw if the player is not dead
        if (!this.player.isDead()) {
            if (this.offsetBackgroundOneX < BACKGROUND_WIDTH) {
                backgrounds.get(0).draw(canvas, this.offsetBackgroundOneX);
            }
            // the player will begin to see a black background, so we draw the next background
            // that is fixed on the right side of the previous background
            if (this.offsetBackgroundTwoX < BACKGROUND_WIDTH) {
                backgrounds.get(1).draw(canvas, this.offsetBackgroundTwoX);
            }
            // Log.i("offsets", "One : " + offsetBackgroundOneX + " | Two : " + offsetBackgroundTwoX);
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
            for (Coin c : coins) {
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

            for (Enemy e : enemies) {
                if (e.getX() < 0) {
                    e.resetPosition();
                }
                if (this.player.getHitbox().intersect(e.getHitbox())) {
                    this.player.setDead(true);
                    pause();
                }
                e.draw(canvas);
            }
        }
        else {
            pause();
        }

        if (this.paused) {
            // Game Over
            if (this.player.isDead()) {
                gameOver();
            }
            else {
                // Just pause the game
                canvas.drawBitmap(
                        touchToPlay,
                        (canvas.getWidth() / 2) - (touchToPlay.getWidth() / 2),
                        (canvas.getHeight() / 2) - (touchToPlay.getHeight() / 2),
                        null);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.surfaceViewHeight = getHeight();
        this.surfaceViewWidth = getWidth();
        startGame();

        /**
         * Start the game in a new thread
         */
//        this.viewThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                GameView.this.run();
//            }
//        });
//        this.viewThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        this.audioGame.release();
        this.stopGame();
    }
}
