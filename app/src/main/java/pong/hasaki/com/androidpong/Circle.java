package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle {

    int color;
    float radius;
    Shape circle;

    Circle(int color, float centerX, float centerY, float radius){
        this.color = color;
        this.radius = radius;

        circle = new Shape(centerX, centerY);
    }

    public void render(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(circle.centerX, circle.centerY, this.radius, paint);
    }

    public void move(int moveX, int moveY){
        circle.move(moveX, moveY);
    }
}
