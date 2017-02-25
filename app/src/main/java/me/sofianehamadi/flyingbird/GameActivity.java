package me.sofianehamadi.flyingbird;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.sofianehamadi.flyingbird.views.GameView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
