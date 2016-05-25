package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Jefferson on 22/04/2016.
 */
public class Jogador extends  ElementoTela{
    private Acelerometro ac;

    public Jogador(Context context){
        super();
        //define a posição inicial do jogador.
        this.posX = (Tela.getLargura() / 2) - (raio / 2);
        this.posY = (Tela.getAltura() - raio - 20);
        this.ac = new Acelerometro(context);
        setVelocidade(3.0f);
    }

    public void update() {
        //atualiza a posição do jogador com base no acelerometro.
        float acX = ac.getAcelerationX();
        if (acX > 0 && this.posX + getVelocidade() >= 0)
        {
            //esta indo para esquerda.
            posX -= (acX * getVelocidade());
        }
        if(acX < 0 && ((this.posX + (raio * 2)) - getVelocidade()) <= Tela.getLargura())
        {
            //esta indo para direita.
            posX -= (acX * getVelocidade());
        }
    }
}
