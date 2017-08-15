package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    private int color;
    float centerX, centerY, radius;
    float directionX = 10, directionY = 10;
    //int bot;

    Ball(int color, float centerX, float centerY, float radius){
        this.color = color;
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void render(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius, p);
    }

    public void move(double moveX, double moveY, int bot, int right){
        centerX += moveX;
        centerY += moveY;
        if(this.centerY - this.radius <= 0){
            directionY = -directionY;
        } else if(this.centerY + this.radius >= bot){
            directionY = -directionY;
        } else if(this.centerX - this.radius <= 0){
            directionX = -directionX;
        } else if(this.centerX + this.radius >= right){
            directionX = -directionX;
        }
    }

    public void reset(int width, int height){
        this.centerX = width / 2 - radius;
        this.centerY = height / 2 - radius;
        //speedX = Math.signum(Math.random() * 2 - 1) * Math.ceil(Math.random() * )
    }
}