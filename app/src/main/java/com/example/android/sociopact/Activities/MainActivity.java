package com.example.android.sociopact.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.sociopact.R;

public class MainActivity extends AppCompatActivity {

//    HomeFeedFragment homeFeedFragment;
//    FragmentManager fragmentManager;
//    LoginFragment loginFragment;
//
    private static final String TAG = "MainActivity";
//    private static final int requestCode = 100;


    SharedPreferences sharedPreferences;
    Intent intentToUserAcitivity;
    Intent intentToUserFeedActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting shared preferences
        sharedPreferences = getSharedPreferences("Registration", MODE_PRIVATE);
        String email = sharedPreferences.getString("email",null);
        String password = sharedPreferences.getString("password", null);
        Log.i(TAG, "onCreate: "+email);

        //if email or password is not stored in the prefrences, go to the userActivity
        if (email==null || password==null) {
            intentToUserAcitivity = new Intent(this, UserActivity.class);
            startActivity(intentToUserAcitivity);
        } else {
            intentToUserFeedActivity = new Intent(this, UserFeed.class);
            startActivity(intentToUserFeedActivity);
        }



    }

}
