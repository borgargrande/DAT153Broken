package com.example.dat153.CustomClasses;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.dat153.Persistent.ImageSaver;

import java.io.Serializable;
import java.util.UUID;

public class Question implements Serializable {
    private Campus campus;
    private String imageName;
    private String ID;
    private transient Bitmap image;


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

    public Bitmap loadImage(Context context) {
        if (this.image == null){
            this.image = new ImageSaver(context).setFileName(ID).setDirectoryName("images").load();
        }
        return image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
