package com.example.android.sociopact.NetworkUtils;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.sociopact.Interfaces.CreateCampgainInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class PostCreateCampgain extends AsyncTask<String,Void,String> {

    public static final String TAG = "The Tags";
    CreateCampgainInterface listener;
    Context context;
    ProgressBar progressBar;
    Activity activity;
    JSONObject jsonObject;

    public PostCreateCampgain(CreateCampgainInterface listener, Context context, ProgressBar progressBar, Activity activity,JSONObject jsonObject) {
        this.listener = listener;
        this.context = context;
        this.progressBar = progressBar;
        this.activity = activity;
        this.jsonObject = jsonObject;
    }

    @Override
    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.connect();


            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpURLConnection
                    .getOutputStream()));
            writer.write(jsonObject.toString());
            Log.i(TAG, "doInBackground: "+jsonObject.toString());
            writer.flush();


            int responseCode = httpURLConnection.getResponseCode();
            Log.i(TAG, "doInBackground: "+responseCode);
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
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i(TAG, "onPostExecute: "+s);
        progressBar.setVisibility(View.INVISIBLE);
        if (s != null) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                String key = jsonObject.get("Success").toString();
                Log.i(TAG, "onPostExecute: " + key);
                if (key.equals("Campaign Created Successfully")) {
                    listener.setResult(true);
                } else {
                    listener.setResult(false);
                }
            } catch (JSONException e) {

                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show();
            listener.setResult(false);
        }
    }
}
