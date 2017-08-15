package pong.hasaki.com.androidpong;

import android.graphics.Canvas;

public class Ball {

    Circle ball;
    float centerX, centerY;

    Ball(int color, float centerX, float centerY, float radius){
        this.centerX = centerX;
        this.centerY = centerY;
        ball = new Circle(color, this.centerX, this.centerY, radius);
    }

    public void render(Canvas canvas) {
        ball.render(canvas);
    }

    public void move(int moveX, int moveY){
        ball.move(moveX, moveY);
    }
}
