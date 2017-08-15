package pong.hasaki.com.androidpong;

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

public class Retry extends Activity implements OnTouchListener{

    OurView v;
    Bitmap ball;
    float x, y;
    Paint white = new Paint();
    Paint black = new Paint();
    static int width = 20, height = 300;
    static float radius = 30;
    Rectangle field;
    Paddle paddle1;
    Paddle paddle2;
    Ball gameball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new OurView(this);
        v.setOnTouchListener(this);
        //ball = BitmapFactory.decodeResource(getResources(), R.drawable.background_1);
        x = y = 0;
        setContentView(v);
        white.setColor(Color.WHITE);
        white.setStyle(Paint.Style.FILL);
        black.setColor(Color.BLACK);
        black.setStyle(Paint.Style.FILL);
    }

    protected void onPause(){
        super.onPause();
        v.pause();
    }

    protected void onResume(){
        super.onResume();
        v.resume();
    }

    public class OurView extends SurfaceView implements Runnable {

        Thread t = null;
        SurfaceHolder holder;
        boolean started = false, setup = false;
        Canvas canvas;
        float ballX, ballY;

        public OurView(Context context) {
            super(context);
            holder = getHolder();
        }

        public void run() {
            while(started){
                if(!holder.getSurface().isValid()){
                    continue;
                }
                canvas = holder.lockCanvas();
                if(!setup) {
                    ballX = canvas.getWidth() / 2 - radius;
                    ballY = canvas.getHeight() / 2 - radius;

                    field = new Rectangle(Color.BLACK, 0, 0, canvas.getWidth(), canvas.getHeight(), 1);
                    paddle1 = new Paddle(Color.WHITE, 10, Math.round(y), width, height);
                    paddle2 = new Paddle(Color.WHITE, canvas.getWidth() - 10, canvas.getHeight() / 2, width, height);
                    gameball = new Ball(Color.WHITE, ballX, ballY, radius);
                    setup = true;
                }
                //c.drawBitmap(ball, x-(ball.getWidth()/2), y-(ball.getHeight()/2), blue);

                gameball.centerY += 10;
                gameball.centerX += 10;

                field.render(canvas);
                paddle1.render(canvas);
                paddle2.render(canvas);
                gameball.render(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause(){
            started = false;
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
            started = true;
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent me) {

        switch(me.getAction()){
            case MotionEvent.ACTION_DOWN:
                paddle1.centerY = me.getY();
                break;
            case MotionEvent.ACTION_UP:
                paddle1.centerY = me.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                paddle1.centerY = me.getY();
                break;
        }
        return true;
    }
}