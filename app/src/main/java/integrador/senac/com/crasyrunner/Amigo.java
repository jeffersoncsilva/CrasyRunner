package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Jefferson on 08/05/2016.
 */
public class Amigo {
    private String nome;
    private Bitmap foto;
    private int id;
    private int pontos;

    public Amigo(String nome, Context context, int id){
        this.nome = nome;
        Bitmap back = BitmapFactory.decodeResource(context.getResources(), R.drawable.jogaodr);
        this.foto = Bitmap.createScaledBitmap(back, 100,100,true);
        this.id = id;
    }

    public Amigo(Context context){
        Bitmap back = BitmapFactory.decodeResource(context.getResources(), R.drawable.jogaodr);
        this.foto = Bitmap.createScaledBitmap(back, 100,100,true);
    }

    public Bitmap getFoto() {
        return foto;
    }

    public String getNome(){
        return  this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getId() {return  this.id; }

    public void setId(int id){
        this.id = id;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
