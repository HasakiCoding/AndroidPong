package pong.hasaki.com.androidpong;

import android.graphics.Paint;

public class Shape {

    float centerX, centerY;
    int color;
    Paint p;

    Shape(int centerX, int centerY, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
    }

    Shape(float centerX, float centerY, int color) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.color = color;
    }

    public void move(int moveX, int moveY) {
        this.setPosition(moveX, moveY);
    }

    public void setPosition(int moveX, int moveY) {
        this.centerX += moveX;
        this.centerY += moveY;
    }

    public void render(){
        p = new Paint();
        p.setColor(this.color);
        p.setStyle(Paint.Style.FILL);
    }
}
