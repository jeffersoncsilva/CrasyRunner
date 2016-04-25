package integrador.senac.com.crasyrunner;

import android.util.Log;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class Rectangle {
    private float largura;
    private float altura;
    private float posX;
    private float posY;

    public Rectangle(float larg, float alt, float x, float y){
        this.largura = larg;
        this.altura = alt;
        this.posX = x;
        this.posY = y;
    }

    public static boolean EstaColidindo(Rectangle rect, Rectangle rect2){
        return  (rect.getPosX() + rect.getLargura() > rect2.getPosX() &&
                 rect.getPosX() < rect2.getPosX() + rect2.getLargura() &&
                 rect.getPosY() + rect.getAltura() > rect2.getPosY() &&
                 rect.getPosY() < rect2.getPosY() + rect2.getAltura());
    }

    public float getLargura() {
        return largura;
    }

    public float getAltura() {
        return altura;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }
}
