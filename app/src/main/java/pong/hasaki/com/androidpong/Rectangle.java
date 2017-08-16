package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle {

    int color;
    float left, top, right, bottom, centerX, centerY, width, height;

    Rectangle(int color, int left,  int top, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
        /*this.left = left;
        this.top = top;
        this.right = left + width;
        this.bottom = top + height;*/
        this.centerX = left + width / 2;
        this.centerY = top + height / 2;
    }

    Rectangle(int color, float centerX, float centerY, float width, float height) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void render(Canvas canvas){
        this.left = centerX - width / 2;
        this.right = centerX + width / 2;
        this.top = centerY - height / 2;
        this.bottom = centerY + height / 2;
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, p);
    }
}