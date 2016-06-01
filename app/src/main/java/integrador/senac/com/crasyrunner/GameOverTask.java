package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.facebook.Profile;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class GameOverTask extends AsyncTask<Void, Void, Void> {
    private final String gameId = "ea4a1c89-6530-4925-b9cb-0db59be19232";
    private Activity act;
    private long time;
    private PopupWindow pw;
    private int score;
    private boolean conectFb;

    public GameOverTask(Activity act, int score){
        this.act = act;
        this.score = score;
        this.conectFb = false;


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
        enviaPontuacao();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        verificaDadosEnviados();
    }

    private void enviaPontuacao(){
        try {
            Profile prof = Profile.getCurrentProfile();
            if(prof != null) {
                Bitmap bmp = pegaImageProfile(prof);

                conectFb = true;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", prof.getFirstName());
                jsonObject.put("gameId", gameId);
                jsonObject.put("score", score);
                jsonObject.put("facebookId", prof.getId());
                jsonObject.put("latitude", MainActivity.GpsLoc.getLatitude());
                jsonObject.put("longitude", MainActivity.GpsLoc.getLongitude());

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://acesso.ws/ranking/services/score/sendScore");
                post.setEntity(new StringEntity(jsonObject.toString(), "UTF8"));
                post.setHeader("Content-type", "application/json");
                HttpResponse resp = httpclient.execute(post);

                if (resp != null) {
                    Log.i("score", "Status: " + resp.getStatusLine().getStatusCode());
                }
            }
            else{
                Log.e("score", "usuario nao logado no facebook.");
                conectFb = false;
            }
        } catch (Exception e) {
            Log.e("score", e.getMessage(), e);
            e.printStackTrace();
        }
    }

    private Bitmap pegaImageProfile(Profile prof){
        Bitmap[] bmp = new Bitmap[1];
        Uri uri = prof.getProfilePictureUri(100,100);
        try {
            InputStream in = new URL(uri.toString()).openStream();
            bmp[0] = BitmapFactory.decodeStream(in);
            return  bmp[0];
        } catch (Exception e) {
            Log.e("uriface", "ERRO: " + e.toString());
        }
        return null;
    }

    private void mostraTelaFimJogo(){
        try {
            LayoutInflater inflater = (LayoutInflater) this.act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.game_over, null, false);

            Button btInicio = (Button) view.findViewById(R.id.btnVoltarInicio);//pega referencia para o botao de inicio de jogo.
            btInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

    private void verificaDadosEnviados(){
        if(conectFb) {
            Toast.makeText(act, "Pontuação enviada para o servidor. " , Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(act, "Pontuação não enviada. Conecte-se para poder participar do rank." , Toast.LENGTH_SHORT).show();
        }
    }
}
