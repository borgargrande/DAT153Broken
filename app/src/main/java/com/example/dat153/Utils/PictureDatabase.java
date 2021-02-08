package com.example.dat153.Utils;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Picture.class}, version = 2)
public abstract class PictureDatabase extends RoomDatabase {
    private static PictureDatabase instance;

    public abstract PictureDao pictureDao();

    public static synchronized PictureDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PictureDatabase.class, "picture_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }



}
