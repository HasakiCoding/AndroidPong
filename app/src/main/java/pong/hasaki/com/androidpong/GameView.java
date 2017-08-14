package pong.hasaki.com.androidpong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    int paddle1Y = 0;
    static int width = 20, height = 300;
    static float radius = 30;
    float ballX, ballY, eventY = 0;
    Rectangle field;
    Paddle paddle1;
    Paddle paddle2;
    Ball gameball;


    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ballX = canvas.getWidth() / 2 - radius;
        ballY = canvas.getHeight() / 2 - radius;

        field = new Rectangle(Color.BLACK, 0, 0, canvas.getWidth(), canvas.getHeight(), 1);
        paddle1 = new Paddle(Color.WHITE, 10, paddle1Y, width, height);
        paddle2 = new Paddle(Color.WHITE, canvas.getWidth() - 10, canvas.getHeight() / 2, width, height);
        gameball = new Ball(Color.WHITE, ballX, ballY, radius);

        paddle1Y = Math.round(eventY);

        field.render(canvas);
        paddle1.render(canvas);
        paddle2.render(canvas);
        gameball.render(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                paddle1Y = Math.round(event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                paddle1Y = Math.round(event.getY());
                break;
            default:
                return false;
        }
        invalidate();
        return false;
    }
}