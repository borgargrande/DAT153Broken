package com.example.dat153.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dat153.Entity.Question;
import com.example.dat153.R;
import com.example.dat153.ViewModels.QuestionViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PlayActivity extends AppCompatActivity {

    private ImageView playImage;
    private Question currentQuestion;
    private int score = 0;
    private TextView playScore;
    private List<Question> currentQuiz;
    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Get sharedObject.
        questionViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QuestionViewModel.class);


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

        setupQuiz();

    }

    /**
     * Setup the quiz with a random question.
     */
    private void setupQuiz(){
        questionViewModel.getallQuestions().observe(this, questions -> {
            setupQuiz(questions);
        });
    }

    public void setupQuiz(List<Question> questions){
        currentQuiz = new ArrayList<>(questions);
        Collections.shuffle(currentQuiz);
        if (currentQuiz.size() > 0){
            currentQuestion = currentQuiz.get(0);
            playScore.setBackgroundColor(Color.WHITE);
            score = 0;
            playScore.setText("Score: " + score);
            if (currentQuestion != null) {
                playImage.setImageBitmap(currentQuestion.getImage());
            }
            currentQuiz.remove(0);
        }

    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public ImageView getPlayImage() {
        return playImage;
    }

    public List<Question> getCurrentQuiz() {
        return currentQuiz;
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * @return a random question that is left in the current quiz.
     */
    private Question getRandomQuestion() {

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
        if (currentQuestion != null) {
            if (rb.getText().toString().toUpperCase().equals(currentQuestion.getCampus().toString())) {
                score++;
                playScore.setText("Score: " + score);
                playScore.setBackgroundColor(Color.GREEN);
            } else if (rb.getText().toString().toUpperCase().equals("42")) {
                playScore.setText("Korrekt, men ingen poeng");
                playScore.setBackgroundColor(Color.GREEN);
            } else {
                playScore.setBackgroundColor(Color.RED);
                Toast.makeText(this, "Rett svar er " + currentQuestion.getCampus().toString(), Toast.LENGTH_SHORT).show();
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
                .setMessage("Din score: " + score + " av " + questionViewModel.getallQuestions().getValue().size())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNeutralButton("Reset", (dialog, which) -> setupQuiz())
                .setNegativeButton("OK", (dialog, which) -> finish())
                .show();
    }

    public int getScore() {
        return score;
    }
}