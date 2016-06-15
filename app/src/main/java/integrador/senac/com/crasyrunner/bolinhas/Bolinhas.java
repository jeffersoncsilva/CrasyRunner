package integrador.senac.com.crasyrunner.bolinhas;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Jefferson on 02/05/2016.
 */
public class Bolinhas extends ElementoTela {

    public Bolinhas(Context context){
        super(context);
        posY = -(raio * 2);
        posX = geraPosicaoAleatoria();
    }

    public Bolinhas(Bitmap bitmap, String nomeForma){
        super(bitmap, nomeForma);
        posY = -(raio * 2);
        posX = geraPosicaoAleatoria();
    }

    @Override
    public void update() {
        posY += getVelocidade();
    }
}
