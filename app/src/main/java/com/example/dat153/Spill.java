package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dat153.Utils.MyAdapter;
import com.example.dat153.Utils.SharedObject;
import com.example.dat153.Utils.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import  android.widget.Button;
import android.widget.Toast;


public class Spill extends AppCompatActivity {

    SharedObject sharedObject;
    ImageView imageView;
    List<GameObject> allImages;
    Button button;
    EditText svarTekst;
    TextView score, navn;
    int min;
    int max;
    int korrekte;

    Random random;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        SharedObject sharedObject = (SharedObject) getApplicationContext();
        allImages = new ArrayList<>(sharedObject.getAllObjectsShared());
        imageView = (ImageView) findViewById(R.id.imageView1);


        //vis et tilfeldig bilde
        random = new Random();
        min = 0;
        max = allImages.size() -1 ;
        //position = random.nextInt(max - min +1) +min;
        position = random.nextInt(max - min+1)+min;


        imageView.setImageBitmap(allImages.get(position).getImage());


        button = findViewById(R.id.answerButton);
        svarTekst = findViewById(R.id.bildeNavn);
        score = findViewById(R.id.textscore);



        //vis/oppdater score
        korrekte = 0;

        //se om svar er korrekt
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(svarTekst.getText().toString().equals(allImages.get(position).getName())) {

                    korrekte ++ ;
                    score.setText("Din score er :" + korrekte);


                    Toast.makeText(Spill.this, "korrekt", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Spill.this, "feil", Toast.LENGTH_LONG).show();
                }

                if(allImages.size() > 1) {

                    allImages.remove(position);

                    max--;
                    position = random.nextInt(max - min + 1) + min;

                    imageView.setImageBitmap(allImages.get(position).getImage());
                }else {
                    //ferdig med bildene
                    //vis kun score, fjern bilde. prøv igjen knapp.
                }
            }
        });


        //




    }
}