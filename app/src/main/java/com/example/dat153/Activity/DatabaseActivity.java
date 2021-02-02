package com.example.dat153.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat153.Adapter.DatabaseAdapter;
import com.example.dat153.Entity.Campus;
import com.example.dat153.Entity.Question;
import com.example.dat153.R;
import com.example.dat153.ViewModels.QuestionViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DatabaseActivity extends AppCompatActivity {
    private FloatingActionButton newQuestionButton;
    private RecyclerView recyclerView;
    private DatabaseAdapter adapter;

    private QuestionViewModel questionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.personTable);
        newQuestionButton = findViewById(R.id.newQuestionButton);

        // Create and set adapter for recycleView
        adapter = new DatabaseAdapter();
        recyclerView.setAdapter(adapter);

        questionViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(QuestionViewModel.class);
        questionViewModel.getallQuestions().observe(this, questions -> {
            adapter.submitList(questions);
        });


        // Create recycleView-adapter and send the questions as input.
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
            Runnable runnable = () -> {
                Question q1 = new Question(Campus.FØRDE);
                q1.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.vie_kantine)));

                Question q2 = new Question(Campus.BERGEN);
                q2.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.bergen1)));
                Question q3 = new Question(Campus.BERGEN);
                q3.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.bergen2)));
                Question q4 = new Question(Campus.STORD);
                q4.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.stord1)));
                Question q5 = new Question(Campus.HAUGESUND);
                q5.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.haugesund1)));
                Question q6 = new Question(Campus.SOGNDAL);
                q6.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.sogndal1)));
                Question q7 = new Question(Campus.HAUGESUND);
                q7.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.haugesund2)));
                Question q8 = new Question(Campus.SOGNDAL);
                q8.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.sogndal2)));
                Question q9 = new Question(Campus.BERGEN);
                q9.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.bergen3)));
                Question q10 = new Question(Campus.FØRDE);
                q10.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.vie2)));
                Question q11 = new Question(Campus.SOGNDAL);
                q11.setImage(scaledBM(BitmapFactory.decodeResource(getResources(), R.drawable.sogndal3)));

                questionViewModel.insert(q1);
                questionViewModel.insert(q2);
                questionViewModel.insert(q3);
                questionViewModel.insert(q4);
                questionViewModel.insert(q5);
                questionViewModel.insert(q6);
                questionViewModel.insert(q7);
                questionViewModel.insert(q8);
                questionViewModel.insert(q9);
                questionViewModel.insert(q10);
                questionViewModel.insert(q11);

            };
            Thread thread = new Thread(runnable);
            thread.start();


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            questionViewModel.delete(adapter.getQuestionAt(viewHolder.getAdapterPosition()));
        }
    };

    private Bitmap scaledBM(Bitmap bm) {

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



