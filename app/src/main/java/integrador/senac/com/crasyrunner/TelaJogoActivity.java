package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by Jefferson on 19/04/2016.
 */
public class TelaJogoActivity extends Activity {
    private Game game;


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);


        setContentView(R.layout.tela_jogo);

        //pega os extras da intent que fala o nivel do jogo que foi escolhido.
        Bundle extras = getIntent().getExtras();
        int i = extras.getInt("nivel");

        FrameLayout content = (FrameLayout) findViewById(R.id.conteiner);
        game = new Game(this, i);
        content.addView(game);
    }


    @Override
    public void onStop(){
        Game.SetGameOverTrue();
        MainActivity.GpsLoc.removeUpdatesLocation();
        super.onStop();
    }

}
