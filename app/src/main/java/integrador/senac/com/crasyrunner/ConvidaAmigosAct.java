package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jefferson on 08/05/2016.
 */
public class ConvidaAmigosAct extends Activity {
    private Button btnVolta;
    private ListView lista;
    private ArrayList<Amigo> amigos;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        criaAmigos();
        setContentView(R.layout.convida_amigos);

        btnVolta = (Button) findViewById(R.id.btnVoltar);
        btnVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio = new Intent(ConvidaAmigosAct.this, MainActivity.class);
                startActivity(inicio);
                finish();
            }
        });

        lista = (ListView) findViewById(R.id.listView);
        /*
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                lista.removeFooterView(view);
                Toast.makeText(getBaseContext(), "Voce convidou um amigo.", Toast.LENGTH_SHORT).show();
            }
        });
        */
        ConvidaAmigosAdapter adapter = new ConvidaAmigosAdapter(amigos, this);
        lista.setAdapter(adapter);
    }


    public void enviaConvite(View v){
        Toast.makeText(getBaseContext(), "Convite enviado.", Toast.LENGTH_SHORT).show();
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
