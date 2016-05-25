package integrador.senac.com.crasyrunner;

import android.graphics.Canvas;
import java.util.Random;

/**
 * Created by Jefferson on 27/04/2016.
 */
public abstract class ElementoTela {
    public static float TaxaVelocidade = 0.01f;
    protected Cor cor;
    protected int raio;
    protected float posX;
    protected float posY;
    private float velocidade;
    private int nivel;

    public ElementoTela(int nivel){
        this.nivel = nivel;
        raio = defineRaio();
        cor = Cores.CriaCor(nivel);
        setVelocidade(defineVelocidade());
    }

    public ElementoTela(Cor cor){
        raio =  defineRaio();
        this.cor = cor;
        setVelocidade(defineVelocidade());
    }

    protected float geraPosicaoAleatoria(){
        Random r = new Random();
        return  (r.nextInt((Tela.getLargura() - (raio * 2)))  + raio);
    }

    protected void setVelocidade(float velocidade){
        this.velocidade = velocidade;
    }

    protected float getVelocidade(){return this.velocidade;}

    public abstract void update();

    public void draw(Canvas canvas){
        canvas.drawCircle(this.posX, this.posY, raio, cor.getCor());
    }

    public boolean saiuTela(){
        return ((this.posY - raio) >= Tela.getAltura());
    }

    public String getNomeCor(){return this.cor.getNomeCor();}

    public void mudaCor(){
        this.cor = Cores.CriaCor(nivel);
    }

    public float getX() { return  this.posX; }

    public float getY() { return  this.posY; }

    public float getRaio() { return  this.raio; }

    public Cor getCor(){return this.cor;}

    private float defineVelocidade(){
        return (float)TaxaVelocidade * Tela.getAltura();
    }

    private int defineRaio(){
        return (int) (Tela.getLargura() * 0.05f);
    }
}
