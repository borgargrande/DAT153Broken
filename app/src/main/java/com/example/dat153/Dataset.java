package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.dat153.Utils.GameObject;
import com.example.dat153.Utils.MyAdapter;
import com.example.dat153.Utils.SharedObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jonas.dat153v2.R;

import java.util.ArrayList;
import java.util.List;

public class Dataset extends AppCompatActivity {
    RecyclerView r;
    RecyclerView.LayoutManager mLayoutManager;
    List<GameObject> allImages;
    FloatingActionButton goToAddImage;
    MyAdapter newAdapter;
    TextView noObjectsText;
    SharedObject sharedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);
        r = findViewById(R.id.ImagesRecyclerView);
        goToAddImage = findViewById(R.id.goToAddImageBtn);
        sharedObject = (SharedObject) getApplicationContext();
        noObjectsText = findViewById(R.id.noObjectsText);

        if (sharedObject.getAllObjectsShared().size() > 0){
            noObjectsText.setVisibility(View.INVISIBLE);
        }



       newAdapter = new MyAdapter(Dataset.this, sharedObject.getAllObjectsShared());
        r.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        r.setLayoutManager(mLayoutManager);

        newAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                if (sharedObject.getAllObjectsShared().size() < 1){
                    noObjectsText.setVisibility(View.VISIBLE);
                }
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
        if (sharedObject.getAllObjectsShared().size() > 0){
            noObjectsText.setVisibility(View.INVISIBLE);
        }
        newAdapter.notifyDataSetChanged();
    }
}