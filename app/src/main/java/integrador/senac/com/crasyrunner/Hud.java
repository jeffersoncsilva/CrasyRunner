package integrador.senac.com.crasyrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by Jefferson on 27/04/2016.
 */
public class Hud {
    private Paint cor;
    private String distancia;
    private String pontos;
    private int dist;
    private int pt;

    private long ultimoSegundo;
    private long diferencaTempo;

    public Hud(){
        dist = 0;
        pt = 0;
        cor = new Paint();
        cor.setColor(0xFFFFFFFF);
        cor.setTextSize(45);
        cor.setTypeface(Typeface.DEFAULT_BOLD);
        pontos = "P: 0";
        distancia = "D: 0";
        diferencaTempo = 1000;
        ultimoSegundo = System.currentTimeMillis();
    }

    public void update(long currentTimeMillis){
        if(currentTimeMillis >= ultimoSegundo + diferencaTempo) {
            dist += 1;
            distancia = "D: " + dist;
            ultimoSegundo = System.currentTimeMillis();
        }
    }

    public void draw(Canvas canvas){
        canvas.drawText(pontos, 25, 40, cor);
        canvas.drawText(distancia, 350, 40, cor);
    }

    public void aumentaPontos(int valor){
        pt += valor;
        pontos = "P: " + pt;
    }

    public int getPt(){return  this.pt; }
}
