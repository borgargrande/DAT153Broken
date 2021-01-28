package com.example.dat153;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dat153.Utils.SharedObject;

public class MainActivity extends AppCompatActivity {
    Button goToGame, goToDataset;
    SharedObject sharedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToGame = findViewById(R.id.goToGameButton);
        goToDataset = findViewById(R.id.goToDatabseButton);
        sharedObject = (SharedObject) getApplicationContext();


        goToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sharedObject.getAllObjectsShared().size() > 0){
                    startActivity(new Intent(MainActivity.this, Game.class));
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("OBS!");
                    alertDialog.setMessage("Du må legge til eit bilde for å spille. Gå til databasen for å gjøre dette.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }
        });

        goToDataset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Dataset.class));
            }
        });
    }
}