package com.example.dat153.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.dat153.R;
import com.example.dat153.database.GameObject;
import com.example.dat153.utils.GameObjectViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button goToGame, goToDataset;

    private GameObjectViewModel viewModel;
    List<GameObject> allObjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init variables
        goToGame = findViewById(R.id.goToGameButton);
        goToDataset = findViewById(R.id.goToDatabseButton);

        //Retrieve viewmodel and all gameObjects.
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(GameObjectViewModel.class);
        viewModel.getAllGameObjects().observe(this, new Observer<List<GameObject>>() {
            @Override
            public void onChanged(List<GameObject> gameObjects) {
                allObjects = new ArrayList<>(gameObjects);
            }
        });

        //Set onclick on goToGame. Sends user to Game-activity
        goToGame.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Game.class)));

        //Set onClick on Dataset. Sends user to Dataset-activity
        goToDataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Dataset.class));
            }
        });
    }
}