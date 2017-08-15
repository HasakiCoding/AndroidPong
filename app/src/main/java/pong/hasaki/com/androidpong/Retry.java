package pong.hasaki.com.androidpong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Retry extends Activity {

    GameView v = new GameView(this);
    Thread t = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(v);
    }

    public void onPause() {
        super.onPause();
        v.pause();
    }

    public void onResume() {
        super.onResume();
        v.resume();
    }

    public class GameView extends SurfaceView implements Runnable{

        private boolean check = false;
        public boolean running;
        SurfaceHolder holder;
        Paint black = new Paint();
        Paint white = new Paint();

        public GameView(Context context) {
            super(context);
            holder = getHolder();
        }

        public void run (){
            Canvas canvas = holder.lockCanvas();
            if(!check) {
                init(canvas);//set all the objects
                check = true;
            }
            render(canvas);//render all of our objects
        }

        public void pause(){
            running = false;
            while(true){
                try {
                    t.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume() {
            running = true;
            t = new Thread(this);
            t.start();
        }

        public void init (Canvas canvas){
            black.setColor(Color.BLACK);
            black.setStyle(Paint.Style.FILL);
            white.setColor(Color.WHITE);
            white.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), black);
            canvas.drawRect(0, canvas.getHeight() / 2 - 150, 20, canvas.getHeight() / 2 + 150, white);
            canvas.drawRect(canvas.getWidth(), canvas.getHeight() / 2 - 150, canvas.getWidth() - 20, canvas.getHeight() / 2 + 150, white);
        }

        public void render (Canvas canvas){
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), black);
            canvas.drawRect(0, canvas.getHeight() / 2 - 150, 20, canvas.getHeight() / 2 + 150, white);
            canvas.drawRect(canvas.getWidth(), canvas.getHeight() / 2 - 150, canvas.getWidth() - 20, canvas.getHeight() / 2 + 150, white);
        }
    }
}
