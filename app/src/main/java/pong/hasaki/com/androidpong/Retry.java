package pong.hasaki.com.androidpong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class Retry extends Activity {

    GameView v = new GameView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(v);
    }

    public class GameView extends View {

        public GameView(Context context) {
            super(context);
            init();//set all the objects
            run(){ //loop at 60 FPS (1000/60)
                objects.render();//render all of our objects
                ball.move();//logical movement of the ball
                ball.check();//check if ball collides with walls/paddles
            }
        }

        public void init (Canvas canvas){
        }

        public void render (Canvas canvas){

        }
    }
}
