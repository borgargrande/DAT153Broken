package com.example.dat153.Utils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PictureRepository {

    private PictureDao pictureDao;
    private LiveData<List<Picture>> allPictures;

    public PictureRepository(Application application) {
        PictureDatabase database = PictureDatabase.getInstance(application);
        pictureDao = database.pictureDao();
        allPictures = pictureDao.getAllPictures();
    }

    public void insert(Picture picture) {
        Runnable run = () -> {
            pictureDao.insert(picture);
        };
        Thread thread = new Thread(run);
        thread.start();


    }

    public void update(Picture picture) {
        Runnable run = () -> {
            pictureDao.update(picture);
        };
        Thread thread = new Thread(run);
        thread.start();

    }

    public void delete(Picture picture) {
        Runnable run = () -> {
            pictureDao.delete(picture);
        };
        Thread thread = new Thread(run);
        thread.start();


    }


    public LiveData<List<Picture>> getAllPictures() {
        return allPictures;
    }


}

