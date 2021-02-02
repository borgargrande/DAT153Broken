package com.example.dat153.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.dat153.DAO.QuestionDao;
import com.example.dat153.Entity.Question;
import com.example.dat153.Room.QuestionDatabase;

import java.util.List;

public class QuestionRepository {


    private QuestionDao questionDao;
    private LiveData<List<Question>> allQuestions;

    public QuestionRepository(Application application){
        QuestionDatabase database = QuestionDatabase.getInstance(application);
        questionDao = database.questionDao();
        allQuestions = questionDao.getallQuestions();
    }

    public void insert(Question question){
        Runnable runnable = () -> {
            questionDao.insert(question);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void update(Question question){
        Runnable runnable = () -> {
            questionDao.update(question);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void delete(Question question){
        Runnable runnable = () -> {
            questionDao.delete(question);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void deleteAllQuestions(){
        Runnable runnable = () -> {
            questionDao.deleteAllQuestions();
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public LiveData<List<Question>> getAllQuestions(){
        return allQuestions;
    }




}
