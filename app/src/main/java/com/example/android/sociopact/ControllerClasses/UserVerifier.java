package com.example.android.sociopact.ControllerClasses;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class UserVerifier {

    String userName;
    String email;
    String  phoneNumber;
    String password;
    String regexStr = "^[0-9]*$";

    public UserVerifier(String userName, String email, String phoneNumber, String password) {
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;

    }

    public  UserVerifier(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean checkFirstName(String firstName) {
        if (stringContainsNumber(firstName) || firstName == null) {
            return false;
        }else {
            return true;
        }
    }


    public boolean checkEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        return true;
    }

    public boolean checkPassword(String password) {
        if (password != null && password.length() > 4) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isEveryThingFine() {
        if ((checkPassword(password)==true)&&(checkFirstName(userName) == true)  && (checkEmail(email) == true) && (checkPhoneNumber(phoneNumber) == true)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean stringContainsNumber( String s )
    {
        return Pattern.compile( "[0-9]" ).matcher( s ).find();
    }
}
