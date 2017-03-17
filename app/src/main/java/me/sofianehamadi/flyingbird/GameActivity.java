package me.sofianehamadi.flyingbird;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.sofianehamadi.flyingbird.common.AudioGame;
import me.sofianehamadi.flyingbird.views.GameView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AudioGame audioGame = new AudioGame(this);
        setContentView(new GameView(this, audioGame));
    }
}
