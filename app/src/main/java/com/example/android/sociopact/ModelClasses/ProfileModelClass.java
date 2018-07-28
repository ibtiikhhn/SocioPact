package com.example.android.sociopact.ModelClasses;

import android.graphics.Bitmap;

public class ProfileModelClass {
    public static Bitmap image;
    public static String bio;
    public static String about;
    public static String address;
    public static String phone;
    public static String name;

    public ProfileModelClass(Bitmap image, String bio, String about, String address, String phone) {
        this.image = image;
        this.bio = bio;
        this.about = about;
        this.address = address;
        this.phone = phone;

    }

    public ProfileModelClass(String bio, String about, String address, String phone) {
        this.bio = bio;
        this.about = about;
        this.address = address;
        this.phone = phone;
    }

    public static Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public static String getBio() {
        return bio;
    }

    public static String getName() {
        return name;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }

    public static String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public static String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
