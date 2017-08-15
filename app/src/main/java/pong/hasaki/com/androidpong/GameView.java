package pong.hasaki.com.androidpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView implements Runnable {

    Thread t = null;
    SurfaceHolder holder;
    boolean started = false, setup = false;
    Canvas canvas;

    Rectangle field;
    Paddle paddle1;
    Paddle paddle2;
    Ball gameball;

    static int width = 20, height = 300;
    static float radius = 30;

    public GameView(Context context) {
        super(context);
        holder = getHolder();
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {
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
        });
    }

    public void run() {
        while(started){
            if(!holder.getSurface().isValid()){
                continue;
            }
            canvas = holder.lockCanvas();
            if(!setup) {
                init(canvas);
                setup = true;
            }
            //c.drawBitmap(ball, x-(ball.getWidth()/2), y-(ball.getHeight()/2), blue);
            gameRender(canvas);
            gameball.move(10, 10);

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

    public void init(Canvas canvas){
        field = new Rectangle(Color.BLACK, 0, 0, canvas.getWidth(), canvas.getHeight(), 1);
        paddle1 = new Paddle(Color.WHITE, 10, canvas.getHeight() / 2, width, height);
        paddle2 = new Paddle(Color.WHITE, canvas.getWidth() - 10, canvas.getHeight() / 2, width, height);
        gameball = new Ball(Color.WHITE, canvas.getWidth() / 2 - radius, canvas.getHeight() / 2 - radius, radius);
    }

    public void gameRender(Canvas canvas){
        field.render(canvas);
        paddle1.render(canvas);
        paddle2.render(canvas);
        gameball.render(canvas);
    }
}