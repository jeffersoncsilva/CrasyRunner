package integrador.senac.com.crasyrunner;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by Jefferson on 27/04/2016.
 */
public class ElementoTela {
    protected Bitmap img;
    protected float largura;
    protected float altura;
    protected float posX;
    protected float posY;
    protected float velocidade = 4.0f;
    protected String rotulo;

    public ElementoTela(){
        largura = (Tela.getLargura() * 0.15f);
        altura = (Tela.getAltura() * 0.15f);
    }

    public void update(){}


    public void draw(Canvas canvas){
        canvas.drawBitmap(img, this.posX, this.posY, null);
    }

    protected float geraPosicaoAleatoria(){
        Random r = new Random();
        return  r.nextInt(Tela.getLargura());
    }

    public boolean saiuTela(){
        return (this.posY >= Tela.getAltura());
    }

    public float getLargura() {return this.img.getWidth();}
    public float getAltura() {return this.img.getHeight();}
    public String getRotulo(){return this.rotulo;}

    public Rectangle getRectangle(){
        return  new Rectangle(getLargura(), getAltura(), posX, posY);
    }

    protected void setRotulo(String rotulo){ this.rotulo = rotulo; }
}
