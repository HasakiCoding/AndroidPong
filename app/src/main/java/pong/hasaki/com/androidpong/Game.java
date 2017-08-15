package pong.hasaki.com.androidpong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;
import android.view.View;

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