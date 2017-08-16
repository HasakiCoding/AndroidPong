package pong.hasaki.com.androidpong;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity {

    GameView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new GameView(this);
        setContentView(v);
    }

    protected void onPause() {
        super.onPause();
        v.pause();
    }

    protected void onResume() {
        super.onResume();
        v.resume();
    }
}