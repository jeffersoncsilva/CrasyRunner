package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Jefferson on 25/05/2016.
 */
public class Background {
    private Bitmap back;

    public Background(Context con){
        Bitmap bm = BitmapFactory.decodeResource(con.getResources(), R.drawable.fundo_jogo);
        this.back = Bitmap.createScaledBitmap(bm, Tela.getLargura(), Tela.getAltura(), false);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(back, 0,0, null);
    }
}
