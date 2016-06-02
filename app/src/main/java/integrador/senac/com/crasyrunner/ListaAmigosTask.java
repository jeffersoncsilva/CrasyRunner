package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aluno on 16/05/2016.
 */
public class ListaAmigosTask extends AsyncTask<String, String, String> {
    private final String URL_PONTOS = "http://acesso.ws/ranking/services/score/listAllScore/ea4a1c89-6530-4925-b9cb-0db59be19232";

    private ProgressDialog progressDialog;
    private Activity act;
    private ListView lista;
    private ArrayList<Amigo> amigos;
    private JSONArray jArray;

    public ListaAmigosTask(Activity act, ListView list){
        this.progressDialog = new ProgressDialog(act);
        this.act = act;
        this.lista = list;
        amigos = new ArrayList<Amigo>();
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Buscando dados...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            JsonParser jParse = new JsonParser();
            JSONArray jArray = jParse.getJSONArrayFromUrl(URL_PONTOS);
            for(int i = 0; i < jArray.length(); i++){
                JSONObject jOb = jArray.getJSONObject(i);
                Amigo am = new Amigo();
                am.setNome(jOb.getString("name"));
                am.setId(jOb.getInt("facebookId"));
                am.setPontos(jOb.getInt("score"));
                am.setFoto(getBimapJsonObj(jOb));

                amigos.add(am);
            }

        }catch (Exception e){
            Log.e("erroamigo", "ERRO: " + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String v) {
        progressDialog.cancel();
        ListaAmigosAdapter adapter = new ListaAmigosAdapter(amigos, act);
        lista.setAdapter(adapter);
    }

    private Bitmap getBimapJsonObj(JSONObject job){
        try {
            byte[] imgBytes = job.get("picture").toString().getBytes();
            return BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
        }
        catch (Exception e){
            Log.e("listaamigostask", "ERRO: " + e.toString(), e);
        }

        return  null;
    }

}
