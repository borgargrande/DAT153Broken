package com.example.dat153.SharedClasses;

import android.app.Application;

import java.util.ArrayList;


public class SharedObject extends Application {
    private ArrayList<Question> questions;

    public SharedObject() {
        this.questions = new ArrayList<>();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
