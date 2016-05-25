package integrador.senac.com.crasyrunner;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefferson on 27/04/2016.
 */
public class ControleBolinhas {
    private ArrayList<ElementoTela> elementos;
    private int nivel;
    //o tempo esta em milissegundos;
    private long ultimCriacaoBolinha;      //tempo da ultima bolinha criada.
    private long intervaloCriacaoBolinha;  //intervalo de criação de cada bolinha.

    private long ultimaBolinhaCorJogador;  //tempo da ultima bolinha do jogador que foi criada.
    private long intervaloBolinhaJogador;  //intervalo de criação de bolinha da cor do jogador.

    private Jogador player;

    public ControleBolinhas(int nivel, Jogador jogador){
        this.nivel = nivel;
        elementos = new ArrayList<>();
        this.player = jogador;
        defineTempos();
    }

    public void update(long currentTimeMillis){
        //cria a bolinha de cor aleatoria.
        if (currentTimeMillis >= ultimCriacaoBolinha + intervaloCriacaoBolinha) {
            criaBolinha(currentTimeMillis);
            Log.e("bolinhaaleatoria", "bolinha aleatoria criada.");
        }
        //cria a bolinha da cor do jogador.
        if(currentTimeMillis >= ultimaBolinhaCorJogador + intervaloBolinhaJogador){
            criaBolinaJogador(currentTimeMillis);
            Log.e("bolinhajogador", "bolinha da cor do jogador criada.");
        }


        //atualiza update de cada bolinha e verifica se esta saiu da tela.
        List<Integer> itensRemover = new ArrayList<>();
        for(int i = 0; i < elementos.size(); i++) {
            ElementoTela en = elementos.get(i);
            en.update();
            if(en.saiuTela()){
                itensRemover.add(i);
            }
        }
        for(Integer i : itensRemover){
            elementos.remove((int)i);
            Log.i("bolinhas", String.format("removido: %d, qtd bolinhas: %d", i, elementos.size()));
        }
    }

    public void draw(Canvas canvas){
        Log.e("desenhobolinhas", "QTD bolinhas: " + elementos.size());
        for(ElementoTela en : elementos)
            en.draw(canvas);
    }

    private void criaBolinha(long currentTimeMillis){
        ultimCriacaoBolinha = currentTimeMillis;
        elementos.add(new Bolinhas());
    }

    private void criaBolinaJogador(long currentTime){
        ultimaBolinhaCorJogador = currentTime;
        elementos.add(new Bolinhas(this.player.getObjCor()));
    }

    public ArrayList<ElementoTela> getElementos(){ return this.elementos; }

    private void defineTempos(){
        switch (nivel){
            case 1:
                intervaloBolinhaJogador = 2000;
                intervaloCriacaoBolinha = 1000;
                break;
            case 2:
                intervaloBolinhaJogador = 2000;
                intervaloCriacaoBolinha = 1000;
                break;
            case 3:
                intervaloCriacaoBolinha = 2000;
                intervaloBolinhaJogador = 1000;
                break;
        }
    }

}
