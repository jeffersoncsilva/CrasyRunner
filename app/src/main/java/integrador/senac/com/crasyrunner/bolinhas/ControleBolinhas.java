package integrador.senac.com.crasyrunner.bolinhas;

import android.content.Context;
import android.graphics.Canvas;

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

    //pra saber se ta na hora de aumentar a velocidade das bolinhas.
    private long ultimaAlteracaoVelocidade;
    private long tempoAlteracaoVelocidade;

    private Jogador player;
    private Context context;

    public ControleBolinhas(int nivel, Jogador jogador, Context context){
        //volta a taxa de velocidade ao normal de 0.01f dos elementos da tela.
        ElementoTela.TaxaVelocidade = 0.01f;
        this.nivel = nivel;
        elementos = new ArrayList<>();
        this.player = jogador;
        defineTempos();
        this.context = context;
    }

    public void update(long currentTimeMillis){
        //cria a bolinha de cor aleatoria.
        if (currentTimeMillis >= ultimCriacaoBolinha + intervaloCriacaoBolinha) {
            criaBolinha(currentTimeMillis);
        }
        //cria a bolinha da cor do jogador.
        if(currentTimeMillis >= ultimaBolinhaCorJogador + intervaloBolinhaJogador){
            criaBolinaJogador(currentTimeMillis);
        }

        if(currentTimeMillis >= ultimaAlteracaoVelocidade + tempoAlteracaoVelocidade){
            aumentaVelocidadeBolinhas(currentTimeMillis);
            aumentaQtdBolinhas();
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
        }
    }

    public void draw(Canvas canvas){
        for(ElementoTela en : elementos)
            en.draw(canvas);
    }

    public ArrayList<ElementoTela> getElementos(){ return this.elementos; }

    private void defineTempos(){
        ultimaAlteracaoVelocidade = System.currentTimeMillis();
        switch (nivel){
            case 1:
                intervaloBolinhaJogador = 1800;
                intervaloCriacaoBolinha = 1000;

                tempoAlteracaoVelocidade = 13000;
                break;
            case 2:
                intervaloBolinhaJogador = 2000;
                intervaloCriacaoBolinha = 800;

                tempoAlteracaoVelocidade = 10000;
                break;
            case 3:
                intervaloCriacaoBolinha = 2200;
                intervaloBolinhaJogador = 600;
                tempoAlteracaoVelocidade = 7000;
                break;
        }
    }

    private void criaBolinha(long currentTimeMillis){
        ultimCriacaoBolinha = currentTimeMillis;
        elementos.add(new Bolinhas(this.context));
    }

    private void criaBolinaJogador(long currentTime){
        ultimaBolinhaCorJogador = currentTime;
        elementos.add(new Bolinhas(this.player.getFormaBitmap(), this.player.getNomeForma()));
    }

    private void aumentaVelocidadeBolinhas(long currentMillis){
        //aumenta a velocidade das bolinhas.
        ElementoTela.TaxaVelocidade += 0.001f;
        player.aumentaVelocidade();
        ultimaAlteracaoVelocidade = currentMillis;
    }

    private void aumentaQtdBolinhas(){
        switch (nivel){
            case 1:
                if(intervaloBolinhaJogador < 3000)
                    intervaloBolinhaJogador += 50;

                if(intervaloCriacaoBolinha > 100)
                    intervaloCriacaoBolinha -= 25;

                if(tempoAlteracaoVelocidade > 10000)
                    tempoAlteracaoVelocidade -= 50;

                break;
            case 2:
                if(intervaloBolinhaJogador < 4000)
                    intervaloBolinhaJogador += 80;

                if(intervaloCriacaoBolinha > 100)
                    intervaloCriacaoBolinha -= 40;

                if(tempoAlteracaoVelocidade > 5000)
                    tempoAlteracaoVelocidade -= 75;
                break;
            case 3:
                if(intervaloBolinhaJogador < 5000)
                    intervaloBolinhaJogador += 110;

                if(intervaloCriacaoBolinha > 100)
                    intervaloCriacaoBolinha -= 60;

                if(tempoAlteracaoVelocidade > 2000)
                    tempoAlteracaoVelocidade -= 100;
                break;
        }
    }
}