package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.dat153.Utils.GameObject;
import com.example.dat153.Utils.MyAdapter;
import com.example.dat153.Utils.SharedObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Dataset extends AppCompatActivity {
    RecyclerView r;
    RecyclerView.LayoutManager mLayoutManager;
    List<GameObject> allImages;
    FloatingActionButton goToAddImage;
    MyAdapter newAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);
        r = findViewById(R.id.ImagesRecyclerView);
        goToAddImage = findViewById(R.id.goToAddImageBtn);
        SharedObject sharedObject = (SharedObject) getApplicationContext();

      /*  GameObject one = new GameObject("Bilde 1", R.drawable.ic_testimage);
        GameObject two = new GameObject("Bilde 2", R.drawable.ic_testimage);
        GameObject three = new GameObject("Bilde 3", R.drawable.ic_testimage);
        GameObject four = new GameObject("Bilde 4", R.drawable.ic_testimage);
        GameObject five = new GameObject("Bilde 5", R.drawable.ic_testimage);

        allImages = new ArrayList<>();

        allImages.add(one);
        allImages.add(two);
        allImages.add(three);
        allImages.add(four);
        allImages.add(five);

        sharedObject.addObject(one);
        sharedObject.addObject(two);
        sharedObject.addObject(three);
        sharedObject.addObject(four);
        sharedObject.addObject(five);*/

       newAdapter = new MyAdapter(Dataset.this, sharedObject.getAllObjectsShared());
        r.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        r.setLayoutManager(mLayoutManager);

        newAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                sharedObject.removeObject(position);
                newAdapter.notifyItemRemoved(position);
            }
        });

        r.setAdapter(newAdapter);


        goToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dataset.this, AddImage.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        newAdapter.notifyDataSetChanged();
    }
}