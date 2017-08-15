package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    //Circle ball;
    int color;
    float centerX, centerY, radius;

    Ball(int color, float centerX, float centerY, float radius){
        this.color = color;
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        //ball = new Circle(color, this.centerX, this.centerY, radius);
    }

    public void render(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    public void move(int moveX, int moveY){
        centerX += moveX;
        centerY += moveY;
    }
}
