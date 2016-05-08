package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jefferson on 08/05/2016.
 */
public class RankJogo extends Activity {
    private Button btnVoltar;
    private ListView lista;

    //lista de amigo somente para teste
    private ArrayList<Amigo> amigos;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        criaAmigos();
        setContentView(R.layout.rank);
        lista = (ListView) findViewById(R.id.listView);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(RankJogo.this, MainActivity.class);
                startActivity(inicio);
                finish();
            }
        });

        //seta os amigos na tela.
        ListaAmigosAdapter adapter = new ListaAmigosAdapter(amigos, this);
        lista.setAdapter(adapter);
    }

    private void criaAmigos(){
        amigos = new ArrayList<>();
        amigos.add(new Amigo("Jose", this, 1));
        amigos.add(new Amigo("Manoel", this, 2));
        amigos.add(new Amigo("Josefina", this, 3));
        amigos.add(new Amigo("Marileusa", this, 4));
        amigos.add(new Amigo("Mariane", this, 5));
        amigos.add(new Amigo("Any", this, 6));
    }
}
