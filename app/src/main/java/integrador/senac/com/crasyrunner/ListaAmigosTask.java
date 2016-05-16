package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Aluno on 16/05/2016.
 */
public class ListaAmigosTask extends AsyncTask<String, String, String> {

    private ProgressDialog progressDialog;
    private Activity act;
    private ListView lista;
    private ArrayList<Amigo> amigos;

    public ListaAmigosTask(Activity act, ListView list){
        this.progressDialog = new ProgressDialog(act);
        this.act = act;
        this.lista = list;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setMessage("Buscando dados...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    protected void onPostExecute(String v) {
        ListaAmigosAdapter adapter = new ListaAmigosAdapter(amigos, act);
        lista.setAdapter(adapter);
    }

}
