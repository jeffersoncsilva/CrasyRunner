package integrador.senac.com.crasyrunner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_main);
    }

    public void inciaJogo(View v){
        Intent telaJogo = new Intent(MainActivity.this, TelaJogoActivity.class);
        startActivity(telaJogo);
    }

    public void rank(View v){
        Log.i("rank", "mostrando rank do jogo.");
        Button btn = (Button) findViewById(R.id.btnVoltar);
    }

    public void sair(View v){
        finish();
        System.exit(0);
    }
}
