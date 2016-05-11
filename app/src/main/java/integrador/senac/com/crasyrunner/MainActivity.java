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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private CallbackManager call;
    private AccessToken acToken;
    private Profile prof;

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
                Log.i("facebook", "houve um tipo de fracasso. ERRO: " + error.toString() + " | " + error.getMessage());
            }

        });

        //verifica se ja esta conectado ao facebook.
        verificaConexaoFace();
        /*PEGAR A KEY HASH PARA APP DO FACEBOOK.
        pegaKeyHash();
        */
    }

    public void inciaJogo(View v){
        Intent telaNivel = new Intent(MainActivity.this, DificuldadeJogoAct.class);
        startActivity(telaNivel);
    }

    public void rank(View v){
        Intent rank = new Intent(MainActivity.this, RankJogo.class);
        startActivity(rank);
        finish();
    }

    public void sair(View v){
        finish();
        System.exit(0);
    }

    public void convida(View v){
        Intent convite = new Intent(MainActivity.this, ConvidaAmigosAct.class);
        startActivity(convite);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("activityresult", "foi recebido o resultado de algo.");
        call.onActivityResult(requestCode, resultCode, data);
    }

    private void verificaConexaoFace(){
        if(AccessToken.getCurrentAccessToken() != null) {
            acToken = AccessToken.getCurrentAccessToken();
            prof = Profile.getCurrentProfile();
            Log.i("toenatual", "User: " + acToken.getUserId());
            Log.i("toenatual", "Profile: " + prof.getName());

            //parte responsavel por pegar os amigos no facebook.
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/friends",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            /* handle the result */
                            Log.i("resposta", "algo aconteceu. : " + response.toString());
                            JSONObject json = response.getJSONObject();
                            if(json != null)
                                Log.i("resposta", "Resp: " + json.toString());
                            else
                                Log.i("resposta","o json e null.");
                        }
                    }
            ).executeAsync();
        }
    }

    private void pegaKeyHash(){
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
    }

}
