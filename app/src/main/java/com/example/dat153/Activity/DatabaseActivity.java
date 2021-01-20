package com.example.dat153.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dat153.Adapter.DatabaseAdapter;
import com.example.dat153.R;
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

        adapter = new DatabaseAdapter(sharedObject.getQuestions().getList());
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
            adapter.notifyDataSetChanged();
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            sharedObject.getQuestions().getList().remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };
}



