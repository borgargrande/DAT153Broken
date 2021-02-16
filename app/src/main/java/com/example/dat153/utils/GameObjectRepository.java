package com.example.dat153.utils;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.dat153.database.GameObject;
import com.example.dat153.database.GameObjectDao;
import com.example.dat153.database.GameObjectDatabase;

import java.util.List;

public class GameObjectRepository {
    private GameObjectDao gameObjectDao;
    private LiveData<List<GameObject>> allGameObjects;



    public GameObjectRepository(Application application){
        GameObjectDatabase database = GameObjectDatabase.getInstance(application);
        gameObjectDao = database.gameObjectDao();
        allGameObjects = gameObjectDao.getAllGameObjects();

    }

    //Initialize every database-action with seperate threads
    public void insert(GameObject gameObject){
        Runnable runnable = () -> {
          gameObjectDao.insert(gameObject);
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    public void delete(GameObject gameObject){
        Runnable runnable = () -> {
            gameObjectDao.delete(gameObject);
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    public LiveData<List<GameObject>> getAllGameObjects(){
        return allGameObjects;
    }


}
