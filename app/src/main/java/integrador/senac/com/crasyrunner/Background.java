package integrador.senac.com.crasyrunner;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Jefferson on 25/05/2016.
 */
public class Background {
    private Paint cor;

    public Background(int nivel){
        mudaCorFundo(nivel);
    }

    public void mudaCorFundo(int nivel){
        this.cor = novaCor(nivel);
    }

    public void draw(Canvas canvas){
        canvas.drawPaint(cor);
    }

    private Paint novaCor(int nivel){
        Paint p = new Paint();
        if(nivel == 1){
            p.setARGB(255,176,224,230);
            return  p;
        }
        else if(nivel == 2){
            p.setARGB(255,210,180,140);
            return p;
        }
        else {
            p.setARGB(255, 34, 139, 34);
            return p;
        }
    }
}
