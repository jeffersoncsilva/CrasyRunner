package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Jefferson on 22/04/2016.
 */
public class Enemys {
    public static final int LARGURA_INIMIGO = 50;
    public static final int ALTURA_INIMIGO = 50;
    private Bitmap img;
    private float posX;
    private float posY;
    private float velocidadeY = 2.5f;



    public Enemys(Context context, int idImage, int posX){
        Bitmap back = BitmapFactory.decodeResource(context.getResources(), idImage);
        this.img = Bitmap.createScaledBitmap(back, LARGURA_INIMIGO, ALTURA_INIMIGO, false);
        this.posX = posX;
        this.posY = -ALTURA_INIMIGO;
    }

    public void update(){
        this.posY+=this.velocidadeY;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(img, posX, posY, null);
    }

    public boolean saiuTela(){
        return (this.posY >= Tela.getAltura());
    }

    public float getLargura() {return this.img.getWidth();}
    public float getAltura() {return this.img.getHeight();}

    public Rectangle getRectangle(){
        return  new Rectangle(LARGURA_INIMIGO, ALTURA_INIMIGO, posX, posY);
    }
}
