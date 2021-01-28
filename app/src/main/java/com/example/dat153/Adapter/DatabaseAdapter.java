package com.example.dat153.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat153.SharedClasses.Question;
import com.example.dat153.R;

import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

    private List<Question> questions;
    private Context context;

    public DatabaseAdapter(List<Question> questions) {

        this.questions = questions;

    }

    @NonNull
    @Override
    public DatabaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dataset_view, parent, false);
        this.context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseAdapter.ViewHolder holder, int position) {
        holder.campus.setText(questions.get(position).getCampus().toString());
        holder.imageView.setImageBitmap(questions.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView campus;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            campus = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.databaseQuestionImage);
        }


    }
}
