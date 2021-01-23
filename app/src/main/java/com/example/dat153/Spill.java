package com.example.dat153;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.dat153.Utils.MyAdapter;
import com.example.dat153.Utils.SharedObject;

public class Spill extends AppCompatActivity {

    MyAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        SharedObject sharedObject = (SharedObject) getApplicationContext();

        myadapter = new MyAdapter(Spill.this, sharedObject.getAllObjectsShared());
        //myadapter.getItemCount();

        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.imageView1);
        //imageView =  findViewById(R.id.quizBilde);
        imageView.setImageResource(R.drawable.ic_testimage);

    }
}