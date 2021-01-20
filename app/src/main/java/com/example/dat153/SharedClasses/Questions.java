package com.example.dat153.SharedClasses;

import java.util.ArrayList;

public class Questions  {

    private ArrayList<Question> list;

    public Questions() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Question> getList() {
        return list;
    }

    public void addQuestion(Question question) {
        this.list.add(question);
    }
}
