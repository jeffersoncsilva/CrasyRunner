package integrador.senac.com.crasyrunner;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private CallbackManager call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        call = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        LoginButton lbtn = (LoginButton) findViewById(R.id.login_button);
        lbtn.setReadPermissions("user_friends");

        lbtn.registerCallback(call, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("facebook", "houve um tipo de sucesso: " + loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                Log.i("facebook", "houve um tipo de cancelamento.");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("facebook", "houve um tipo de fracasso.");
            }
        });


        /*PEGAR A KEY HASH PARA APP DO FACEBOOK.
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "integrador.senac.com.crasyrunner",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("erro", e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            Log.e("erro", e.getMessage(), e);
        }
        */

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("activityresult","foi recebido o resultado de algo.");
        call.onActivityResult(requestCode, resultCode, data);
    }
}
