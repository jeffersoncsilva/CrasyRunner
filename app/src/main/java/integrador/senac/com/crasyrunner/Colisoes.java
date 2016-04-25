package integrador.senac.com.crasyrunner;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class Colisoes {

    public static void ColisaoJogadorInimigos(Jogador player, ArrayList<Enemys> inimigos){
        Rectangle play = player.getRectangle();

        for(Enemys en : inimigos){
            if(Rectangle.EstaColidindo(play, en.getRectangle())){
                Log.i("colisao", "esta tendo colisão entre o player e um inimigo.");
            }
        }
    }


}
