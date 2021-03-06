package com.example.dat153.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;

import com.example.dat153.Adapter.DatabaseAdapter;
import com.example.dat153.R;
import com.example.dat153.SharedClasses.Campus;
import com.example.dat153.SharedClasses.Question;
import com.example.dat153.SharedClasses.SharedObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DatabaseActivity extends AppCompatActivity {
    private FloatingActionButton newQuestionButton;
    private RecyclerView recyclerView;
    private DatabaseAdapter adapter;
    private SharedObject sharedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_database);

        sharedObject = (SharedObject) getApplicationContext();

        recyclerView = findViewById(R.id.personTable);
        newQuestionButton = findViewById(R.id.newQuestionButton);

        // Create recycleView-adapter and send the questions as input.
        adapter = new DatabaseAdapter(sharedObject.getQuestions());

        newQuestionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, NewQuestionActivity.class);
            startActivityForResult(intent, 1);
        });

        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Back button and finish activity on click
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // setup the cheat-button for populating the database if you're lazy. Used many many times.
        Button fixDatabase = findViewById(R.id.fixDatabase);
        fixDatabase.setOnClickListener(v -> {
            fixDatabase.setClickable(false);
            Question q1 = new Question(Campus.FØRDE, BitmapFactory.decodeResource(getResources(), R.drawable.vie_kantine));
            Question q2 = new Question(Campus.BERGEN, BitmapFactory.decodeResource(getResources(), R.drawable.bergen1));
            Question q3 = new Question(Campus.BERGEN, BitmapFactory.decodeResource(getResources(), R.drawable.bergen2));
            Question q4 = new Question(Campus.STORD, BitmapFactory.decodeResource(getResources(), R.drawable.stord1));
            Question q5 = new Question(Campus.HAUGESUND, BitmapFactory.decodeResource(getResources(), R.drawable.haugesund1));
            Question q6 = new Question(Campus.SOGNDAL, BitmapFactory.decodeResource(getResources(), R.drawable.sogndal1));

            Question q7 = new Question(Campus.HAUGESUND, BitmapFactory.decodeResource(getResources(), R.drawable.haugesund2));
            Question q8 = new Question(Campus.SOGNDAL, BitmapFactory.decodeResource(getResources(), R.drawable.sogndal2));
            Question q9 = new Question(Campus.BERGEN, BitmapFactory.decodeResource(getResources(), R.drawable.bergen3));
            Question q10 = new Question(Campus.FØRDE, BitmapFactory.decodeResource(getResources(), R.drawable.vie2));
            Question q11 = new Question(Campus.SOGNDAL, BitmapFactory.decodeResource(getResources(), R.drawable.sogndal3));


            sharedObject.addQuestion(q1);
            sharedObject.addQuestion(q2);
            sharedObject.addQuestion(q3);
            sharedObject.addQuestion(q4);
            sharedObject.addQuestion(q5);
            sharedObject.addQuestion(q6);
            sharedObject.addQuestion(q7);
            sharedObject.addQuestion(q8);
            sharedObject.addQuestion(q9);
            sharedObject.addQuestion(q10);
            sharedObject.addQuestion(q11);


            // Notify adapter that dataset has changed
            adapter.notifyDataSetChanged();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == resultCode) {
            adapter.notifyDataSetChanged();
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            sharedObject.getQuestions().remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };
}



