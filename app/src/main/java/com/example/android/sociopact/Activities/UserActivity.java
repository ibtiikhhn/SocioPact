package com.example.android.sociopact.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.sociopact.Fragments.LoginFragment;
import com.example.android.sociopact.R;

public class UserActivity extends AppCompatActivity {

    Intent receivedIntent;
    LoginFragment loginFragment;
    android.support.v4.app.FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        receivedIntent = getIntent();
        loginFragment = LoginFragment.newInstance();
        fragmentManager = getSupportFragmentManager();


        fragmentManager.beginTransaction().add(R.id.placeholder, loginFragment).commit();
    }

    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
