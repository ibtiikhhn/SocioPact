package com.example.android.sociopact.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.sociopact.Fragments.HomeFeedFragment;
import com.example.android.sociopact.Fragments.ProfileFragment;
import com.example.android.sociopact.Fragments.UpdateProfile;
import com.example.android.sociopact.Fragments.VolunteersFragment;
import com.example.android.sociopact.R;

public class UserFeed extends AppCompatActivity {

    public static final String HELL = "UserFeed ma poncha gya";
    public static final int PICK_IMAGE = 1;
    private DrawerLayout mDrawerLayout;
    UpdateProfile updateProfile;

    Intent intentSent;
    Intent intent;
    FragmentManager fragmentManager;
    HomeFeedFragment homeFeedFragment;
    SharedPreferences.Editor editor;

    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed);
        profileFragment = ProfileFragment.newInstance();
        editor = getSharedPreferences("Registration", Context.MODE_PRIVATE).edit();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight

                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            // close drawer when item is tapped
                            case R.id.logout:
                                editor.remove("email");
                                editor.remove("password");
                                editor.commit();
                                intentSent = new Intent(UserFeed.this, UserActivity.class);
                                startActivity(intentSent);
                                mDrawerLayout.closeDrawers();
                                break;

                            case R.id.profile:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.placeholderUser, profileFragment).addToBackStack(null).commit();
                                mDrawerLayout.closeDrawers();
                                break;

                            case R.id.home:
                                homeFeedFragment = HomeFeedFragment.newInstance();
                                fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().add(R.id.placeholderUser, homeFeedFragment).commit();
                                mDrawerLayout.closeDrawers();
                                break;


                            case R.id.volunteers:
                                VolunteersFragment volunteersFragment = new VolunteersFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.placeholderUser, volunteersFragment)
                                        .addToBackStack(null).commit();
                                mDrawerLayout.closeDrawers();
                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here
                        }
                        return true;
                    }

                });

        Button button = navigationView.getHeaderView(0).findViewById(R.id.updateProfile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile = UpdateProfile.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.placeholderUser, updateProfile, "Get This").addToBackStack(null).commit();
                mDrawerLayout.closeDrawers();
            }
        });


        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);



        intent = getIntent();
        homeFeedFragment = HomeFeedFragment.newInstance();
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.placeholderUser, homeFeedFragment).commit();
        Log.i(HELL, "Reached");

    }




    @Override
    public void onBackPressed() {

        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //call super
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);


        }
        return super.onOptionsItemSelected(item);
    }

}
