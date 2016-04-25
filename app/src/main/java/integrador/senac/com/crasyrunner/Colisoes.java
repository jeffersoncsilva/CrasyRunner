package integrador.senac.com.crasyrunner;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class Colisoes {

    public static void verificaColisao(Jogador player, ArrayList<Enemys> inimigos){
        for(Enemys en : inimigos){
            if(Rectangle.EstaColidindo(player.getRectangle(), en.getRectangle())){
                Log.i("colisao", "esta tendo colisão entre o player e um inimigo.");
            }
        }
    }


}
