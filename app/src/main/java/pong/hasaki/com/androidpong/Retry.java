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
        Paddle paddle1;

        public OurView(Context context) {
            super(context);
            holder = getHolder();
            //canvas = holder.lockCanvas();
            //init();
        }

        public void run() {
            while(started){
                if(!holder.getSurface().isValid()){
                    continue;
                }
                canvas = holder.lockCanvas();
                if(!setup) {
                    paddle1 = new Paddle(Color.WHITE, 10, Math.round(y), 20, 300);
                }
                //canvas = holder.lockCanvas();
                //canvas.drawRect(0, y - 150, 20, y + 150, white);
                //canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), black);
                //canvas.drawRect(canvas.getWidth() - 20, canvas.getHeight() / 2 - 150, canvas.getWidth(), canvas.getHeight() / 2+ 150, white);
                //canvas.drawCircle(canvas.getWidth() / 2 - 20, canvas.getHeight() / 2 - 20, 20, white);
                //c.drawBitmap(ball, x-(ball.getWidth()/2), y-(ball.getHeight()/2), blue);

                paddle1.render(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        //public void init() {
            //Paddle paddle1 = new Paddle(Color.WHITE, 10, canvas.getHeight() - 150 , 30, canvas.getHeight() + 150);

        //}

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