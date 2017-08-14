package pong.hasaki.com.androidpong;

import android.graphics.Canvas;

public class Ball {

    Circle ball;

    Ball(int color, float centerX, float centerY, float radius){
        ball = new Circle(color, centerX, centerY, radius);
    }

    public void render(Canvas canvas) {
        ball.render(canvas);
    }

    public void move(int moveX, int moveY){
        ball.move(moveX, moveY);
    }
}
