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
    private ShareFb share;
    private int score;


    public GameOverTask(Activity act, int score){
        this.act = act;
        this.score = score;
        this.share = new ShareFb(act);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            JSONObject jsonObject = new JSONObject();
            try {
                Profile prof = Profile.getCurrentProfile();
                jsonObject.put("name", prof.getName());
                jsonObject.put("gameId", gameId);
                jsonObject.put("score", score);
                jsonObject.put("facebookId", prof.getId());

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://acesso.ws/ranking/services/score/sendScore");

                post.setEntity(new StringEntity(jsonObject.toString(), "UTF8"));
                post.setHeader("Content-type", "application/json");

                Log.i("score", jsonObject.toString());
                HttpResponse resp = httpclient.execute(post);

                if (resp != null) {
                    Log.i("score", "Status: " + resp.getStatusLine().getStatusCode());
                }
            } catch (Exception e) {
                Log.e("score", e.getMessage(), e);
                e.printStackTrace();
            }
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
