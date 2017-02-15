package network.iut.org.flappydragon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
