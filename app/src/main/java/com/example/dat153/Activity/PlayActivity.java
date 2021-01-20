package com.example.dat153.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dat153.R;
import com.example.dat153.SharedClasses.Question;
import com.example.dat153.SharedClasses.SharedObject;

import java.util.Random;


public class PlayActivity extends AppCompatActivity {

   private ImageView playImage;
    private SharedObject sharedObject;
    private Question currentQuestion;
    private int score = 0;
    private TextView playScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        sharedObject = (SharedObject) getApplicationContext();

        RadioGroup radioGroup = findViewById(R.id.radioGroupPlay);
        radioGroup.clearCheck();
        playImage = findViewById(R.id.playImage);
         playScore = findViewById(R.id.playScore);

        Button backBtn = findViewById(R.id.playBackBtn);
        backBtn.setOnClickListener(v -> {
            finish();
        });

        radioGroup.setOnCheckedChangeListener(radioListener);

        currentQuestion = getRandomQuestion();
        if (currentQuestion != null) {
            playImage.setImageBitmap(currentQuestion.getImage());
            radioGroup.setVisibility(View.VISIBLE);
        } else {
            showToast("Legg til spørsmål :)");
        }

    }

    private Question getRandomQuestion() {
        Question question = null;
        if (sharedObject.getQuestions().getList().size() > 0) {
            int next = new Random().nextInt(sharedObject.getQuestions().getList().size());
            question = sharedObject.getQuestions().getList().get(next);
        }

        return question;
    }

    private void showToast(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_SHORT).show();
    }

    private RadioGroup.OnCheckedChangeListener radioListener = (group, checkedId) -> {
        RadioButton rb = (RadioButton) findViewById(checkedId);
        rb.setChecked(false);
        if (rb.getText().toString().toUpperCase().equals(currentQuestion.getCampus().toString())) {
            score++;
            playScore.setText("Score: " + score);
            playScore.setBackgroundColor(Color.GREEN);
            playScore.setTextSize(24);
        } else if (rb.getText().toString().toUpperCase().equals("42")) {
            playScore.setText("Korrekt, men ingen poeng");
            playScore.setBackgroundColor(Color.GREEN);
        } else {
            playScore.setBackgroundColor(Color.RED);
            playScore.setTextSize(24);
        }
        currentQuestion = getRandomQuestion();
        playImage.setImageBitmap(currentQuestion.getImage());
    };





}