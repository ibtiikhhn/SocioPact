package com.example.android.sociopact.ModelClasses;

import android.graphics.Bitmap;

public class VolenteerModelClass {
    public String name;
    public String qualification;
    public Bitmap image;
    public String skills;
    public String cause;

    public VolenteerModelClass(String name, String qualification, Bitmap image, String skills, String cause) {
        this.name = name;
        this.qualification = qualification;
        this.image = image;
        this.skills = skills;
        this.cause = cause;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
