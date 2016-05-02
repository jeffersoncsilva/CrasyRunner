package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Canvas;
import java.util.ArrayList;

/**
 * Created by Jefferson on 27/04/2016.
 */
public class ControleBolinhas {
    private ArrayList<ElementoTela> elementos;
    private int nivel;
    //o tempo esta em milissegundos;
    private long ultimCriacaoBolinha;
    private long tempoCriaBolinha;

    public ControleBolinhas(int nivel){
        this.nivel = nivel;
        this.tempoCriaBolinha = 1000;
        elementos = new ArrayList<>();
    }



    public void update(long currentTimeMillis){
        //verifica se esta na hora de criar o inimigo.
        if(currentTimeMillis >= ultimCriacaoBolinha + tempoCriaBolinha){
            criaBolinha(currentTimeMillis);
        }

        //atualiza update de cada inimigo e verifica se este saiu da tela.
        for(int i = 0; i < elementos.size(); i++) {
            ElementoTela en = elementos.get(i);
            en.update();
            if(en.saiuTela()){
                elementos.remove(i);
            }
        }
    }

    public void draw(Canvas canvas){
        for(ElementoTela en : elementos)
            en.draw(canvas);
    }

    private void criaBolinha(long currentTimeMillis){
        ultimCriacaoBolinha = currentTimeMillis;
        elementos.add(new Bolinhas());
    }

    public ArrayList<ElementoTela> getElementos(){ return this.elementos; }

}
