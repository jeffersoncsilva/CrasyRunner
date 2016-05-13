package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Jefferson on 02/05/2016.
 */
public class DificuldadeJogoAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.nivel_act);
    }

    public void escolheDificuldade(View v){
        Button btn = (Button)v;
        Intent telaJogo = new Intent(DificuldadeJogoAct.this, TelaJogoActivity.class);

        if(v.getId() == R.id.btnFacil){
            telaJogo.putExtra("nivel", 1);
        }
        else if(v.getId() == R.id.btnMedio){
            telaJogo.putExtra("nivel", 2);
        }else if (v.getId() == R.id.btnDificio){
            telaJogo.putExtra("nivel", 3);
        }

        startActivity(telaJogo);
        finish();
    }

    public void voltaInicio(View v){
        finish();
    }

}
