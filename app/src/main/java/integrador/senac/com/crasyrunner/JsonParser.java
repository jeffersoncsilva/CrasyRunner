package integrador.senac.com.crasyrunner;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Aluno on 18/05/2016.
 */
public class JsonParser {

    private String getRequest(String url){
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpPost = new HttpGet(url);

            HttpResponse httpResponse = null;
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream is = httpEntity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            Log.e("errojoson", "ERRRO: " + e.toString(), e);
            return "";
        }

    }

    public JSONObject getJSONFromUrl(String url) {
        try {
            return new JSONObject(getRequest(url));
        } catch (JSONException e) {
            Log.e("errojoson", "ERRRO: " + e.toString(), e);
            return null;
        }
    }

    public JSONArray getJSONArrayFromUrl(String url){
        try {
            return new JSONArray(getRequest(url));
        } catch (JSONException e) {
            Log.e("errojoson", "ERRRO: " + e.toString(), e);
            return null;
        }
    }


}
