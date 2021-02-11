package com.example.dat153;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dat153.Utils.Picture;
import com.example.dat153.Utils.PictureAdapter;
import com.example.dat153.Utils.PictureViewModel;

import java.util.ArrayList;
import java.util.List;

public class DatasetActivity extends AppCompatActivity {

    Button buttonleggtil;
    RecyclerView recyclerview;

    RecyclerView.LayoutManager layoutManager;
    PictureViewModel pictureViewModel;
    PictureAdapter pictureAdapter ;

    List<Picture> allPictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);


       buttonleggtil = findViewById(R.id.buttonLeggTilBilde);

        buttonleggtil.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View view) {
               Intent goToLeggTilBilde = new Intent(DatasetActivity.this, AddPicActivity.class);
               startActivity(goToLeggTilBilde);


          }
       });





        layoutManager = new LinearLayoutManager(this);
        recyclerview = findViewById(R.id.recycler_view);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(layoutManager);

        pictureAdapter = new PictureAdapter();
        recyclerview.setAdapter(pictureAdapter);

        pictureViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PictureViewModel.class);
        pictureViewModel.getAllPictures().observe(this, new Observer<List<Picture>>() {
            @Override
            public void onChanged(@Nullable List<Picture> pictures) {
                pictureAdapter.submitList(pictures);
                allPictures = new ArrayList<Picture>(pictures);

            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                pictureViewModel.delete(pictureAdapter.getPictureAt(viewHolder.getAdapterPosition()));
                Toast.makeText(DatasetActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerview);



    }
    public int getSize() {
        return allPictures.size();
    }
/*
    @Override
    protected void onResume() {
        super.onResume();
       // myadapter.notifyDataSetChanged();
        pictureAdapter.notifyDataSetChanged();
    }

 */
}