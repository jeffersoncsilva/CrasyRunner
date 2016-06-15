package integrador.senac.com.crasyrunner.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import integrador.senac.com.crasyrunner.GPSLocation;
import integrador.senac.com.crasyrunner.R;
import integrador.senac.com.crasyrunner.Som;
import integrador.senac.com.crasyrunner.rank.RankJogo;

public class MainActivity extends AppCompatActivity {
    public static GPSLocation GpsLoc;

    private CallbackManager call;
    private AccessToken acToken;
    private Profile prof;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //cria o som.
        Som.CriaSom(getBaseContext());

        GpsLoc = new GPSLocation(this);

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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    public void inciaJogo(View v) {
        Som.playSom(Som.IrMenu);
        Intent telaNivel = new Intent(MainActivity.this, DificuldadeJogoAct.class);
        startActivity(telaNivel);
    }

    public void rank(View v) {
        if(Profile.getCurrentProfile()  != null) {
            Som.playSom(Som.IrMenu);
            Intent rank = new Intent(MainActivity.this, RankJogo.class);
            startActivity(rank);
            finish();
        }else
        {
            Som.playSom(Som.VoltarMenu);
            Toast.makeText(getBaseContext(), "Logue-se pra ver o rank.", Toast.LENGTH_LONG).show();
        }
    }

    public void sair(View v) {
        Som.playSom(Som.IrMenu);
        finish();
        System.exit(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        call.onActivityResult(requestCode, resultCode, data);
    }

    private void verificaConexaoFace() {
        if (AccessToken.getCurrentAccessToken() != null) {
            acToken = AccessToken.getCurrentAccessToken();
            prof = Profile.getCurrentProfile();
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
                            if (json != null)
                                Log.i("resposta", "Resp: " + json.toString());
                            else
                                Log.i("resposta", "o json e null.");
                        }
                    }
            ).executeAsync();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://integrador.senac.com.crasyrunner/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://integrador.senac.com.crasyrunner/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
