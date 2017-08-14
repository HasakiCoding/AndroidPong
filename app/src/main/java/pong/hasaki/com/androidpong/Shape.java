package pong.hasaki.com.androidpong;

public class Shape {

    float centerX, centerY;

    Shape(int centerX, int centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    Shape(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void move(int moveX, int moveY) {
        this.setPosition(moveX, moveY);
    }

    public void setPosition(int moveX, int moveY) {
        this.centerX += moveX;
        this.centerY += moveY;
    }
}
