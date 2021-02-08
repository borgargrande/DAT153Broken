package com.example.dat153;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import  android.widget.Button;
import android.widget.Toast;

import com.example.dat153.Utils.Picture;
import com.example.dat153.Utils.PictureAdapter;
import com.example.dat153.Utils.PictureViewModel;


public class GameActivity extends AppCompatActivity {


    ImageView imageView;
    List<Picture> allImages;
    Button button;
    EditText svarTekst;
    TextView score, navn;

    PictureViewModel pictureViewModel;

    int min;
    int max;
    int korrekte;

    Random random;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);


        pictureViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PictureViewModel.class);
        pictureViewModel.getAllPictures().observe(this, (Observer<List<Picture>>) pictures -> {

            if (pictures.size() < 1){

                AlertDialog alertDialog = new AlertDialog.Builder(GameActivity.this).create();
                alertDialog.setTitle("OBS!");
                alertDialog.setMessage("Du må legge til eit bilde for å spille. Gå til databasen for å gjøre dette.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        (dialog, which) -> finish());
                alertDialog.show();
                return;
            }
            imageView = (ImageView) findViewById(R.id.imageView1);
            allImages = new ArrayList<Picture>(pictures);

            //vis et tilfeldig bilde
            random = new Random();
            min = 0;
            max = allImages.size() -1 ;
            //position = random.nextInt(max - min +1) +min;
            position = random.nextInt(max - min+1)+min;

            byte[] imageAsBytes = allImages.get(position).getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) ;
            imageView.setImageBitmap(bitmap);

            button = findViewById(R.id.answerButton);
            svarTekst = findViewById(R.id.bildeNavn);
            score = findViewById(R.id.textscore);

            //vis/oppdater score
            korrekte = 0;

            //se om svar er korrekt
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(svarTekst.getText().toString().equals(allImages.get(position).getTitle())) {

                        korrekte ++ ;
                        score.setText("Din score er :" + korrekte);


                        Toast.makeText(GameActivity.this, "korrekt", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(GameActivity.this, "feil", Toast.LENGTH_LONG).show();
                    }

                    if(allImages.size() > 1) {

                        allImages.remove(position);

                        max--;
                        position = random.nextInt(max - min + 1) + min;

                        byte[] imageAsBytes = allImages.get(position).getImage();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) ;
                        imageView.setImageBitmap(bitmap);

                    }else {
                        //ferdig med bildene
                        //vis kun score, fjern bilde. prøv igjen knapp.
                    }
                }
            });

        });



        //




    }

}