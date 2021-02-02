package com.example.dat153.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dat153.Entity.Campus;
import com.example.dat153.Entity.Question;
import com.example.dat153.R;
import com.example.dat153.ViewModels.QuestionViewModel;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class NewQuestionActivity extends AppCompatActivity {

    private final Question question = new Question(Campus.FØRDE);
    private ImageView image;
    private QuestionViewModel questionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);

        image = findViewById(R.id.newQuestionImage);
        questionViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QuestionViewModel.class);

        Button saveButton = findViewById(R.id.newquestionSaveBtn);
        saveButton.setOnClickListener(v -> {
            if (question.getImage() == null) {
                showToast("Velg bilete");
            } else {
                questionViewModel.insert(question);
                finish();
            }
        });


        RadioGroup radioGroup = findViewById(R.id.selectedCampus);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                RadioButton rb = findViewById(checkedId);
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



        Button newPhoto = findViewById(R.id.newQuestionPhoto);
        newPhoto.setOnClickListener(v -> {


            // Choose from camera roll
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, 1);



/*
            // Show camera and take a picture. Not in use
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
                this.startActivityForResult(takePictureIntent, 2);
            }
*/
        });
    }

    /**
     * @param message to be shown for the user in the Toast.
     */
    private void showToast(String message) {
        Toast.makeText(this, message,
                Toast.LENGTH_LONG).show();
    }

    /**
     *
     * @param requestCode The requestcode that was added to startActivityForResult
     * @param resultCode the resultcode that is set from the other activity with setResult(**)
     * @param data intent-data from the other activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handling the cameraroll
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

        // Handling the camera. Not in use
        if (resultCode == RESULT_OK && requestCode == 2) {
            Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
            question.setImage(scaledBM(selectedImage));
        }
        image.setImageBitmap(question.getImage());
    }

    /**
     * @param bm to be scaled.
     * @return scaled bitmap with width and/or height of max 500
     */
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