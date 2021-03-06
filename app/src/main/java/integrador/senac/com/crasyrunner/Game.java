package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import integrador.senac.com.crasyrunner.bolinhas.ControleBolinhas;
import integrador.senac.com.crasyrunner.bolinhas.ElementoTela;
import integrador.senac.com.crasyrunner.bolinhas.Jogador;

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
        Tela.IniciaTela(activity.getBaseContext());
        this.gameOver = false;
        this.act = activity;
        this.jogador = new Jogador(activity.getBaseContext());
        this.bolinhasControl = new ControleBolinhas(nivel, this.jogador, activity.getBaseContext());
        this.hud = new Hud();
        this.nivel = nivel;
        this.back = new Background(activity.getBaseContext());

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){
        while (!gameOver) {
            try {
                if (!holder.getSurface().isValid()) continue;
                //PRIMEIRO, update da fisica do jogo.
                update();
                // SEGUNDO, desenha na tela.
                Canvas canvas = holder.lockCanvas();    //pego o canvas para poder desenhas na tela
                drawTela(canvas);
                holder.unlockCanvasAndPost(canvas);     //libero o canvas para ser desenhado (mostrado ao jogador)
            }
            catch(Exception e){
             Log.e("errocanvas","ERRO: " + e.toString(), e);
            }
        }
    }

    private void update(){
        long currentTimeMillis = System.currentTimeMillis();
        this.bolinhasControl.update(currentTimeMillis);
        this.jogador.update();
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
                    if (e.getNomeForma().equals(jogador.getNomeForma())) {
                        hud.aumentaPontos(5);
                        el.remove(i);
                        //dispara som vitoria
                        Som.playSom(Som.BolinhaCerta);
                        if(nivel == 2){
                            jogador.mudaImagemForma();
                        }else if (nivel == 3){
                            jogador.mudaImagemForma();
                        }
                        continue;
                    } else {
                        //dispara som de derrota
                        Som.playSom(Som.BolinhaErrada);
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

    public static void SetGameOverTrue(){
        gameOver = true;
    }

}