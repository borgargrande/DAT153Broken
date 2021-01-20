package com.example.dat153.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dat153.SharedClasses.Campus;
import com.example.dat153.SharedClasses.Question;
import com.example.dat153.R;
import com.example.dat153.SharedClasses.SharedObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class NewQuestionActivity extends AppCompatActivity {

    private static final String TAG = "NewQuestionActivity";
    private final Question question = new Question(Campus.FØRDE, null);
    private SharedObject sharedObject;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);
        Button savebtn = findViewById(R.id.newquestionSaveBtn);
        RadioGroup radioGroup = findViewById(R.id.selectedCampus);
        image = findViewById(R.id.newQuestionImage);

        sharedObject = (SharedObject) getApplicationContext();


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (rb != null) {
                    CharSequence text = rb.getText();
                    if ("Førde".contentEquals(text)) {
                        question.setCampus(Campus.FØRDE);
                    } else if ("Sogndal".contentEquals(text)) {
                        question.setCampus(Campus.SOGNDAL);
                    } else if ("Bergen".contentEquals(text)) {
                        question.setCampus(Campus.BERGEN);
                    } else if ("Haugesund".contentEquals(text)) {
                        question.setCampus(Campus.HAUGESUND);
                    } else if ("Stord".contentEquals(text)) {
                        question.setCampus(Campus.STORD);
                    }
                }
            }
        });


        savebtn.setOnClickListener(v -> {
            if (question.getImage() == null){
                showToast("Velg bilete");
            }else {
                sharedObject.getQuestions().addQuestion(question);
                setResult(1);
                finish();
            }

        });

        Button newPhoto = findViewById(R.id.newQuestionPhoto);
        newPhoto.setOnClickListener(v -> {


            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, 1);


/*
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
                this.startActivityForResult(takePictureIntent, 2);
            }
*/
        });
    }

    private void showToast(String message){
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                question.setImage(scaledBM(selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }

        }


        if (resultCode == RESULT_OK && requestCode == 2) {


            Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
            question.setImage(scaledBM(selectedImage));

        }
        image.setImageBitmap(question.getImage());
    }

    public Bitmap scaledBM(Bitmap bm) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        int maxWidth = 500;

        int maxHeight = 500;

        if (width > height) {
            // landscape
            float ratio = (float) width / maxWidth;
            width = maxWidth;
            height = (int) (height / ratio);
        } else if (height > width) {
            // portrait
            float ratio = (float) height / maxHeight;
            height = maxHeight;
            width = (int) (width / ratio);
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }


        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }
}