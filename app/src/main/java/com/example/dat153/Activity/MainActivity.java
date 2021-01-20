package com.example.dat153.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.dat153.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDatabase = findViewById(R.id.btnDatabase);
        btnDatabase.setOnClickListener(v -> {
            startActivity(new Intent(this, DatabaseActivity.class));
        });


        Button btnplay = findViewById(R.id.btnplay);
        btnplay.setOnClickListener(v -> {
            startActivity(new Intent(this, PlayActivity.class));
        });

    }



}