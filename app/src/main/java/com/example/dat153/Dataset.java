package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.util.Measure;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dat153.Utils.GameObject;
import com.example.dat153.Utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class Dataset extends AppCompatActivity {

    Button buttonleggtil;
    RecyclerView recyclerview;
    List<GameObject> allImages;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);
//
//        buttonleggtil = findViewById(R.id.buttonleggtil);
//
//        buttonleggtil.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToLeggTilBilde = new Intent(Dataset.this, LeggTilBilde.class);
//                startActivity(goToLeggTilBilde);
//            }
//        });

        GameObject one = new GameObject("Bilde 1", R.drawable.ic_testimage);
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

        recyclerview = findViewById(R.id.reclerview);
        MyAdapter myadapter = new MyAdapter(Dataset.this, allImages);

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
}