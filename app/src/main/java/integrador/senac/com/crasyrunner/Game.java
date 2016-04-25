package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jefferson on 19/04/2016.
 */
public class Game extends SurfaceView implements Runnable, View.OnTouchListener {
    //objeto que permite ter acesso ao canvas.
    private final SurfaceHolder holder = getHolder();

    private static boolean gameOver = false;

    private Activity act;
    private Context context;
    private Background backJogo;
    private ControleInimigos inimigos;

    private Jogador jogador;

    public Game(Activity activity){
        super(activity.getBaseContext());
        this.context = activity.getBaseContext();
        this.act = activity;
        Tela.IniciaTela(context);
        this.backJogo = new Background(1, context);
        gameOver = false;
        this.inimigos = new ControleInimigos(1, context);
        this.jogador = new Jogador(context, R.drawable.jogaodr);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
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
        this.inimigos.update();
        this.jogador.update();
        //verifica se hove colisão do jogador com algum inimigo.
        if(Colisoes.ColisaoJogadorInimigos(jogador, inimigos.getListaInimigos())){
            gameOver = true;
            new GameOverTask(act);
        }
    }

    private void drawTela(Canvas canvas){
        this.backJogo.draw(canvas);
        this.inimigos.draw(canvas);
        this.jogador.draw(canvas);
    }

}