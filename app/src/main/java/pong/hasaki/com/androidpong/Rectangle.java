package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangle {

    float left, top, right, bottom;
    Paint p = new Paint();

    Rectangle(int color, float left,  float top, float width, float height) {
        this.left = left;
        this.top = top;
        this.right = this.left + width;
        this.bottom = this.top + height;
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
    }

    public void render(Canvas canvas){
        canvas.drawRect(left, top, right, bottom, p);
    }
}