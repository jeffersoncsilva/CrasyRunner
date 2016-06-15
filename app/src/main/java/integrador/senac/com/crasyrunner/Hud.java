package integrador.senac.com.crasyrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Jefferson on 27/04/2016.
 */
public class Hud {
    private Paint cor;
    private String pontos;
    private int dist;
    private static int pt;


    public Hud(){
        dist = 0;
        pt = 0;
        cor = new Paint();
        cor.setColor(0xFFFFFFFF);
        cor.setTextSize(45);
        cor.setTypeface(Typeface.DEFAULT_BOLD);
        pontos = "P: 0";
    }

    public void draw(Canvas canvas){
        canvas.drawText(pontos, 25, 40, cor);
    }

    public void aumentaPontos(int valor){
        pt += valor;
        pontos = "P: " + pt;
    }

    public static int getPt(){return  pt; }
}
