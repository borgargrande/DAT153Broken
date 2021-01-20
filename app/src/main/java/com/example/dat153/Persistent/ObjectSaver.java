package com.example.dat153.Persistent;


import android.content.Context;

import com.example.dat153.SharedClasses.Question;
import com.example.dat153.SharedClasses.Questions;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

/*
public class ObjectSaver implements Serializable {

    Context context;
    private static final String TAG = "SaveState";

    public ObjectSaver(Context context) {
        this.context = context;
    }


    public boolean saveQuestion(Question question) {
        try {
            Gson gson = new Gson();
            String yourObjectJson = gson.toJson(question);
            String yourFilePath = context.getFilesDir() + "/" + question.getID();
            File yourFile = new File(yourFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(yourFile);
            fileOutputStream.write(yourObjectJson.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            return true;

        }
        return false;
    }


    public boolean saveQuestions(Questions questions) {
        try {
            Gson gson = new Gson();
            String yourObjectJson = gson.toJson(questions);
            String yourFilePath = context.getFilesDir() + "/" + "QUESTIONS";
            File yourFile = new File(yourFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(yourFile);
            fileOutputStream.write(yourObjectJson.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            return true;

        }
        return false;
    }

    public Question getQuestion(String id) {
        Gson gson = new Gson();
        String text = "";
        try {
            String yourFilePath = context.getFilesDir() + "/" + id;
            File yourFile = new File(yourFilePath);
            InputStream inputStream = new FileInputStream(yourFile);
            StringBuilder stringBuilder = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                text = stringBuilder.toString();
                Question yourObject = gson.fromJson(text, Question.class);
                return yourObject;
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return null;

    }

    public Questions getQuestions() {
        Gson gson = new Gson();
        String text = "";
        try {
            String yourFilePath = context.getFilesDir() + "/" + "QUESTIONS";
            File yourFile = new File(yourFilePath);
            InputStream inputStream = new FileInputStream(yourFile);
            StringBuilder stringBuilder = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                text = stringBuilder.toString();
                Questions yourObject = gson.fromJson(text, Questions.class);
                return yourObject;
            }
        } catch (FileNotFoundException e) {
            return new Questions();
        } catch (IOException e) {
            return new Questions();
        }
        return new Questions();

    }

}


 */