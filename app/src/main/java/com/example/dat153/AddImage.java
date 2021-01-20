package com.example.dat153;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dat153.Utils.GameObject;
import com.example.dat153.Utils.SharedObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddImage extends AppCompatActivity {
    EditText imageName;
    Button saveButton, addImageButton;
    ImageView addedImage;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        SharedObject sharedObject = (SharedObject) getApplicationContext();


        imageName = findViewById(R.id.editTextImageName);
        saveButton = findViewById(R.id.saveButton);
        addImageButton = findViewById(R.id.addImageButton);
        addedImage = findViewById(R.id.addedImageView);


        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

        imageName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0 && addedImage.getDrawable() != null) {
                    saveButton.setVisibility(View.VISIBLE);
                } else {
                    saveButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        saveButton.setOnClickListener(v -> {
            sharedObject.addObject(new GameObject(imageName.getText().toString(), imageBitmap));
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageBitmap = scaledBM(selectedImage);
                    addedImage.setImageBitmap(imageBitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                }


                addImageButton.setVisibility(View.INVISIBLE);
                //addedImage.setImageURI(imageUri);

                if (!(imageName.getText().toString() == "")) {
                    saveButton.setVisibility(View.VISIBLE);
                }

            }
        }
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