package com.example.dat153.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dat153.R;
import com.example.dat153.ViewModels.QuestionViewModel;

public class MainActivity extends AppCompatActivity {
    private QuestionViewModel questionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Collect button for navigation to PlayActivity, and add onClickListener to start activity
        Button btnplay = findViewById(R.id.btnplay);
        btnplay.setOnClickListener(v -> startActivity(new Intent(this, PlayActivity.class)));

        // Collect button for navigation to DatabaseActivity, and add onClickListener to start activity.
        Button btnDatabase = findViewById(R.id.btnDatabase);
        btnDatabase.setOnClickListener(v -> startActivity(new Intent(this, DatabaseActivity.class)));

        // Get ViewModel
        questionViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QuestionViewModel.class);
        questionViewModel.getallQuestions().observe(this, questions -> {
            if (questions.size() > 0){
                btnplay.setVisibility(View.VISIBLE);
            }else {
                btnplay.setVisibility(View.GONE);
            }
        });
    }

}