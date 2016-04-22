package integrador.senac.com.crasyrunner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inciaJogo(View v){
        Log.i("inicio", "Iniciando o jogo.");
        Intent telaJogo = new Intent(MainActivity.this, TelaJogoActivity.class);
        startActivity(telaJogo);
    }

    public void rank(View v){
        Log.i("rank", "mostrando rank do jogo.");
    }

    public void sair(View v){
        finish();
        System.exit(0);
    }
}
