package pong.hasaki.com.androidpong.;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View.OnTouchListener;
import android.view.View;

import pong.hasaki.com.androidpong.R;


public class Retry extends Activity implements OnTouchListener{

    OurView v;
    Bitmap ball;
    float x, y;
    Paint blue = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new OurView(this);
        v.setOnTouchListener(this);
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.background_1);
        x = y = 0;
        setContentView(v);
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);
    }

    protected void onPause(){
        super.onPause();
        v.pause();
    }

    protected void onResume(){
        super.onResume();
        v.resume();
    }

    public class OurView  extends SurfaceView implements Runnable {

        Thread t = null;
        SurfaceHolder holder;
        boolean isItOK = false;

        public OurView(Context context) {
            super(context);
            holder = getHolder();
        }

        public void run() {
            while(isItOK){
                if(!holder.getSurface().isValid()){
                    continue;
                }
                Canvas c = holder.lockCanvas();
                c.drawARGB(255, 150, 150, 10);
                c.drawBitmap(ball, x-(ball.getWidth()/2), y-(ball.getHeight()/2), blue);
                holder.unlockCanvasAndPost(c);
            }
        }

        public void pause(){
            isItOK = false;
            while(true){
                try {
                    t.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume(){
            isItOK = true;
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent me) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        switch(me.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = me.getX();
                y = me.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = me.getX();
                y = me.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x = me.getX();
                y = me.getY();
                break;
        }
        return true;
    }
}