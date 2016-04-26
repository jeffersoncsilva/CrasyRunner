package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class GameOverTask extends AsyncTask<Void, Void, Void> {
    private Activity act;
    long time;
    private PopupWindow pw;

    public GameOverTask(Activity act){
        this.act = act;
    }
    @Override
    protected Void doInBackground(Void... params) {
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e ){
            Log.i("gameovertask", "ola thread de gameover.");
        }
        time = System.currentTimeMillis();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            Log.i("gameovertask", "game over task executado. tempo: " + time);
            LayoutInflater inflater = (LayoutInflater) this.act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.game_over, null, false);
            ImageView btnFacebook = (ImageView) view.findViewById(R.id.iconFace);
            btnFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(act.getBaseContext(), "Ola, no futuro ira se conectar com o facebook.", Toast.LENGTH_SHORT).show();
                }
            });
            Button btInicio = (Button) view.findViewById(R.id.btnVoltarInicio);

            btInicio.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    act.setContentView(R.layout.activity_main);
                    pw.
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
