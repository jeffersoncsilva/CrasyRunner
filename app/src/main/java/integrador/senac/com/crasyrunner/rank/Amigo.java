package integrador.senac.com.crasyrunner.rank;

import android.graphics.Bitmap;

/**
 * Created by Jefferson on 08/05/2016.
 */
public class Amigo {
    private String nome;
    private Bitmap foto;
    private int id;
    private int pontos;

    public Amigo(){}

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap bmp){this.foto = bmp;}

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