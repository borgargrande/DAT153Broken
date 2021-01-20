package com.example.dat153.SharedClasses;

import android.app.Application;


public class SharedObject extends Application {
    private Questions questions = new Questions();

    public Questions getQuestions() {
        return questions;
    }

}
