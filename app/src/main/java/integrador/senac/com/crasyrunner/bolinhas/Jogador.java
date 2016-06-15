package integrador.senac.com.crasyrunner.bolinhas;

import android.content.Context;

import integrador.senac.com.crasyrunner.Acelerometro;
import integrador.senac.com.crasyrunner.Tela;

/**
 * Created by Jefferson on 22/04/2016.
 */
public class Jogador extends  ElementoTela{
    private Acelerometro ac;

    public Jogador(Context context){
        super(context);
        //define a posição inicial do jogador.
        this.posX = (Tela.getLargura() / 2) - (raio / 2);
        this.posY = (Tela.getAltura() - raio - 20);
        this.ac = new Acelerometro(context);
        setVelocidade(defineVelocidadeJogador());
    }

    public void update() {
        //atualiza a posição do jogador com base no acelerometro.
        float acX = ac.getAcelerationX();
        float vel = (acX * getVelocidade());
        if (acX > 0 && (this.posX-raio) - vel >= 0)
        {
            //esta indo para esquerda.
            posX -= vel;
        }
        if(acX < 0 && ((this.posX + raio) - vel) <= Tela.getLargura())
        {
            //esta indo para direita.
            posX -= vel;
        }

    }

    public void aumentaVelocidade(){
        float x = (float)0.004 * Tela.getLargura();
        setVelocidade(getVelocidade()+x);
    }

    private float defineVelocidadeJogador(){
        return (float)0.008 * Tela.getLargura();
    }
}
