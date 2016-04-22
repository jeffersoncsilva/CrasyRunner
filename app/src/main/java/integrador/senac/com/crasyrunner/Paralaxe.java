package integrador.senac.com.crasyrunner;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Jefferson on 19/04/2016.
 */
public class Paralaxe {
    private Bitmap img;
    private float x;
    private float y;
    private float velocidade;
    private Tela tela;

    public Paralaxe(Bitmap bitmap, float posX, float posY, float velocidade, Tela tela){
        this.img = bitmap;
        this.x = posX;
        this.y = posY;
        this.velocidade = velocidade;
        this.tela = tela;
    }

    public void update(){

        this.y += this.velocidade;

        if(this.y >= this.tela.getAltura()){
            this.y = -this.img.getHeight();
        }

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(img, x, y, null);
    }

}
