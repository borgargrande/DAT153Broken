package com.example.dat153;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Measure;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dat153.Utils.GameObject;
import com.example.dat153.Utils.MyAdapter;
import com.example.dat153.Utils.SharedObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Dataset extends AppCompatActivity {

    //private static final int GALLERY_REQUEST = 0;
    Button buttonleggtil;
    RecyclerView recyclerview;
    List<GameObject> allImages;
    RecyclerView.LayoutManager layoutManager;
    Bitmap imageBitmap;
    MyAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);

       buttonleggtil = findViewById(R.id.buttonLeggTilBilde);

        buttonleggtil.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View view) {
               Intent goToLeggTilBilde = new Intent(Dataset.this, LeggTilBilde.class);
               startActivity(goToLeggTilBilde);

             // Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
             // photoPickerIntent.setType("image/*");
             // startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
          }
       });

        /*GameObject one = new GameObject("Bilde 1", R.drawable.ic_testimage);
        GameObject two = new GameObject("Bilde 2", R.drawable.ic_testimage);
        GameObject three = new GameObject("Bilde 3", R.drawable.ic_testimage);
        GameObject four = new GameObject("Bilde 4", R.drawable.ic_testimage);
        GameObject five = new GameObject("Bilde 5", R.drawable.ic_testimage);
        allImages = new ArrayList<>();
        allImages.add(one);
        allImages.add(two);
        allImages.add(three);
        allImages.add(four);
        allImages.add(five);*/

        SharedObject sharedObject = (SharedObject) getApplicationContext();

        recyclerview = findViewById(R.id.reclerview);
        myadapter = new MyAdapter(Dataset.this, sharedObject.getAllObjectsShared());

        myadapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                allImages.remove(position);
                myadapter.notifyItemRemoved(position);
            }
        });

        layoutManager = new LinearLayoutManager(this);
        recyclerview.setHasFixedSize(true);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(myadapter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        myadapter.notifyDataSetChanged();
    }
}