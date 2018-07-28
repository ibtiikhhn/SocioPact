package com.example.android.sociopact.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.sociopact.Activities.UserFeed;
import com.example.android.sociopact.ControllerClasses.UserVerifier;
import com.example.android.sociopact.Interfaces.OnSignInComplete;
import com.example.android.sociopact.NetworkUtils.PostSignIn;
import com.example.android.sociopact.R;

import org.json.JSONException;
import org.json.JSONObject;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class LoginFragment extends Fragment implements OnSignInComplete {

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
    private static final String TAGG = "MySi";


    EditText userEmail;
    EditText userPassword;
    Button signIn;
    Button signUp;
    ProgressBar progressBar;

    String email;
    String password;

    TextFieldBoxes emailTextField;
    TextFieldBoxes passwordTextField;

    HomeFeedFragment homeFeedFragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String emaill;
    String pass;

    PostSignIn postSignIn;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = this.getActivity().getSharedPreferences("Registration", Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        homeFeedFragment = new HomeFeedFragment();

        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        userEmail = v.findViewById(R.id.emailEditText);
        userPassword = v.findViewById(R.id.passwordEditText);
        signIn = v.findViewById(R.id.signInButton);
        signUp = v.findViewById(R.id.signUpButton);

        emailTextField = v.findViewById(R.id.emailBox);
        passwordTextField = v.findViewById(R.id.passwordBox);

        emaill= sharedPreferences.getString("email", null);
        pass = sharedPreferences.getString("password", null);

        userEmail.setText(emaill);
        userPassword.setText(pass);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = userEmail.getText().toString();
                password = userPassword.getText().toString();

                UserVerifier userVerifier = new UserVerifier("email", password);
                if (!userVerifier.checkEmail(email)) {
                    emailTextField.setSecondaryColor(R.color.A400red);
                    userEmail.setError("Invalid Email");

                }
                else if (!userVerifier.checkPassword(password)) {
                    userPassword.setError("Length Too Short");
                }

                if (userVerifier.checkEmail(email) == true && userVerifier.checkPassword(password) == true) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("email", email);
                        jsonObject.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    progressBar.setVisibility(View.VISIBLE);
                    postSignIn = new PostSignIn(LoginFragment.this, jsonObject, getContext(), progressBar, getActivity());
//                    postSignIn.execute(Urls.SignInUrl);
                }

                else {
                    Toast.makeText(getActivity(), "Email or pass is not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment signUpFragment = new SignUpFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.placeholder, signUpFragment, "Get This")
                        .addToBackStack(null).commit();
            }
        });

        return v;
    }

    @Override
    public void onDetach() {
        getActivity().finish();
        super.onDetach();
    }

    @Override
    public void onTaskCompleted(String key,String id,String auth ) {
        editor = getActivity().getSharedPreferences("Registration", Context.MODE_PRIVATE).edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("key", key);
        editor.putString("id", id);
        editor.putString("auth", auth);
        editor.apply();

        Intent intent = new Intent(getActivity(), UserFeed.class);
        startActivity(intent);

    }
}
