package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Jefferson on 22/04/2016.
 */
public class Enemys extends ElementoTela {

    public Enemys(Context context, int idImage, String rotulo){
        super();
        Bitmap back = BitmapFactory.decodeResource(context.getResources(), idImage);
        this.img = Bitmap.createScaledBitmap(back, (int)this.largura, (int)this.altura, false);
        this.posX = geraPosicaoAleatoria();
        this.posY = -this.altura;
        setRotulo(rotulo);
    }

    public void update(){
        this.posY+=this.velocidade;
    }
}
