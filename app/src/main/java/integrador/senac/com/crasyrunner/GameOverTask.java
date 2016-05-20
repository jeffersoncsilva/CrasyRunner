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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class GameOverTask extends AsyncTask<Void, Void, Void> {
    private final String gameId = "ea4a1c89-6530-4925-b9cb-0db59be19232";
    private Activity act;
    private long time;
    private PopupWindow pw;
   //private ShareFb share;
    private int score;
    private boolean conectFb;

    public GameOverTask(Activity act, int score){
        this.act = act;
        this.score = score;
        this.conectFb = false;
    }

    @Override
    protected Void doInBackground(Void... params) {
        enviaPontuacao();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
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

            //verifica se o jogo está conectado no facebook, se estiver mostra o botao de compartilhar se nao estiver mostra o botao de se conectar.
            if(conectFb) {
                Toast.makeText(act, "Pontuação enviada para o servidor. " , Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(act, "Pontuação não enviada. Conecte-se para poder participar do rank." , Toast.LENGTH_SHORT).show();
            }

            //mostra na tela.
            pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            LinearLayout layout = new LinearLayout(act);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        }
        catch(Exception e){

            Log.i("gameovertaskerro", "Erro: " + e.toString());
        }
    }

    private void enviaPontuacao(){
        try {
            Profile prof = Profile.getCurrentProfile();
            if(prof != null) {
                conectFb = true;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", prof.getFirstName());
                jsonObject.put("gameId", gameId);
                jsonObject.put("score", score);
                jsonObject.put("facebookId", prof.getId());
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
}
