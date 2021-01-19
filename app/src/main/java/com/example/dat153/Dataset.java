package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dataset extends AppCompatActivity {

    Button buttonleggtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataset);

        buttonleggtil = findViewById(R.id.buttonleggtil);

        buttonleggtil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLeggTilBilde = new Intent(Dataset.this, LeggTilBilde.class);
                startActivity(goToLeggTilBilde);
            }
        });
    }
}