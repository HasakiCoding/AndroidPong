package pong.hasaki.com.androidpong;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Paddle {

    //Rectangle pad;
    int color;
    float centerX, centerY, width, height, left, top, right, bottom;
    Paint paint;

    Paddle(int color, int centerX, int centerY, int width, int height) {
        this.color = color;
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
        //pad = new Rectangle(color, centerX, centerY, width, height);
    }

    public void render(Canvas canvas) {
        this.left = centerX - width / 2;
        this.right = centerX + width / 2;
        this.top = centerY - height / 2;
        this.bottom = centerY + height / 2;
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(left, top, right, bottom, paint);
    }
}
