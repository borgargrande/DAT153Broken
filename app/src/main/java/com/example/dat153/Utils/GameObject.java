package com.example.dat153.Utils;

import android.graphics.Bitmap;

public class GameObject {
    private String name;
    private Bitmap image;

    public GameObject(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
