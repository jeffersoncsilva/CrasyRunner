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

    public Jogador(Context context, int idImage){
        super();
        //define a posição inicial do jogador.
        this.posX = (Tela.getLargura() / 2) - (largura / 2);
        this.posY = (Tela.getAltura() - altura - 20);
        Bitmap back = BitmapFactory.decodeResource(context.getResources(), idImage);
        this.img = Bitmap.createScaledBitmap(back, (int)largura, (int)altura, false);
        this.ac = new Acelerometro(context);
        this.velocidade = 3.0f;
    }

    public void update() {
        //atualiza a posição do jogador com base no acelerometro.
        float acX = ac.getAcelerationX();
        if (acX > 0 && this.posX >= 0)
        {
            //esta indo para esquerda.
            posX -= (acX * velocidade);
        }
        if(acX < 0 && this.posX + largura <= Tela.getLargura())
        {
            //esta indo para direita.
            posX -= (acX * velocidade);
        }
    }
}
