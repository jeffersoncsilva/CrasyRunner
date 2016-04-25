package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Jefferson on 22/04/2016.
 */
public class Jogador {
    private static final int TAM = 50;//tamanho do personagem em largura e altura.
    private final float SPEED = 2.5f;
    private Bitmap img;
    private int posX;
    private int posY;

    private Acelerometro ac;

    public Jogador(Context context, int idImage){
        //define a posição inicial do jogador.
        this.posX = (Tela.getLargura() / 2) - (TAM / 2);
        this.posY = (Tela.getAltura() - TAM - 20);
        Bitmap back = BitmapFactory.decodeResource(context.getResources(), idImage);
        this.img = Bitmap.createScaledBitmap(back, 50, 50, false);
        this.ac = new Acelerometro(context);
    }

    public void update() {
        //atualiza a posição do jogador com base no acelerometro.

        float acX = ac.getAcelerationX();

        if (acX > 0 && this.posX >= 0)
        {
            //esta indo para esquerda.
            posX -= (acX * SPEED);
        }
        if(acX < 0 && this.posX + TAM <= Tela.getLargura())
        {
            //esta indo para direita.
            posX -= (acX * SPEED);
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(img, posX, posY, null);
    }

    public Rectangle getRectangle(){
        return  new Rectangle(TAM, TAM, posX, posY);
    }

}
