package com.example.dat153;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dat153.Utils.Picture;
import com.example.dat153.Utils.PictureViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddPicActivity extends AppCompatActivity {

    Bitmap imageBitmap;
    ImageView addedImage;
    PictureViewModel pictureViewModel;

    private String selectedImagePath;
    Button lagreButton, buttonAdd;
    EditText bildeNavn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legg_til_bilde);
        addedImage = findViewById(R.id.addedImage);
        lagreButton = findViewById(R.id.buttonLagre);
        bildeNavn = findViewById(R.id.nameEditText);

        pictureViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(PictureViewModel.class);
        lagreButton.setOnClickListener(view -> {

            saveGameObject();

        });


        buttonAdd = findViewById(R.id.buttonfinnbilde);

        buttonAdd.setOnClickListener(view -> {
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (openCameraIntent.resolveActivity(this.getPackageManager()) != null) {
                this.startActivityForResult(openCameraIntent, 1337);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1000 == requestCode) { //1000


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
            }
        }
        if (requestCode == 1337) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageBitmap = scaledBM(bitmap);
                addedImage.setImageBitmap(imageBitmap);
            }
        }
    }

    //Check if any field is empty. If not, save GameObject to DB.
    private void saveGameObject() {
        String name = bildeNavn.getText().toString();
        Bitmap image = imageBitmap;

        if (name.trim().isEmpty() || image == null) {
            Toast.makeText(this, "Please insert a name and image", Toast.LENGTH_SHORT).show();
            return;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Picture newGameObject = new Picture(name, byteArray);

        pictureViewModel.insert(newGameObject);

        Intent data = new Intent();

        setResult(RESULT_OK);
        finish();
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