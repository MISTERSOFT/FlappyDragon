package me.sofianehamadi.flyingbird;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.sofianehamadi.flyingbird.views.GameView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
