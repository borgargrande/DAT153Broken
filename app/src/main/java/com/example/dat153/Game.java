package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dat153.Utils.GameObject;
import com.example.dat153.Utils.SharedObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {
    SharedObject sharedObject;
    ImageView gameImage;
    EditText gameNameGuess;
    Button tryButton, resetButton;
    TextView scoreText, finalScoreText;
    int score;
    int max;
    int min;
    int position;
    int maxScore;
    Random random;

    List<GameObject> allImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        sharedObject = (SharedObject) getApplicationContext();
        gameImage = findViewById(R.id.gameImage);
        gameNameGuess = findViewById(R.id.guessEditText);
        tryButton = findViewById(R.id.guessButton);
        scoreText = findViewById(R.id.scoreTextView);

        finalScoreText = findViewById(R.id.finalScoreText);
        resetButton = findViewById(R.id.tryAgainBtn);

        allImages = new ArrayList<>(sharedObject.getAllObjectsShared());

        random = new Random();


         min = 0;
         max = allImages.size() -1;
         maxScore = allImages.size();
        score = 0;

         position = random.nextInt(max - min + 1) + min;

        gameImage.setImageBitmap(allImages.get(position).getImage());


        tryButton.setOnClickListener(v -> {
            // Check if answer is correct
            if (gameNameGuess.getText().toString().equals(allImages.get(position).getName()) ){
                Toast.makeText(Game.this, "Korrekt!!", Toast.LENGTH_SHORT).show();
                score++;
                scoreText.setText("Din score er: " + score);
            }else{
                Toast.makeText(Game.this, "Feil..", Toast.LENGTH_SHORT).show();
            }
            gameNameGuess.setText("");

            if (allImages.size() > 1){
                allImages.remove(position);
                max = allImages.size() -1;

                position = random.nextInt(max - min + 1) + min;
                gameImage.setImageBitmap(allImages.get(position).getImage());
            }else{
                finalScoreText.setText("Din score blei: " + score + " av " + maxScore);
                finalScoreText.setVisibility(View.VISIBLE);
                resetButton.setVisibility(View.VISIBLE);
                scoreText.setVisibility(View.INVISIBLE);
                tryButton.setVisibility(View.INVISIBLE);
                gameNameGuess.setVisibility(View.INVISIBLE);
                gameImage.setVisibility(View.INVISIBLE);
            }


            //gameImage.setImageBitmap(allImages.get(position).getImage());


        });

        resetButton.setOnClickListener(v -> {
            resetGame();
        });



    }

    public void resetGame(){
        allImages = new ArrayList<>(sharedObject.getAllObjectsShared());
        max = allImages.size()-1;
        position = random.nextInt(max - min + 1) + min;
        gameImage.setImageBitmap(allImages.get(position).getImage());
        score = 0;
        scoreText.setText("Din score er: " + score);
        finalScoreText.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.INVISIBLE);
        scoreText.setVisibility(View.VISIBLE);
        tryButton.setVisibility(View.VISIBLE);
        gameNameGuess.setVisibility(View.VISIBLE);
        gameNameGuess.setText("");
        gameImage.setVisibility(View.VISIBLE);
    }
}