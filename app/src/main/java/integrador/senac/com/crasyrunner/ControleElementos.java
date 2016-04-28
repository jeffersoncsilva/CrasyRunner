package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jefferson on 27/04/2016.
 */
public class ControleElementos {
    private ArrayList<ElementoTela> elementos;
    private Context context;
    private int nivel;
    private long ultimaCriacaoInimigo;
    private long intervaloCriacaoInimigo;
    private long intervaloCriacaoPowerUp;
    private long ultimaCriacaoPowerUp;

    public ControleElementos(Context con, int nivel){
        this.context = con;
        this.nivel = nivel;
        this.intervaloCriacaoInimigo = 1000;
        this.intervaloCriacaoPowerUp = 5000;
        elementos = new ArrayList<>();
        criaInimigo();
        criaPowerUp();
    }

    public void update(){

        //verifica se esta na hora de criar o inimigo.
        if(System.currentTimeMillis() >= ultimaCriacaoInimigo + intervaloCriacaoInimigo){
            criaInimigo();
        }

        //verifica se esta na hora de criar o PowerUp
        if(System.currentTimeMillis() >= ultimaCriacaoPowerUp + intervaloCriacaoPowerUp){
            criaPowerUp();
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

    private void criaInimigo(){
        ultimaCriacaoInimigo = System.currentTimeMillis();
        switch (nivel) {
            case 1:
                elementos.add(new Enemys(context, R.drawable.inimigo1, "Inimigo"));
                break;
            case 2:
                elementos.add(new Enemys(context, R.drawable.inimigo2, "Inimigo"));
                break;
            case 3:
                elementos.add(new Enemys(context, R.drawable.inimigo3, "Inimigo"));
                break;
            case 4:
                elementos.add(new Enemys(context, R.drawable.inimigo4, "Inimigo"));
                break;
        }
    }

    private void criaPowerUp(){
        ultimaCriacaoPowerUp = System.currentTimeMillis();
        elementos.add(new PowerUp(context, R.drawable.power_up, "Power_Up"));
    }

    public ArrayList<ElementoTela> getElementos(){ return this.elementos; }
}
