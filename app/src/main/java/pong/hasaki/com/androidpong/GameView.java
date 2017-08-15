package pong.hasaki.com.androidpong;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class GameView extends SurfaceView implements Runnable {

    Thread t = null;
    SurfaceHolder holder;
    boolean started = false, setup = false;
    Canvas canvas;

    Rectangle field, top, left, bot, right;
    Paddle paddle1;
    Paddle paddle2;
    Ball gameball;

    static int width = 20, height = 300;
    static float radius = 30;

    int directionX = 10;
    int directionY = 10;

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

    public void run(){
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
            gameball.move(directionX, directionY);
            collide();
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
        field = new Rectangle(BLACK, 0, 0, canvas.getWidth(), canvas.getHeight());
        top = new Rectangle(WHITE, 0, 0, canvas.getWidth(), 0);
        left = new Rectangle(WHITE, 0, 0, 0, canvas.getHeight());
        bot = new Rectangle(WHITE, 0, canvas.getHeight(), canvas.getWidth(), 0);
        right = new Rectangle(WHITE, canvas.getWidth(), 0, 0, canvas.getHeight());
        paddle1 = new Paddle(WHITE, 10, canvas.getHeight() / 2 , width, height);
        paddle2 = new Paddle(WHITE, canvas.getWidth() - 10, canvas.getHeight() / 2, width, height);
        gameball = new Ball(WHITE, canvas.getWidth() / 2 - radius, canvas.getHeight() / 2 - radius, radius);
    }

    public void gameRender(Canvas canvas){
        field.render(canvas);
        paddle1.render(canvas);
        paddle2.render(canvas);
        gameball.render(canvas);
    }

    public void collide(){
        if(gameball.centerY + gameball.radius <= top.centerY){
            directionX = -directionX;
        } else if(gameball.centerY - gameball.radius >= bot.centerY){
            directionX = -directionX;
        }
    }

    public void reset(){
        gameball.reset(canvas.getWidth(), canvas.getHeight());
    }
}