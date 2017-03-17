package me.sofianehamadi.flyingbird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import me.sofianehamadi.flyingbird.common.FontCache;
import me.sofianehamadi.flyingbird.database.Database;
import me.sofianehamadi.flyingbird.models.User;

public class MenuActivity extends AppCompatActivity {
    /**
     * Total player coins
     */
    private TextView coins_earn;
    /**
     * User informations
     */
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /**
         * Load user information
         */
        this.loadUserInfo();


        /**
         * Change font of the total coin earned
         */
        this.coins_earn = (TextView) findViewById(R.id.coins_earn);
        this.coins_earn.setTypeface(FontCache.getTypeface(this, FontCache.PixelOperatorMono8));
        this.updateCoinEarned();

        /**
         * Add listeners on buttons
         */
        final ImageButton playButton = (ImageButton) findViewById(R.id.play_button);
        final ImageButton shopButton = (ImageButton) findViewById(R.id.shop_button);
        final ImageButton aboutButton = (ImageButton) findViewById(R.id.about_button);
        final ImageButton optionsButton = (ImageButton) findViewById(R.id.options_button);

        /**
         * Button to go on Game activity
         */
        playButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        playButton.setImageResource(R.drawable.play_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        playButton.setImageResource(R.drawable.play_unpressed);
                        startActivity(new Intent(MenuActivity.this, GameActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
        /**
         * Button to go on Shop activity
         */
        shopButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        shopButton.setImageResource(R.drawable.shop_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        shopButton.setImageResource(R.drawable.shop_unpressed);
                        startActivity(new Intent(MenuActivity.this, ShopActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
        /**
         * Button to go on About activity
         */
        aboutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        aboutButton.setImageResource(R.drawable.about_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        aboutButton.setImageResource(R.drawable.about_unpressed);
                        startActivity(new Intent(MenuActivity.this, AboutActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
        /**
         * Button to go on Options activity
         */
        optionsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        optionsButton.setImageResource(R.drawable.options_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        optionsButton.setImageResource(R.drawable.options_unpressed);
                        startActivity(new Intent(MenuActivity.this, OptionsActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
    }

    /**
     * On activity restart, reload information displayed
     */
    @Override
    protected void onRestart() {
        this.loadUserInfo();
        this.updateCoinEarned();
        super.onRestart();
    }

    /**
     * Load user information
     */
    private void loadUserInfo() {
        this.user = Database.getInstance(this).getUser();
    }

    /**
     * Refresh player total coin
     */
    private void updateCoinEarned() {
        this.coins_earn.setText(user.getMoney().toString());
    }
}
