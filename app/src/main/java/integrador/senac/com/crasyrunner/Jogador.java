package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Jefferson on 22/04/2016.
 */
public class Jogador {
    private Bitmap img;
    private int posX = 150;
    private int posY = 150 ;
    private int velocidadeY;

    public Jogador(Context context, int idImage){
        Bitmap back = BitmapFactory.decodeResource(context.getResources(), idImage);
        this.img = Bitmap.createScaledBitmap(back, 50, 50, false);
    }


    public void update(){
        Log.i("jogador", "update do jogoador.");
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(img, posX, posY, null);
    }
}
