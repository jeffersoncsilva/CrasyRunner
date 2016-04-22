package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Jefferson on 19/04/2016.
 */
public class Game extends SurfaceView implements Runnable, View.OnTouchListener {
    //objeto que permite ter acesso ao canvas.
    private final SurfaceHolder holder = getHolder();

    private static boolean gameOver = false;

    private Context context;
    private Background backJogo;
    private Tela tela;

    public Game(Context context){
        super(context);
        this.context = context;
        this.tela = new Tela(context);
        this.backJogo = new Background(1, tela, context);
        gameOver = false;

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        Log.i("updatejogo", "update do jogo iniciado.");
        while (!gameOver) {
            if (!holder.getSurface().isValid()) continue;
            //PRIMEIRO, update da fisica do jogo.
            update();

            // SEGUNDO, desenha na tela.
            Canvas canvas = holder.lockCanvas();    //pego o canvas para poder desenhas na tela
            drawTela(canvas);
            holder.unlockCanvasAndPost(canvas);     //libero o canvas para ser desenhado (mostrado ao jogador)
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent motion){
        return  false;
    }

    private void update(){
        this.backJogo.update();
    }

    private void drawTela(Canvas canvas){
        backJogo.draw(canvas);
    }

}