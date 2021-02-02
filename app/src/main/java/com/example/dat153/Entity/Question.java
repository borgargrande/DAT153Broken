package com.example.dat153.Entity;

import android.graphics.Bitmap;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "question_table")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    private Campus campus;
    private Bitmap image;

    public Question(Campus campus) {
        this.campus = campus;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
