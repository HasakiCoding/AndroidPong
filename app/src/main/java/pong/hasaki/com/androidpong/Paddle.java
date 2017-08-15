package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle {

    int color;
    float left, top, right, bottom, centerX, centerY, width, height;

    Paddle(int color, int centerX, int centerY, int width, int height) {
        this.color = color;
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
    }

    public void render(Canvas canvas) {
        this.left = this.centerX - width / 2;
        this.right = this.centerX + width / 2;
        this.top = this.centerY - height / 2;
        this.bottom = this.centerY + height / 2;
        Paint p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, p);
    }
}