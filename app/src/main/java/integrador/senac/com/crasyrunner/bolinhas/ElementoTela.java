package integrador.senac.com.crasyrunner.bolinhas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.Random;

import integrador.senac.com.crasyrunner.R;
import integrador.senac.com.crasyrunner.Tela;

/**
 * Created by Jefferson on 27/04/2016.
 */
public abstract class ElementoTela {
    public static float TaxaVelocidade = 0.01f;

    protected Bitmap img;
    protected int raio;
    protected float posX;
    protected float posY;
    protected String nomeForma;
    private float velocidade;
    private Context context;

    public ElementoTela(Context context){
        this.context = context;
        this.raio = defineRaio();
        this.img = carregaImagem(context);
        setVelocidade(defineVelocidade());
    }

    public ElementoTela(Bitmap formaJogador, String nomeForma){
        this.raio = defineRaio();
        this.img = formaJogador;
        this.nomeForma = nomeForma;
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
        canvas.drawBitmap(img, posX - raio, posY - raio, null);
    }

    public boolean saiuTela(){
        return ((this.posY - raio) >= Tela.getAltura());
    }

    public String getNomeForma(){return this.nomeForma;}

    public void mudaImagemForma(){
        this.img = carregaImagem(this.context);
    }

    public float getX() { return  this.posX; }

    public float getY() { return  this.posY; }

    public float getRaio() { return  this.raio; }

    public Bitmap getFormaBitmap(){return this.img;}

    private float defineVelocidade(){
        return (float)TaxaVelocidade * Tela.getAltura();
    }

    private int defineRaio(){
        return (int) (Tela.getLargura() * 0.05f);
    }

    private Bitmap carregaImagem(Context context){
        Random r = new Random();
        Bitmap bp;
        switch (r.nextInt(6)){
            case 0:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.b_bola);
                this.nomeForma = "b_bola";
                return Bitmap.createScaledBitmap(bp, raio * 2, raio * 2, false);
            case 1:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.b_estrela);
                this.nomeForma = "b_estrela";
                return Bitmap.createScaledBitmap(bp, raio * 2, raio * 2, false);
            case 2:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.b_losango);
                this.nomeForma = "b_losango";
                return Bitmap.createScaledBitmap(bp, raio * 2, raio * 2, false);
            case 3:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.b_paralelepipedo);
                this.nomeForma = "b_paralelepipedo";
                return Bitmap.createScaledBitmap(bp, raio * 2, raio * 2, false);
            case 4:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.b_quadrado);
                this.nomeForma = "b_quadrado";
                return Bitmap.createScaledBitmap(bp, raio * 2, raio * 2, false);
            case 5:
                bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.b_triangulo);
                this.nomeForma = "b_triangulo";
                return Bitmap.createScaledBitmap(bp, raio * 2, raio * 2, false);
            default:
                return null;
        }
    }
}
