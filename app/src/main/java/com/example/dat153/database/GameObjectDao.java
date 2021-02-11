package com.example.dat153.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GameObjectDao {

    @Insert
    void insert(GameObject gameObject);

    @Update
    void update(GameObject gameObject);

    @Delete
    void delete(GameObject gameObject);

    @Query("SELECT * FROM gameObject_table ORDER BY name DESC")
    LiveData<List<GameObject>> getAllGameObjects();
}
