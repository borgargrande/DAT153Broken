package com.example.dat153.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dat153.R;
import com.example.dat153.SharedClasses.Question;
import com.example.dat153.SharedClasses.SharedObject;

import java.util.ArrayList;
import java.util.Random;


public class PlayActivity extends AppCompatActivity {

    private ImageView playImage;
    private SharedObject sharedObject;
    private Question currentQuestion;
    private int score = 0;
    private TextView playScore;
    private ArrayList<Question> currentQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Get sharedObject.
        sharedObject = (SharedObject) getApplicationContext();

        // Get and configure radiogroup.
        RadioGroup radioGroup = findViewById(R.id.radioGroupPlay);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(radioListener);

        // Get image- and playScore-View.
        playImage = findViewById(R.id.playImage);
        playScore = findViewById(R.id.playScore);

        // Get back-button and configure onclick to end current activity.
        Button backBtn = findViewById(R.id.playBackBtn);
        backBtn.setOnClickListener(v -> {
            finish();
        });
        startQuiz();

    }

    /**
     * Start the quiz with a random question.
     */
    private void startQuiz() {
        // Get random question and set bitmap
        currentQuestion = getRandomQuestion();
        playScore.setBackgroundColor(Color.WHITE);
        score = 0;
        playScore.setText("Score: " + score);
        if (currentQuestion != null) {
            playImage.setImageBitmap(currentQuestion.getImage());
        }
    }

    /**
     * @return a random question that is left in the current quiz.
     */
    private Question getRandomQuestion() {
        if (currentQuiz == null) {
            currentQuiz = new ArrayList<>(sharedObject.getQuestions());
        }
        Question question = null;
        if (currentQuiz.size() > 1) {
            Random random = new Random();
            int nextQuestion = random.nextInt(currentQuiz.size() - 1);
            question = currentQuiz.get(nextQuestion);
            currentQuiz.remove(nextQuestion);
        } else if (currentQuiz.size() == 1) {
            question = currentQuiz.get(0);
            currentQuiz.remove(0);
        } else {
            this.currentQuiz = null;
            showAlertQuizIsDone();
        }

        return question;
    }


    /**
     * RadioGroup.OnCheckedChangeListener for handeling answers in the quiz.
     * If will also handle the flow in the quiz. This could be done in another method.
     */
    @SuppressLint("SetTextI18n")
    private final RadioGroup.OnCheckedChangeListener radioListener = (group, checkedId) -> {
        RadioButton rb = findViewById(checkedId);
        rb.setChecked(false);
        if (currentQuestion != null){
            if (rb.getText().toString().toUpperCase().equals(currentQuestion.getCampus().toString())) {
                score++;
                playScore.setText("Score: " + score);
                playScore.setBackgroundColor(Color.GREEN);
            } else if (rb.getText().toString().toUpperCase().equals("42")) {
                playScore.setText("Korrekt, men ingen poeng");
                playScore.setBackgroundColor(Color.GREEN);
            } else {
                playScore.setBackgroundColor(Color.RED);
            }
            currentQuestion = getRandomQuestion();
            if (currentQuestion != null) {
                playImage.setImageBitmap(currentQuestion.getImage());
            }
        }

    };

    /**
     * Method for alerting the user that the quiz is finished.
     */
    private void showAlertQuizIsDone() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.quizIsDone)
                .setMessage("Din score: " + score + " av " + sharedObject.getQuestions().size())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton("Reset", (dialog, which) -> startQuiz())
                .setNegativeButton("OK", (dialog, which) -> finish())
                .show();
    }
}