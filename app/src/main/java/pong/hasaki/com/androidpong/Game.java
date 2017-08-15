package pong.hasaki.com.androidpong;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity {

    GameView v;
    //Bitmap ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new GameView(this);
        //ball = BitmapFactory.decodeResource(getResources(), R.drawable.background_1);
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