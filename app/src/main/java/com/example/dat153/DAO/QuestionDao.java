package com.example.dat153.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dat153.Entity.Question;

import java.util.List;

@Dao
public interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Question question);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Question question);

    @Delete
    void delete(Question question);

    @Query("DELETE FROM question_table")
    void deleteAllQuestions();

    @Query("SELECT * FROM question_table ORDER BY ID DESC")
    LiveData<List<Question>> getallQuestions();

    @Query("SELECT * FROM question_table WHERE id = :ID")
    Question getQuestion(int ID);


}
