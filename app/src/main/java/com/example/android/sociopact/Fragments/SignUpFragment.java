package com.example.android.sociopact.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.sociopact.ControllerClasses.UserVerifier;
import com.example.android.sociopact.Interfaces.SignUpPostInterface;
import com.example.android.sociopact.ModelClasses.SignUpModelClass;
import com.example.android.sociopact.NetworkUtils.PostSignUp;
import com.example.android.sociopact.R;

import org.json.JSONObject;

import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements SignUpPostInterface{

    public static String TAG = "Fragment";

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    EditText userName;
    EditText userEmail;
    EditText userPhone;
    EditText userPassword;

    TextFieldBoxes nameBox;
    TextFieldBoxes emailBox;
    TextFieldBoxes phoneBox;
    TextFieldBoxes passwordBox;

    Button signUp;
    Button signIn;

    String name;
    String email;
    String phone;
    String password;
    ProgressBar progressBar;

    UserVerifier userVerifier;
    SignUpModelClass signUpModelClass;
    HomeFeedFragment homeFeedFragment;
    PostSignUp postSignUp;

    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeFeedFragment = new HomeFeedFragment();



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_fragment, container, false);

        progressBar = v.findViewById(R.id.signUpProgressbar);
        userName = v.findViewById(R.id.nameEditText);
        userEmail = v.findViewById(R.id.emailEditText);
        userPhone = v.findViewById(R.id.phoneEditText);
        userPassword = v.findViewById(R.id.passwordEditText);
        signUp = v.findViewById(R.id.signUpButton);
        signIn = v.findViewById(R.id.signInButtonRef);

        nameBox = v.findViewById(R.id.nameBox);
        emailBox = v.findViewById(R.id.emailBoxS);
        phoneBox = v.findViewById(R.id.phoneBox);
        passwordBox = v.findViewById(R.id.passwordBoxS);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = userName.getText().toString();
                email = userEmail.getText().toString();
                phone = userPhone.getText().toString();
                password = userPassword.getText().toString();

                userVerifier = new UserVerifier(name, email, phone, password);


                if (userVerifier.isEveryThingFine() == true) {
                    signUpModelClass = new SignUpModelClass(name, email, phone, password);
                    SignUpModelClass.name = name;
                    SignUpModelClass.email = email;
                    SignUpModelClass.phone = phone;
                    SignUpModelClass.password = password;

                    try {

                        JSONObject jsonObject = new JSONObject();

                        jsonObject.put("name",name);
                        jsonObject.put("phone", phone);
                        jsonObject.put("email", email);
                        jsonObject.put("password", password);
                        Log.i(TAG, "onClick: "+name+email+phone+password);

                        postSignUp = new PostSignUp(SignUpFragment.this,jsonObject, getContext(),progressBar,getActivity());
//                        postSignUp.execute(Urls.postUrl);

                    } catch (Exception e) {
                    }

                }else {
                    Toast.makeText(getActivity(), "Not fine", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.placeholder, loginFragment, "Get This")
                        .addToBackStack(null).commit();
            }
        });

        return v;
    }



    @Override
    public void setResult(String result) {
        if (result == "true") {
            editor = getActivity().getSharedPreferences("Registration", Context.MODE_PRIVATE).edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.putString("phone", phone);
            editor.putString("password", password);
            editor.apply();
            LoginFragment loginFragment = new LoginFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.placeholder, loginFragment, "Get This")
                    .addToBackStack(null).commit();
            /*Intent intent = new Intent(getActivity(), UserFeed.class);
            startActivity(intent)*/
        }
    }
}

