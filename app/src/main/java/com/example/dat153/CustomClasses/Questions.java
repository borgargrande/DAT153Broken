package com.example.dat153.CustomClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Questions implements Serializable {

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
