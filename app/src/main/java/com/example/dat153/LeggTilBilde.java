package com.example.dat153;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dat153.Utils.GameObject;
import com.example.dat153.Utils.SharedObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class LeggTilBilde extends AppCompatActivity {


    Bitmap imageBitmap;
    ImageView addedImage;

    private String selectedImagePath;
    Button lagreButton;
    EditText bildeNavn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legg_til_bilde);
        addedImage = findViewById(R.id.addedImage);
        lagreButton = findViewById(R.id.buttonLagre);
        bildeNavn = findViewById(R.id.nameEditText);
        SharedObject sharedObject = (SharedObject) getApplicationContext();

        lagreButton.setOnClickListener(view -> {

            if(!(bildeNavn.getText().toString() == "")) {
                sharedObject.addObject(new GameObject(bildeNavn.getText().toString(), imageBitmap));
                finish();

            }
        });

        findViewById(R.id.buttonfinnbilde)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(openGalleryIntent, 1000);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( 1000 == requestCode) {
            if(resultCode== Activity.RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = this.getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    imageBitmap = scaledBM(selectedImage);
                    addedImage.setImageBitmap(imageBitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
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