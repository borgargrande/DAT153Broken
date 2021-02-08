package com.example.dat153.Utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PictureViewModel extends AndroidViewModel {
    private PictureRepository repository;
    private LiveData<List<Picture>> allPictures;


    public PictureViewModel(@NonNull Application application) {
        super(application);
        repository = new PictureRepository(application);
        allPictures = repository.getAllPictures();
    }

    public void insert(Picture picture) {
        repository.insert(picture);
    }

    public void update(Picture picture) {
        repository.update(picture);
    }

    public void delete(Picture picture) {
        repository.delete(picture);
    }

    public LiveData<List<Picture>> getAllPictures() {
        return allPictures;
    }
}
