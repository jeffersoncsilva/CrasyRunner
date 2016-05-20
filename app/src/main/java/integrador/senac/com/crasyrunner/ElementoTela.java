package integrador.senac.com.crasyrunner;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.EventListener;
import java.util.Random;

/**
 * Created by Jefferson on 27/04/2016.
 */
public abstract class ElementoTela {
    private Paint cor;
    protected int raio;
    protected float posX;
    protected float posY;
    protected float velocidade;
    protected String nomeCor;

    public ElementoTela(){
        raio = (int)(Tela.getLargura() * 0.05f);
        cor = new Paint();
        mudaCor();
        velocidade = defineVelocidade();
    }

    private float defineVelocidade(){
        float x = (float)0.01 * Tela.getAltura();
        Log.i("velocidade", "velocidade definida de: " + x);
        return x;
    }

    public ElementoTela(Object[] obj){
        raio = (int)(Tela.getLargura() * 0.05f);
        cor = (Paint)obj[0];
        this.nomeCor = (String)obj[1];
    }

    protected float geraPosicaoAleatoria(){
        Random r = new Random();
        return  (r.nextInt((Tela.getLargura() - (raio * 2)))  + raio);
    }

    protected void setNomeCor(String nomeCor){ this.nomeCor = nomeCor; }

    public abstract void update();

    public void draw(Canvas canvas){
        canvas.drawCircle(this.posX, this.posY, raio, cor);
    }

    public boolean saiuTela(){
        return ((this.posY - raio) >= Tela.getAltura());
    }

    public String getNomeCor(){return this.nomeCor;}

    public void mudaCor(){
        Random r = new Random();
        int q = r.nextInt(10);
        switch(q){
            case 1:
                cor.setColor(0xFFFF0000);
                setNomeCor("vermelho");
                break;
            case 2:
                cor.setColor(0xFF0000FF);
                setNomeCor("azulescuro");
                break;
            case 3:
                cor.setColor(0xFF00BFFF);
                setNomeCor("azulclaro");
                break;
            case 4:
                cor.setColor(0xFF00FF00);
                setNomeCor("verde");
                break;
            case 5:
                cor.setColor(0xFFFFFF00);
                setNomeCor("amarelo");
                break;
            case 6:
                cor.setColor(0xFFFF4500);
                setNomeCor("laranjado");
                break;
            case 7:
                cor.setColor(0xFFFF1493);
                setNomeCor("rosa");
                break;
            case 8:
                cor.setColor(0xFFFF00FF);
                setNomeCor("magenta");
                break;
            case 9:
                cor.setColor(0xFF800080);
                setNomeCor("roxo");
                break;
            case 10:
                cor.setColor(0xFF808080);
                setNomeCor("cinza");
                break;
        }
    }

    public Object[] getObjCor(){
        Object[] obj = new Object[2];
        obj[0] = this.cor;
        obj[1] = this.nomeCor;
        return obj;
    }

    public float getX() { return  this.posX; }

    public float getY() { return  this.posY; }

    public float getRaio() { return  this.raio; }

}
