package com.example.dat153.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.dat153.Adapter.DatabaseAdapter;
import com.example.dat153.CustomClasses.Campus;
import com.example.dat153.CustomClasses.Question;
import com.example.dat153.CustomClasses.Questions;
import com.example.dat153.Persistent.ImageSaver;
import com.example.dat153.Persistent.ObjectSaver;
import com.example.dat153.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;


public class DatabaseActivity extends AppCompatActivity {
    private static final String TAG = "DatabaseActivity";

    private FloatingActionButton newQuestionButton;
    private RecyclerView recyclerView;
    private DatabaseAdapter adapter;
    private Questions questions;
    private ObjectSaver objectSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);





        recyclerView = findViewById(R.id.personTable);
        newQuestionButton = findViewById(R.id.newQuestionButton);

        objectSaver = new ObjectSaver(this);
        questions = objectSaver.getQuestions();
        adapter = new DatabaseAdapter(questions.getList());
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == resultCode){
            Question question = (Question) data.getSerializableExtra("addedQuestion");
            this.questions.addQuestion(question);
            this.adapter.notifyDataSetChanged();
            this.objectSaver.saveQuestions(questions);
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            questions.getList().remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
            objectSaver.saveQuestions(questions);
        }
    };
}




/*
           Questions questions = new Questions();
        Question q1 = new Question(Campus.FØRDE, null);
        Question q2 = new Question(Campus.BERGEN, null);
        Question q3 = new Question(Campus.HAUGESUND, null);
        Question q4 = new Question(Campus.SOGNDAL, null);
        Question q5 = new Question(Campus.STORD, null);
        Question q6 = new Question(Campus.HAUGESUND, null);
        Question q7 = new Question(Campus.FØRDE, null);

        questions.addQuestion(q1);
        questions.addQuestion(q2);
        questions.addQuestion(q3);
        questions.addQuestion(q4);
        questions.addQuestion(q5);
        questions.addQuestion(q6);
        questions.addQuestion(q7);

        new ObjectSaver(this).saveQuestions(questions);
*/
