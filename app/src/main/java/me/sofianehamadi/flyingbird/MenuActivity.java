package me.sofianehamadi.flyingbird;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MenuActivity extends AppCompatActivity implements View.OnTouchListener {

    private static ImageButton playButton;
    private static ImageButton scoreboardButton;
    private static ImageButton aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playButton = (ImageButton) findViewById(R.id.play_button);
        scoreboardButton = (ImageButton) findViewById(R.id.scoreboard_button);
        aboutButton = (ImageButton) findViewById(R.id.about_button);

        playButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("Btn - Play", "onTouch: Action_down");
                        playButton.setBackgroundResource(R.drawable.play_pressed);
//                        startActivity(new Intent(MenuActivity.this, GameActivity.class));
                        break;

                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.i("Btn - Play", "onTouch: Action_btn_press");
                        playButton.setBackgroundResource(R.drawable.play_pressed);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        Log.i("Btn - Play", "onTouch: Action_cancel");
                        playButton.setBackgroundResource(R.drawable.play_unpressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        Log.i("Btn - Play", "onTouch: Action_up");
                        playButton.setBackgroundResource(R.drawable.play_unpressed);
                        break;

                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.i("Btn - Play", "onTouch: action_btn_releaese");
                        startActivity(new Intent(MenuActivity.this, GameActivity.class));
                        break;

                    default: break;
                }

                return false;
            }
        });
        scoreboardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        scoreboardButton.setBackgroundResource(R.drawable.scoreboard_pressed);
                        break;

                    case MotionEvent.ACTION_BUTTON_PRESS:
                        scoreboardButton.setBackgroundResource(R.drawable.scoreboard_pressed);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        scoreboardButton.setBackgroundResource(R.drawable.scoreboard_unpressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        scoreboardButton.setBackgroundResource(R.drawable.scoreboard_unpressed);
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

                    case MotionEvent.ACTION_BUTTON_PRESS:
                        aboutButton.setBackgroundResource(R.drawable.about_pressed);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        aboutButton.setBackgroundResource(R.drawable.about_unpressed);
                        break;

                    case MotionEvent.ACTION_UP:
                        aboutButton.setBackgroundResource(R.drawable.about_unpressed);
                        break;

                    default: break;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        int i = event.get;
        int id = playButton.getId();

        return false;
    }
}
