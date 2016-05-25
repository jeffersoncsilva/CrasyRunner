package integrador.senac.com.crasyrunner;

import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Jefferson on 25/05/2016.
 */
public class Cores {
    /*
    * Local de onde foi pego as cores:
    * http://erikasarti.net/html/tabela-cores/
    */
    public static Cor CriaCor(int nivel){
        switch(nivel){
            case 1:
                return CorNivelUm();
            case 2:
                return CorNivelDois();
            case 3:
                return CorNivelTres();
            default:
                return null;
        }
    }

    private static Cor CorNivelUm(){
        Paint p = new Paint();
        Random r = new Random();
        switch(r.nextInt(6)){
            case 1:
                p.setARGB(255,255,0,0);
                return new Cor(p, "Vermelho");
            case 2:
                p.setARGB(255,0,0,255);
                return new Cor(p, "Azul");
            case 3:
                p.setARGB(255,255,165,0);
                return  new Cor(p, "Laranja");
            case 4:
                p.setARGB(255,0,128,0);
                return new Cor(p, "Verde");
            default:
                p.setARGB(255,255,255,0);
                return new Cor(p, "Amarelo");
        }
    }

    private static Cor CorNivelDois(){
        Paint p = new Paint();
        Random r = new Random();
        switch(r.nextInt(6)){
            case 1:
                p.setARGB(255,65,105,225);
                return new Cor(p, "RoyalBlue");
            case 2:
                p.setARGB(255,30,144,255);
                return new Cor(p, "DodgerBlue");
            case 3:
                p.setARGB(255,128,0,0);
                return  new Cor(p, "Maroon");
            case 4:
                p.setARGB(255,165,42,42);
                return new Cor(p, "Brown");
            default:
                p.setARGB(255,255,215,0);
                return new Cor(p, "Gold");
        }
    }

    private static Cor CorNivelTres(){
        Paint p = new Paint();
        Random r = new Random();
        switch(r.nextInt(6)){
            case 1:
                p.setARGB(255,255,105,180);
                return new Cor(p, "HotPink");
            case 2:
                p.setARGB(255,238,130,238);
                return new Cor(p, "Violet");
            case 3:
                p.setARGB(255,233,150,122);
                return  new Cor(p, "DarkSalmon");
            case 4:
                p.setARGB(255,173,216,230);
                return new Cor(p, "LighBlue");
            default:
                p.setARGB(255,176,196,222);
                return new Cor(p, "LightSteelBlue");
        }
    }
}
