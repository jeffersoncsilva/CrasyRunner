package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import integrador.senac.com.crasyrunner.activities.MainActivity;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class GameOverTask extends AsyncTask<Void, Void, Void> {
    private final String gameId = "ea4a1c89-6530-4925-b9cb-0db59be19232";
    private Activity act;
    private long time;
    private PopupWindow pw;
    private int score;

    public GameOverTask(Activity act, int score){
        this.act = act;
        this.score = score;
    }

    @Override
    protected void onPreExecute() {
        act.runOnUiThread(new Runnable(){
            public void run(){
                mostraTelaFimJogo();
            }
        });
    }

    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

    }

    private void mostraTelaFimJogo(){
        try {
            //fonte
            //Typeface fontRobo = Typeface.createFromAsset(this.act.getBaseContext().getAssets(), "fonts/ARLRDBD.ttf");


            LayoutInflater inflater = (LayoutInflater) this.act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.game_over_screen, null, false);

            TextView tx = (TextView) view.findViewById(R.id.game_over);
            //tx.setTypeface(fontRobo);


            TextView txt_gameOver = (TextView)view.findViewById(R.id.go_pontuacao);
            txt_gameOver.setText("Voce conseguiu " + Hud.getPt() + " pontos.");
            //txt_gameOver.setTypeface(fontRobo);

            Button btInicio = (Button) view.findViewById(R.id.btnVoltarInicio);//pega referencia para o botao de inicio de jogo.
            btInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Som.playSom(Som.VoltarMenu);
                    act.finish();
                    pw.dismiss();
                }
            });

            //mostra na tela.
            pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            LinearLayout layout = new LinearLayout(act);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        }
        catch(Exception e){
            Log.i("gameovertaskerro", "Erro: " + e.toString());
        }
    }
}
