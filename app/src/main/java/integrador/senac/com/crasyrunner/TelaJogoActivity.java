package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.os.Bundle;
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

        FrameLayout content = (FrameLayout) findViewById(R.id.conteiner);
        game = new Game(this);
        content.addView(game);
    }

    

}
