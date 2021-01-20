package com.example.dat153.SharedClasses;

import android.graphics.Bitmap;


import java.util.UUID;

public class Question  {
    private Campus campus;
    private String imageName;
    private String ID;
    private Bitmap image;


    public Question(Campus campus, Bitmap image) {
        this.imageName = imageName;
        this.campus = campus;
        this.image = image;
        this.ID = UUID.randomUUID().toString();
    }


    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getID() {
        return ID;
    }



    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
