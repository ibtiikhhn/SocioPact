package com.example.android.sociopact.NetworkUtils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.sociopact.Interfaces.SignUpPostInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostSignUp extends AsyncTask<String, Void, String> {

    public static final String TAG = "The Tags";
    SignUpPostInterface listener;
    JSONObject postData;
    Context context;
    ProgressBar progressBar;
    Activity activity;

    public PostSignUp( SignUpPostInterface listener,JSONObject postData, Context context, ProgressBar progressBar, Activity activity) {
        this.postData = postData;
        this.context = context;
        this.progressBar = progressBar;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.connect();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection
                    .getOutputStream()));
            writer.write(postData.toString());
            Log.i(TAG, "doInBackground: "+postData.toString());
            writer.flush();

            int responseCode = httpURLConnection.getResponseCode();
            Log.i(TAG, "Response code = "  + responseCode);
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (httpURLConnection.getInputStream()));
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while((line = reader.readLine()) != null){
                    stringBuilder.append(line);
                }
                httpURLConnection.disconnect();
                return stringBuilder.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        String key = null;
        if (s != null) {
            try {
                Log.i(TAG, "onPostExecute string: " + s);
                JSONObject jsonObject = new JSONObject(s);
                key = jsonObject.get("Success").toString();
                Log.i(TAG, "onPostExecute: " + key);
                ;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
        progressBar.setVisibility(View.INVISIBLE);

        if (key.equals("Sign Up Successful")) {
            Log.i(TAG, "onPostExecute: we are on success");
            listener.setResult("true");
            /*SignUpFragment signUpFragment = new SignUpFragment();
            signUpFragment.onSuccess();*/
        }
        else {
//            Toast.makeText(context, s + "Some thing is wrong", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onPostExecute: We are not on success");
        }

    }

}
