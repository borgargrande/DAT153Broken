package com.example.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat153.R;
import com.example.dat153.database.GameObject;
import com.example.dat153.utils.GameObjectViewModel;
import com.example.dat153.utils.MyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Dataset extends AppCompatActivity {

    public static final int ADD_IMAGE_REQUEST = 1;

    RecyclerView r;
    RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton goToAddImage;
    MyAdapter newAdapter;
    TextView noObjectsText;
    GameObjectViewModel viewModel;

    List<GameObject> allGameObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);

        //Init variables
        goToAddImage = findViewById(R.id.goToAddImageBtn);
        noObjectsText = findViewById(R.id.noObjectsText);


        //Init recyclerView with adapter
        r = findViewById(R.id.ImagesRecyclerView);
        newAdapter = new MyAdapter();
        r.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        r.setLayoutManager(mLayoutManager);
        r.setAdapter(newAdapter);

        //Get All objects from DB and assign to adapter
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(GameObjectViewModel.class);
        viewModel.getAllGameObjects().observe(this, gameObjects -> {
            allGameObjects = new ArrayList<>(gameObjects);
            newAdapter.submitList(gameObjects);
            if (gameObjects.size() > 0){
                noObjectsText.setVisibility(View.INVISIBLE);
            }else {
                noObjectsText.setVisibility(View.VISIBLE);
            }
        });

        //Set onclick for delete_button
        newAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                viewModel.delete(newAdapter.getGameObjectAt(position));
            }
        });

        //Set onclick for FLoatingActionButton. Go to AddImage-activity
        goToAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dataset.this, AddImage.class);
                startActivityForResult(intent, ADD_IMAGE_REQUEST);
            }
        });
    }

    //When AddImage-activity is finished.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_IMAGE_REQUEST && resultCode == RESULT_OK){
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Image not saved", Toast.LENGTH_SHORT).show();
        }
    }

/*
    public List<GameObject> getDataset(){
        return allGameObjects;
    }
*/
public int getSize(){
    return allGameObjects.size();
}

}