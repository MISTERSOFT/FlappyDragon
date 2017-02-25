package me.sofianehamadi.flyingbird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private ImageButton playButton;
    private ImageButton shopButton;
    private ImageButton aboutButton;
    private ImageButton optionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playButton = (ImageButton) findViewById(R.id.play_button);
        shopButton = (ImageButton) findViewById(R.id.shop_button);
        aboutButton = (ImageButton) findViewById(R.id.about_button);
        optionsButton = (ImageButton) findViewById(R.id.options_button);

        playButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("Btn - Play", "onTouch: Action_down");
                        playButton.setBackgroundResource(R.drawable.play_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.i("Btn - Play", "onTouch: Action_up");
                        playButton.setBackgroundResource(R.drawable.play_unpressed);
                        startActivity(new Intent(MenuActivity.this, GameActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
        shopButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        shopButton.setBackgroundResource(R.drawable.shop_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        shopButton.setBackgroundResource(R.drawable.shop_unpressed);
                        // TODO
//                        startActivity(new Intent(MenuActivity.this, GameActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
        aboutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        aboutButton.setBackgroundResource(R.drawable.about_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        aboutButton.setBackgroundResource(R.drawable.about_unpressed);
                        // TODO
                        startActivity(new Intent(MenuActivity.this, AboutActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
        optionsButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        optionsButton.setBackgroundResource(R.drawable.options_pressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        optionsButton.setBackgroundResource(R.drawable.options_unpressed);
                        // TODO
                        startActivity(new Intent(MenuActivity.this, OptionsActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
    }
}
