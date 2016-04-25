package integrador.senac.com.crasyrunner;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Jefferson on 19/04/2016.
 */
public class Tela {
    private static DisplayMetrics metrics;

    public static void IniciaTela(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display diplay = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        diplay.getMetrics(metrics);
    }

    public static int getAltura(){
        return metrics.heightPixels;
    }
    public static int getLargura(){ return  metrics.widthPixels; }


}
