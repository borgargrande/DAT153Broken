package com.example.dat153.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dat153.DAO.QuestionDao;
import com.example.dat153.Entity.Question;
import com.example.dat153.Repository.QuestionRepository;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel implements QuestionDao {

    private QuestionRepository repository;
    private LiveData<List<Question>> allQuestions;

    public QuestionViewModel(@NonNull Application application) {
        super(application);
        repository = new QuestionRepository(application);
        allQuestions = repository.getAllQuestions();
    }

    @Override
    public void insert(Question question) {
        repository.insert(question);
    }

    @Override
    public void update(Question question) {
        repository.update(question);
    }

    @Override
    public void delete(Question question) {
        repository.delete(question);
    }

    @Override
    public void deleteAllQuestions() {
        repository.deleteAllQuestions();
    }

    @Override
    public LiveData<List<Question>> getallQuestions() {
        return repository.getAllQuestions();
    }

}
