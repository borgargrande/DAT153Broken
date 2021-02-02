package com.example.dat153.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.dat153.DAO.QuestionDao;
import com.example.dat153.Entity.Question;
import com.example.dat153.TypeConverters.QuestionsTypeConverter;

@Database(entities = {Question.class}, version = 2)
@TypeConverters({QuestionsTypeConverter.class})
public abstract class QuestionDatabase extends RoomDatabase {


    private static QuestionDatabase instance;

    public abstract QuestionDao questionDao();

    public static synchronized QuestionDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), QuestionDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
