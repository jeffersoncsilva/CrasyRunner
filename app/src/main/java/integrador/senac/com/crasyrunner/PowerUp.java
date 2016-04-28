package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Jefferson on 27/04/2016.
 */
public class PowerUp extends ElementoTela{

    public PowerUp(Context con, int idImage, String rotulo){
        super();
        Bitmap back = BitmapFactory.decodeResource(con.getResources(), idImage);
        this.img = Bitmap.createScaledBitmap(back, 50, 50, false);
        this.posX = geraPosicaoAleatoria();
        this.posY = -altura;
        setRotulo(rotulo);
    }

    public void update(){
        this.posY += this.velocidade;
    }

}
