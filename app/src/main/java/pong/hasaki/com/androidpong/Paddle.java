package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle {

    float left, top, right, bottom, centerX, centerY, width, height;
    Paint p = new Paint();

    Paddle(int color, float centerX, float centerY, float width, float height) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
    }

    public void render(Canvas canvas) {
        this.left = centerX - width / 2;
        this.right = centerX + width / 2;
        this.top = centerY - height / 2;
        this.bottom = centerY + height / 2;
        canvas.drawRect(left, top, right, bottom, p);
    }

    public void reset(float x, float y){
        centerX = x;
        centerY = y;
    }
}