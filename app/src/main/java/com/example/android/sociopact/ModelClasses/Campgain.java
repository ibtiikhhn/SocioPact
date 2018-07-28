package com.example.android.sociopact.ModelClasses;

import android.graphics.Bitmap;

public class Campgain {
    String title;
    String info;
    String location;
    String id;
    Bitmap image;

    public Campgain(String title, String info, String location, Bitmap image) {
        this.title = title;
        this.info = info;
        this.location = location;
        this.image = image;
    }

    public Campgain(String id, String title, String info, String location) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Campgain{" +
                "title='" + title + '\'' +
                ", info='" + info + '\'' +
                ", location='" + location + '\'' +
                ", image=" + image +
                '}';
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
