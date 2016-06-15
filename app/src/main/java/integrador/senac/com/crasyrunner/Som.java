package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Jefferson on 15/06/2016.
 */
public class Som {
    public static int IrMenu;
    public static int VoltarMenu;
    public static int BolinhaCerta;
    public static int BolinhaErrada;

    private static SoundPool soundPool;

    public static void CriaSom(Context con){
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        IrMenu = soundPool.load(con, R.raw.menu_ir, 0);
        VoltarMenu = soundPool.load(con, R.raw.menu_voltar, 0);
        BolinhaCerta = soundPool.load(con, R.raw.som_certo, 0);
        BolinhaErrada = soundPool.load(con, R.raw.som_errado, 0);
    }

    public static void playSom(int id){
        soundPool.play(id, 1,1,1,0,1);
    }

}
