package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jefferson on 02/05/2016.
 */
public class DificuldadeJogoAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.nivel_act);
    }

    public void facil(View v){
        Intent telaJogo = new Intent(DificuldadeJogoAct.this, TelaJogoActivity.class);
        telaJogo.putExtra("nivel", 1);
        startActivity(telaJogo);
        finish();
    }

    public void medio(View v){
        Intent telaJogo = new Intent(DificuldadeJogoAct.this, TelaJogoActivity.class);
        telaJogo.putExtra("nivel", 2);
        startActivity(telaJogo);
        finish();
    }

    public void dificio(View v){
        Intent telaJogo = new Intent(DificuldadeJogoAct.this, TelaJogoActivity.class);
        telaJogo.putExtra("nivel", 3);
        startActivity(telaJogo);
        finish();
    }

    public void voltaInicio(View v){
        finish();
    }

}
