package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.PopupWindow;
import java.util.ArrayList;

/**
 * Created by Jefferson on 19/04/2016.
 */
public class Game extends SurfaceView implements Runnable {
    //objeto que permite ter acesso ao canvas.
    private final SurfaceHolder holder = getHolder();
    private static boolean gameOver = false;

    private Activity act;
    private Context context;
    private Background backJogo;
    private ControleBolinhas bolinhasControl;

    private Jogador jogador;

    private PopupWindow pw; //para poder exibir a tela de fim de jogo.

    private Hud hud;

    private int nivel;

    private long currentTimeMillis;

    public Game(Activity activity, int nivel){
        super(activity.getBaseContext());

        this.gameOver = false;
        this.context = activity.getBaseContext();
        this.act = activity;
        Tela.IniciaTela(context);
        this.backJogo = new Background(1, context);
        this.jogador = new Jogador(context);
        this.bolinhasControl = new ControleBolinhas(nivel, this.jogador);
        this.hud = new Hud();
        this.nivel = nivel;
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

    private void update(){
        currentTimeMillis = System.currentTimeMillis();

        this.backJogo.update();
        this.bolinhasControl.update(currentTimeMillis);
        this.jogador.update();
        this.hud.update(currentTimeMillis);
        colisaoElementos(bolinhasControl.getElementos());
    }

    private void drawTela(Canvas canvas){
        this.backJogo.draw(canvas);
        this.bolinhasControl.draw(canvas);
        this.jogador.draw(canvas);
        this.hud.draw(canvas);
    }

    private void colisaoElementos(ArrayList<ElementoTela> el){
        //distancia = Math.sqrt( Math.pow( (x1 - x2),2 ) + Math.pow( (y1 - y2),2 ) );
        double distancia;
        try {
            for (int i = 0; i < el.size(); i++) {
                ElementoTela e = el.get(i);
                distancia = Math.sqrt(Math.pow((e.getX() - jogador.getX()), 2) + Math.pow((e.getY() - jogador.getY()), 2));
                if (distancia <= (e.getRaio() * 2)) {
                    //houve colisão com o player. compara se foi com uma bolinha da msm cor, se tiver sido, soma a pontuação, se não for GameOver.
                    if (e.getNomeCor().equals(jogador.getNomeCor())) {
                        hud.aumentaPontos(5);
                        el.remove(i);
                        continue;
                    } else {
                        gameOver = true;
                        new GameOverTask(act).execute();
                        break;
                    }
                }
            }
        }catch (Exception e){
            Log.e("erro",  "ERRO: " + e.toString() + " | " + e.getMessage());
        }
    }

}