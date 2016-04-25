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

    public Paralaxe(Bitmap bitmap, float posX, float posY, float velocidade){
        this.img = bitmap;
        this.x = posX;
        this.y = posY;
        this.velocidade = velocidade;
    }

    public void update(){

        this.y += this.velocidade;

        if(this.y >= Tela.getAltura()){
            this.y = -this.img.getHeight();
        }

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(img, x, y, null);
    }

}
