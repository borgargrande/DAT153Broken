package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button buttonspill, buttondataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");

        buttondataset = findViewById(R.id.buttonDataset);
        buttonspill = findViewById(R.id.buttonSpill);

        buttonspill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSpill = new Intent(MainActivity.this, Spill.class);
                startActivity(goToSpill);
            }
        });

        buttondataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToDataset = new Intent(MainActivity.this, Dataset.class);
                startActivity(goToDataset);
            }
        });
    }


}