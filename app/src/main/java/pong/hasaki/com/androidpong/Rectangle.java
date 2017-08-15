package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle {

    int color;
    float left, top, right, bottom;
    double centerX, centerY;

    Rectangle(int color, int left,  int top, int width, int height) {
        this.color = color;
        this.left = left;
        this.top = top;
        this.right = left + width;
        this.bottom = top + height;
        this.centerX = left + width * 0.5;
        this.centerY = top + height * 0.5;
    }

    public void render(Canvas canvas){
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, p);
    }
}