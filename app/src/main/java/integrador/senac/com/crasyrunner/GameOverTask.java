package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class GameOverTask extends AsyncTask<Void, Void, Void> {
    private Activity act;
    private long time;
    private PopupWindow pw;
    private ShareFb share;


    public GameOverTask(Activity act){
        this.act = act;
        this.share = new ShareFb(act);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            Thread.sleep(100);
            time = System.currentTimeMillis();
        }
        catch(InterruptedException e ){
            Log.i("gameovertask", "ola thread de gameover.");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            //prepara para iniciar o shareFb
            share.preparaCompartilhamento();

            LayoutInflater inflater = (LayoutInflater) this.act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.game_over, null, false);


            Button btInicio = (Button) view.findViewById(R.id.btnVoltarInicio);
            btInicio.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    act.finish(); pw.dismiss();
                }
            });

            Button btShareFb = (Button) view.findViewById(R.id.btShareFb);
            btShareFb.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    share.share();
                }
            });

            pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            LinearLayout layout = new LinearLayout(act);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        }
        catch(Exception e){
            Log.i("gameovertaskerro", "Erro: " + e.toString());
        }
    }
}
