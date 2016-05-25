package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
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
    private ControleBolinhas bolinhasControl;
    private Jogador jogador;
    private Hud hud;
    private int nivel;
    private Background back;

    public Game(Activity activity, int nivel){
        super(activity.getBaseContext());
        this.gameOver = false;
        this.act = activity;
        Tela.IniciaTela(activity.getBaseContext());
        this.jogador = new Jogador(activity.getBaseContext(), nivel);
        this.bolinhasControl = new ControleBolinhas(nivel, this.jogador);
        this.hud = new Hud();
        this.nivel = nivel;
        this.back = new Background(nivel);
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
        long currentTimeMillis = System.currentTimeMillis();
        this.bolinhasControl.update(currentTimeMillis);
        this.jogador.update();
        this.hud.update(currentTimeMillis);
        colisaoElementos(bolinhasControl.getElementos());
    }

    private void drawTela(Canvas canvas){
        this.back.draw(canvas);
        this.bolinhasControl.draw(canvas);
        this.jogador.draw(canvas);
        this.hud.draw(canvas);
    }

    private void colisaoElementos(ArrayList<ElementoTela> el){
        double distancia;
        ElementoTela e;
        try {
            for (int i = 0; i < el.size(); i++) {
                e = el.get(i);
                distancia = Math.sqrt(Math.pow((e.getX() - jogador.getX()), 2) + Math.pow((e.getY() - jogador.getY()), 2));
                if (distancia <= (e.getRaio() * 2)) {
                    if (e.getNomeCor().equals(jogador.getNomeCor())) {
                        hud.aumentaPontos(5);
                        el.remove(i);
                        if(nivel == 2){
                            jogador.mudaCor();
                        }else if (nivel == 3){
                            jogador.mudaCor();
                        }
                        continue;
                    } else {
                        gameOver = true;
                        new GameOverTask(act, hud.getPt()).execute();
                        break;
                    }
                }
            }
        }catch (Exception ex){
            Log.e("erro",  "ERRO: " + ex.toString() + " | " + ex.getMessage());
        }
    }

}