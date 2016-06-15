package integrador.senac.com.crasyrunner.rank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import integrador.senac.com.crasyrunner.Som;
import integrador.senac.com.crasyrunner.activities.MainActivity;
import integrador.senac.com.crasyrunner.R;

/**
 * Created by Jefferson on 08/05/2016.
 */
public class RankJogo extends Activity {
    private final String URL_GET_SCORE = "http://acesso.ws/ranking/services/score/listAllScore/ea4a1c89-6530-4925-b9cb-0db59be19232";
    private Button btnVoltar;
    private ListView lista;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        Som.CriaSom(getBaseContext());
        setContentView(R.layout.rank);
        lista = (ListView) findViewById(R.id.listView);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(RankJogo.this, MainActivity.class);
                Som.playSom(Som.VoltarMenu);
                startActivity(inicio);
                finish();
            }
        });
        //pega a lista de amigos do servidor e mostra na tela
        new ListaAmigosTask(this, lista).execute();
    }

}
