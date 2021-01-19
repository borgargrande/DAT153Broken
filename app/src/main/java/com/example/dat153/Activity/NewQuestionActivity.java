package com.example.dat153.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dat153.CustomClasses.Camera;
import com.example.dat153.CustomClasses.Campus;
import com.example.dat153.CustomClasses.Question;
import com.example.dat153.Persistent.ImageSaver;
import com.example.dat153.Persistent.ObjectSaver;
import com.example.dat153.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class NewQuestionActivity extends AppCompatActivity {
    private Button savebtn;

    private RadioGroup radioGroup;
    private static final String TAG = "NewQuestionActivity";
    private Question question = new Question(Campus.FØRDE, null);
    private Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);
        savebtn = findViewById(R.id.newquestionSaveBtn);
        radioGroup = findViewById(R.id.selectedCampus);
        final Campus[] campus = {Campus.FØRDE};



        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (rb != null) {
                    CharSequence text = rb.getText();
                    if ("Førde".equals(text)) {
                        campus[0] = Campus.FØRDE;
                    } else if ("Sogndal".equals(text)) {
                        campus[0] = Campus.SOGNDAL;
                    } else if ("Bergen".equals(text)) {
                        campus[0] = Campus.BERGEN;
                    } else if ("Haugesund".equals(text)) {
                        campus[0] = Campus.HAUGESUND;
                    } else if ("Stord".equals(text)) {
                        campus[0] = Campus.STORD;
                    }
                }
            }
        });

        camera = new Camera(this, this);
        savebtn.setOnClickListener(v -> {
            Intent intent = new Intent();
            question.setCampus(campus[0]);
            ObjectSaver objectSaver = new ObjectSaver(this);
            objectSaver.saveQuestion(question);

            ImageSaver imageSaver = new ImageSaver(this);
            new ImageSaver(this).setFileName(question.getID()).setDirectoryName("images").save(question.getImage());
            intent.putExtra("addedQuestion", question);
            setResult(1, intent);
            finish();

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

    Uri imageUri;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1){
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                question.setImage(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }

        }



        if (resultCode == RESULT_OK && requestCode == 2){


            Bitmap bm = (Bitmap) data.getExtras().get("data");
            question.setImage(bm);


        }
    }

    public Bitmap scaledBM(Bitmap bm) {
        Bitmap scaled = Bitmap.createScaledBitmap(bm, 450, 500, true);
        return scaled;
    }
}