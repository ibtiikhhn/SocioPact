package com.example.android.sociopact.NetworkUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.sociopact.Interfaces.AllCampgainsInterface;
import com.example.android.sociopact.ModelClasses.Campgain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetAllCampgains extends AsyncTask<String,Void,String> {

    public static final String TAG = "The Tags";
    AllCampgainsInterface listener;
    Context context;
    ProgressBar progressBar;
    Activity activity;
    String title = null;
    String id = null;
    String intro = null;
    String location = null;
    Bitmap image = null;
    List list;
    Campgain campgain;


    public GetAllCampgains(AllCampgainsInterface listener, Context context, ProgressBar progressBar, Activity activity) {
        this.listener = listener;
        this.context = context;
        this.progressBar = progressBar;
        this.activity = activity;
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
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.connect();
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
                list = new ArrayList();

        if (s != null) {
            try {
                JSONArray jsonarray = new JSONArray(s);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    title = jsonobject.getString("CampaignDetails_title");
                    id = jsonobject.getString("CampaignDetails_id");
                    intro = jsonobject.getString("CampaignDetails_Intro");
                    location = jsonobject.getString("CampaignDetails_Location");
                    campgain = new Campgain(title, intro, location, image);
                    listener.allCampgains(campgain);
                    /*HomeFeedFragment.addLists(campgain);
                    list.add(jsonobject);*/
                }
//                image = jsonObject1.getString("");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            progressBar.setVisibility(View.GONE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
