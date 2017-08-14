package pong.hasaki.com.androidpong;

import android.graphics.Canvas;

public class Paddle {

    Rectangle pad;

    Paddle(int color, int centerX, int centerY, int width, int height) {

        pad = new Rectangle(color, centerX, centerY, width, height);
    }

    public void move(int moveX, int moveY) {
        pad.move(moveX, moveY);
    }

    public void render(Canvas canvas) {
        pad.render(canvas);
    }
}
