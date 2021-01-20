package com.example.dat153.Utils;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class SharedObject extends Application {
    private List<GameObject> allObjectsShared;

    public SharedObject(){
        allObjectsShared = new ArrayList<>();
    }

    public SharedObject(List<GameObject> allObjectsShared) {
        this.allObjectsShared = allObjectsShared;
    }

    public List<GameObject> getAllObjectsShared() {
        return allObjectsShared;
    }

    public void setAllObjectsShared(List<GameObject> allObjectsShared) {
        this.allObjectsShared = allObjectsShared;
    }

    public void addObject(GameObject gameObject){
        allObjectsShared.add(gameObject);
    }

    public void removeObject(int position){
        allObjectsShared.remove(position);
    }
}
