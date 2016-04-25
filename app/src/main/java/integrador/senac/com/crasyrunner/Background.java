package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Jefferson on 19/04/2016.
 */
public class Background {
    //representa o fundo do jogo.
    private Bitmap backUm;

    //pra poder fazer o paralaxe do jogo.
    private Paralaxe layerUm;
    private Paralaxe layerDois;

    public Background(int qual, Context context){
        carregaImagens(qual, context);
        criaFundoJogo();
    }

    private void carregaImagens(int qual, Context context){
        Bitmap back;
        switch(qual){
            case 1:
                back = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_1);
                this.backUm = Bitmap.createScaledBitmap(back, Tela.getLargura(), Tela.getAltura(), false);
                break;
            case 2:
                back = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_2);
                this.backUm = Bitmap.createScaledBitmap(back, Tela.getLargura(), Tela.getAltura(), false);
                break;
            case 3:
                back = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_3);
                this.backUm = Bitmap.createScaledBitmap(back, Tela.getLargura(), Tela.getAltura(), false);
                break;
            case 4:
                back = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_4);
                this.backUm = Bitmap.createScaledBitmap(back, Tela.getLargura(), Tela.getAltura(), false);
                break;
        }
    }

    private void criaFundoJogo(){
        //cria o primeiro na posição 0,0
        this.layerUm = new Paralaxe(backUm,0,0,5);
        //criao segundo, na posição: X = 0; Y = -alturaImagem
        this.layerDois = new Paralaxe(backUm, 0,-backUm.getHeight(), 5);
    }

    public void update(){
        this.layerDois.update();
        this.layerUm.update();
    }

    public void draw(Canvas canvas){
        this.layerUm.draw(canvas);
        this.layerDois.draw(canvas);
    }

}
