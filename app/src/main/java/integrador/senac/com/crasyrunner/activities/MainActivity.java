package integrador.senac.com.crasyrunner.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import integrador.senac.com.crasyrunner.GPSLocation;
import integrador.senac.com.crasyrunner.R;
import integrador.senac.com.crasyrunner.Som;

public class MainActivity extends AppCompatActivity {
    public static GPSLocation GpsLoc;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cria o som.
        Som.CriaSom(getBaseContext());
        GpsLoc = new GPSLocation(this);
        setContentView(R.layout.activity_main);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void inciaJogo(View v) {
        Som.playSom(Som.IrMenu);
        Intent telaNivel = new Intent(MainActivity.this, DificuldadeJogoAct.class);
        startActivity(telaNivel);
    }

    public void sair(View v) {
        Som.playSom(Som.IrMenu);
        finish();
        System.exit(0);
    }
}
