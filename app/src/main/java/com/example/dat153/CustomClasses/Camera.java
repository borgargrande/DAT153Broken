package com.example.dat153.CustomClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.TextView;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera {

    private final int REQUEST_TAKE_PHOTO = 1;

    private Context context;
    private Activity activity;

    public Camera(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;

    }

    // Creates a file in phone storage to save images *This method does not save the images, ONLY creates a file for them to be saved in
    // Saves file in sdcard/Android/com.dat153.oblig1/files/Pictures
    public File createImageFile(String path) throws IOException {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES); // /storage/emulated/0/Android/data/com.dat153.oblig1/files/Pictures
        File imageFile = File.createTempFile(path, ".jpg", storageDir); // Create file with random name
        return imageFile;
    }

    // Creates a unique filename


    // takes picture
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }

    }





}
