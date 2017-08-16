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

    int sc1, sc2;

    //int directionX = 10;
    //int speedX = 10;

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
                gameball.reset(canvas.getWidth(), canvas.getHeight());
                setup = true;
            }
            //c.drawBitmap(ball, x-(ball.getWidth()/2), y-(ball.getHeight()/2), blue);
            gameball.move(gameball.speedX, gameball.speedY);
            comp2();
            collide();
            gameRender(canvas);
            holder.unlockCanvasAndPost(canvas);
            /*try {
                Thread.sleep(5);
            } catch (InterruptedException e){
                e.printStackTrace();
            }*/
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

    public void reset(){
        gameball.reset(canvas.getWidth(), canvas.getHeight());
        paddle1.reset(10, canvas.getHeight() * 0.5);
        paddle1.reset(10, canvas.getHeight() * 0.5);
    }

    public void collide() {
        if(gameball.collide(top.centerX, top.centerY, top.width, top.height) || gameball.collide(bot.centerX, bot.centerY, bot.width, bot.height)){
            gameball.speedY = -gameball.speedY;
            speedUp();
        }
        if(gameball.collide(paddle1.centerX, paddle1.centerY, paddle1.width, paddle1.height) || gameball.collide(paddle2.centerX, paddle2.centerY, paddle2.width, paddle2.height)){
            gameball.speedX = -gameball.speedX;
            speedUp();
        }
        if(gameball.collide(right.centerX, right.centerY, right.width, right.height)){
            sc1 ++;
            reset();
        } else if(gameball.collide(left.centerX, left.centerY, left.width, left.height)){
            sc2 ++;
            reset();
        }
        /*if (gameball.centerY - gameball.radius <= 0) {
            gameball.speedY = -gameball.speedY;
        } else if (gameball.centerY + gameball.radius >= canvas.getHeight()) {
            gameball.speedY = -gameball.speedY;
        } else if (Math.abs(gameball.centerX - paddle1.centerX) <= gameball.radius + (paddle1.width * 0.5) && Math.abs(gameball.centerY - paddle1.centerY) <= gameball.radius + (paddle1.height * 0.5)) {
            gameball.speedX = -gameball.speedX;
        } else if (Math.abs(gameball.centerX - paddle2.centerX) <= gameball.radius + (paddle2.width * 0.5) && Math.abs(gameball.centerY - paddle2.centerY) <= gameball.radius + (paddle2.height * 0.5)) {
            gameball.speedX = -gameball.speedX;
        } else if (gameball.centerX - gameball.radius <= 0) {
            gameball.speedX = -gameball.speedX;
            reset();
        } else if (gameball.centerX + gameball.radius >= canvas.getWidth()) {
            gameball.speedX = -gameball.speedX;
            reset();
        }*/
    }

    public void comp2() {
        if(paddle2.centerY < gameball.centerY - 35){
            paddle2.centerY += 15;
        } else if(paddle2.centerY > gameball.centerY + 35){
            paddle2.centerY -= 15;
        }
    }

    public void speedUp(){
        gameball.speedX += 1;
        gameball.speedY += 1;
    }
}