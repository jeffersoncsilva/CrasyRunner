package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
    private ControleElementos elementos;

    private Jogador jogador;

    private PopupWindow pw; //para poder exibir a tela de fim de jogo.

    private Hud hud;

    public Game(Activity activity){
        super(activity.getBaseContext());
        this.gameOver = false;
        this.context = activity.getBaseContext();
        this.act = activity;
        Tela.IniciaTela(context);
        this.backJogo = new Background(1, context);
        this.elementos = new ControleElementos(context, 1);
        this.jogador = new Jogador(context, R.drawable.jogaodr);
        this.hud = new Hud();

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
        this.elementos.update();
        this.jogador.update();
        this.hud.update();
        //verifica se hove colisão do jogador com algum inimigo ou PowerUp.
        colisaoElementos(elementos.getElementos());
    }

    private void drawTela(Canvas canvas){
        this.backJogo.draw(canvas);
        this.elementos.draw(canvas);
        this.jogador.draw(canvas);
        this.hud.draw(canvas);
    }

    private void colisaoElementos(ArrayList<ElementoTela> el){
        Rectangle play = this.jogador.getRectangle();
        //for(ElementoTela en : el){
          for(int i = 0; i < el.size(); i++){
            ElementoTela en = el.get(i);
            if(Rectangle.EstaColidindo(play, en.getRectangle())){
                //verifica se houve colisao com o inimigo, se houve, fim de jogo.
                if(en.getRotulo().equals("Inimigo")) {
                    gameOver = true;
                    new GameOverTask(act).execute();
                }
                //verifica se houve colisao com o power up. se houve, aumenta a pontuacao.
                else if(en.getRotulo().equals("Power_Up")){
                    el.remove(i);
                    this.hud.aumentaPontos(5);
                }
            }
        }
    }
}