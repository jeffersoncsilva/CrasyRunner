package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jefferson on 22/04/2016.
 */
public class ControleInimigos {
    private ArrayList<Enemys> inimigos;
    private Context context;
    private int nivel;
    private int[] posicoesCriacao;
    private long ultimaCriacaoInimigo;
    private long intervaloCriacaoInimigo;

    public ControleInimigos(int nivel, Context context) {
        this.context = context;
        this.nivel = nivel;
        intervaloCriacaoInimigo = 2;

        ultimaCriacaoInimigo = System.currentTimeMillis();
        intervaloCriacaoInimigo = 2000;
        inimigos = new ArrayList<>();
        definePosicaoCriacaoInimigos();
        criaInimigo();
    }

    public void update(){
        if(System.currentTimeMillis() >= ultimaCriacaoInimigo + intervaloCriacaoInimigo){
            ultimaCriacaoInimigo = System.currentTimeMillis();
            criaInimigo();
        }

        //atualiza update de cada inimigo e verifica se este saiu da tela.
        for(int i = 0; i < inimigos.size(); i++) {
            Enemys en = inimigos.get(i);
            en.update();

            if(en.saiuTela()){
                inimigos.remove(i);
            }
        }
    }

    public void draw(Canvas canvas){
        for(Enemys en : inimigos)
            en.draw(canvas);
    }

    public ArrayList<Enemys> getListaInimigos(){return inimigos;}

    private void criaInimigo() {
        int x = sorteiaPosicao();
        switch (nivel) {
            case 1:
                inimigos.add(new Enemys(context, R.drawable.inimigo1, x));
                break;
            case 2:
                inimigos.add(new Enemys(context, R.drawable.inimigo2, x));
                break;
            case 3:
                inimigos.add(new Enemys(context, R.drawable.inimigo3, x));
                break;
            case 4:
                inimigos.add(new Enemys(context, R.drawable.inimigo4, x));
                break;
        }
    }

    private int sorteiaPosicao(){
        Random r = new Random();
        int i = r.nextInt(posicoesCriacao.length);
        return  posicoesCriacao[i];
    }

    private void definePosicaoCriacaoInimigos(){
        /*
         * FUNÇÃO QUE DEFINE ONDE OS INIMIGOS IRÃO SER CRIADOS NO EIXO X.
         * primeiro, se divide a tela em tres partes.
         * segundo, divide o resultado da primeira divisao por dois, para saber onde e o meio de um "vao" de onde o inimigo vi passar
         * terceiro, reduz a metade da largura do inimigo para ter certeza de que funciona.
         * quarto e restante, pega-se a primeira posição e soma a largura do vão da posição da primeira imagem.
         *
        */
        posicoesCriacao = new int[3];
        int v = Tela.getLargura() / 3;
        int a = v / 2;
        int pf = a - (Enemys.LARGURA_INIMIGO / 2);

        posicoesCriacao[0] = pf;
        posicoesCriacao[1] = posicoesCriacao[0] + v;
        posicoesCriacao[2] = posicoesCriacao[1] + v;
    }

}