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
    private DisplayMetrics metrics;

    public Tela(Context conetxt){

        WindowManager wm = (WindowManager) conetxt.getSystemService(Context.WINDOW_SERVICE);
        Display diplay = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        diplay.getMetrics(metrics);
        Log.i("Display", "altura: " + metrics.heightPixels);

    }

    public int getAltura(){
        return metrics.heightPixels;
    }
    public int getLargura(){ return  metrics.widthPixels; }

}
