package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    private int color;
    float centerX, centerY, radius;
    //float directionX = 10, speedX = 10;
    double speedX, speedY;
    //int bot;

    Ball(int color, float centerX, float centerY, float radius) {
        this.color = color;
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void render(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
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

    public boolean collide(float oCenterX, float oCenterY, float oWidth, float oHeight ) {
        if ((Math.abs(centerX - oCenterX) <= radius + (oWidth * 0.5) && Math.abs(centerY - oCenterY) <= radius + (oHeight * 0.5))){
            return true;
        } else {
            return false;
        }
    }
}