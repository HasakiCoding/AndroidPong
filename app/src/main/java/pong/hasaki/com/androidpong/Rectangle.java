package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle {

    int centerX, centerY, color, num;
    float left, top, right, bottom;
    Shape rect;

    Rectangle(int color, int left,  int top, int width, int height, int num) {
        this.color = color;
        this.centerX = left + width/2;
        this.centerY = top + height/2;
        this.left = left;
        this.top = top;
        this.right = left + width;
        this.bottom = top + height;

        rect = new Shape(centerX, centerY);
    }

    Rectangle(int color, int centerX, int centerY, int width, int height) {
        rect = new Shape(centerX, centerY);
        this.color = color;
        this.left = rect.centerX - width/2;
        this.top = rect.centerY - height/2;
        this.right = rect.centerX + width/2;
        this.bottom = rect.centerY + height/2;
    }

    public void render(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, paint);

    }

    public void move(int moveX, int moveY){
        rect.move(moveX, moveY);
    }
}