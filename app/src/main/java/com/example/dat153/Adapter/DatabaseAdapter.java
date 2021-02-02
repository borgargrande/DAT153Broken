package com.example.dat153.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat153.Entity.Question;
import com.example.dat153.R;

public class DatabaseAdapter extends ListAdapter<Question, DatabaseAdapter.ViewHolder> {

    public DatabaseAdapter() {
        super(Diff_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Question> Diff_CALLBACK = new DiffUtil.ItemCallback<Question>() {
        @Override
        public boolean areItemsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
            return oldItem.getID() == newItem.getID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
            return oldItem.getCampus().equals(newItem.getCampus());
        }
    };

    public Question getQuestionAt(int position) {
        return getItem(position);
    }

    @NonNull
    @Override
    public DatabaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dataset_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseAdapter.ViewHolder holder, int position) {
        Question currentQuestion = getItem(position);
        holder.campus.setText(currentQuestion.getCampus().toString());
        holder.imageView.setImageBitmap(currentQuestion.getImage());
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
