package com.example.dat153;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
    Button lagreButton;
    EditText bildeNavn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legg_til_bilde);
        addedImage = findViewById(R.id.addedImage);
        lagreButton = findViewById(R.id.buttonLagre);
        bildeNavn = findViewById(R.id.nameEditText);
       // Picture picture = new Picture("test3", "");

        pictureViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PictureViewModel.class);
        lagreButton.setOnClickListener(view -> {

            saveGameObject();

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
                    // TODO lagre på tlf, lagre path til db.

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Check if any field is empty. If not, save GameObject to DB.
    private void saveGameObject(){
        String name = bildeNavn.getText().toString();
        Bitmap image = imageBitmap;

        if (name.trim().isEmpty() || image == null){
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