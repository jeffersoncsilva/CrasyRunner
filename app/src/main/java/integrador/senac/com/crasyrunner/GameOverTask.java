package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class GameOverTask extends AsyncTask<Void, Void, Void> {
    private Activity act;

    public GameOverTask(Activity act){
        this.act = act;
    }

    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {

        LayoutInflater inflater = (LayoutInflater) this.act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = inflater.inflate(R.layout.game_over, null, false);
        ImageView btnFacebook = (ImageView)view.findViewById(R.id.iconFace);
        btnFacebook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(act.getBaseContext(), "Ola, no futuro ira se conectar com o facebook.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
