package com.example.dat153.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.dat153.R;
import com.example.dat153.SharedClasses.SharedObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get SharedObject
        SharedObject sharedObject = (SharedObject) getApplicationContext();

        // Collect button for navigation to DatabaseActivity, and add onClickListener to start activity.
        Button btnDatabase = findViewById(R.id.btnDatabase);
        btnDatabase.setOnClickListener(v -> startActivity(new Intent(this, DatabaseActivity.class)));

        // Collect button for navigation to PlayActivity, and add onClickListener to start activity
        Button btnplay = findViewById(R.id.btnplay);
        btnplay.setOnClickListener(v -> {

            // If there is no questions in the quiz, alert the user. If there is any questions, navigate to the PlayActivity
            if (sharedObject.getQuestions().size() < 1) {
                showAlertForEmptyQuestions();
            } else {
                startActivity(new Intent(this, PlayActivity.class));
            }
        });

    }

    /**
     * Method for alerting the user for missing questions in the quiz.
     */
    private void showAlertForEmptyQuestions() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.EmptyQuiz)
                .setMessage(R.string.EmptyQuizMsg)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton("OK", null)
                .show();
    }


}