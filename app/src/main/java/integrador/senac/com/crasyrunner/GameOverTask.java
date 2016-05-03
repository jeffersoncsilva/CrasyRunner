package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by Jefferson on 25/04/2016.
 */
public class GameOverTask extends AsyncTask<Void, Void, Void> {
    private Activity act;
    long time;
    private PopupWindow pw;
    private boolean canPresentShareDialog;
    private PendingAction pendingAction = PendingAction.NONE;
    private ShareDialog shareDialog;
    private CallbackManager callbackManager;

    private FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
        @Override
        public void onCancel() {
            Log.d("HelloFacebook", "Canceled");
        }

        @Override
        public void onError(FacebookException error) {
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
            //String title = getString(R.string.error);
            //String alertMessage = error.getMessage();
            //showResult(title, alertMessage);
        }

        @Override
        public void onSuccess(Sharer.Result result) {
            Log.d("HelloFacebook", "Success!");
            if (result.getPostId() != null) {
                Log.d("olafacebook", "houvesucesso.");
                /*String title = getString(R.string.success);
                String id = result.getPostId();
                String alertMessage = getString(R.string.successfully_posted_post, id);
                showResult(title, alertMessage);
                */
            }
        }

        private void showResult(String title, String alertMessage) {
            Toast.makeText(act.getBaseContext(), "Ola, resultado recebido: " + alertMessage, Toast.LENGTH_LONG).show();
            /*
            new AlertDialog.Builder(HelloFacebookSampleActivity.this)
                    .setTitle(title)
                    .setMessage(alertMessage)
                    .setPositiveButton(R.string.ok, null)
                    .show();
            */
        }
    };

    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }

    public GameOverTask(Activity act){
        this.act = act;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e ){
            Log.i("gameovertask", "ola thread de gameover.");
        }
        time = System.currentTimeMillis();
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        try {
            canPresentShareDialog = ShareDialog.canShow(ShareLinkContent.class);
            Log.d("presentsharedialog", "Valor: " + canPresentShareDialog);
            LayoutInflater inflater = (LayoutInflater) this.act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.game_over, null, false);
            callbackManager = CallbackManager.Factory.create();
            shareDialog = new ShareDialog(act);
            shareDialog.registerCallback(callbackManager, shareCallback);

            Button btInicio = (Button) view.findViewById(R.id.btnVoltarInicio);
            btInicio.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    act.finish();
                    pw.dismiss();
                }
            });


            Button btShareFb = (Button) view.findViewById(R.id.btShareFb);
            btShareFb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(act.getBaseContext(), "clique no btn de share", Toast.LENGTH_SHORT).show();

                Profile profile = Profile.getCurrentProfile();
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentTitle("Hello Facebook")
                        .setContentDescription("The 'Hello Facebook' sample  showcases simple Facebook integration")
                        .setContentUrl(Uri.parse("http://developers.facebook.com/docs/android"))
                        .build();

                if (canPresentShareDialog) {
                    shareDialog.show(linkContent);
                } else if (profile != null && hasPublishPermission()) {
                    ShareApi.share(linkContent, shareCallback);
                } else {
                    pendingAction = PendingAction.POST_STATUS_UPDATE;
                }

            }});

            pw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            LinearLayout layout = new LinearLayout(act);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
        }
        catch(Exception e){
            Log.i("gameovertaskerro", "Erro: " + e.toString());
        }
    }

    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains("publish_actions");
    }

}
