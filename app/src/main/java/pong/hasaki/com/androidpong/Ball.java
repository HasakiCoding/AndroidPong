package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    float centerX, centerY, radius;
    double speedX, speedY;
    Paint p = new Paint();

    Ball(int color, float centerX, float centerY, float radius) {
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
    }

    public void render(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, radius, p);
    }

    public void move(double moveX, double moveY) {
        centerX += moveX;
        centerY += moveY;
    }

    public void reset(int width, int height) {
        this.centerX = width / 2 - radius;
        this.centerY = height / 2 - radius;
        speedX = (Math.signum(Math.random() * 2 - 1) * Math.ceil(Math.random() * 2 + 9));
        speedY = (Math.signum(Math.random() * 2 - 1) * Math.ceil(Math.random() * 2 + 9));
    }
}