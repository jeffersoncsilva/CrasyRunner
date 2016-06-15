package integrador.senac.com.crasyrunner;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
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
 * Created by Aluno on 11/05/2016.
 */
public class ShareFb {
    private Activity act;
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

    public ShareFb(Activity act) {
        this.act = act;
    }

    public void preparaCompartilhamento() {
        canPresentShareDialog = ShareDialog.canShow(ShareLinkContent.class);
        Log.d("presentsharedialog", "Valor: " + canPresentShareDialog);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(act);
        shareDialog.registerCallback(callbackManager, shareCallback);
    }

    public void share() {
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
    }

    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains("publish_actions");
    }

}