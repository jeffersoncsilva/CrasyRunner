package integrador.senac.com.crasyrunner;

import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Jefferson on 25/05/2016.
 */
public class Cor {
    private Paint cor;
    private String nomeCor;

    public Cor(Paint c, String nome){
        this.cor = c;
        this.nomeCor = nome;
    }

    public Paint getCor(){ return this.cor;}

    public String getNomeCor() {return this.nomeCor;}

}
