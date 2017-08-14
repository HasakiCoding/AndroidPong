package pong.hasaki.com.androidpong;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity {

    GameView GV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GV = new GameView(this);
        setContentView(GV);
    }
}