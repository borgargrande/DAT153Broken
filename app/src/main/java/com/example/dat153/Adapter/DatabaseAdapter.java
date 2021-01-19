package com.example.dat153.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dat153.CustomClasses.Question;
import com.example.dat153.R;

import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

    private List<Question> persons;
    private Context context;

    public DatabaseAdapter(List<Question> persons) {

        this.persons = persons;

    }

    @NonNull
    @Override
    public DatabaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dataset_view, parent, false);
        this.context = parent.getContext();
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseAdapter.ViewHolder holder, int position) {
        holder.campus.setText(persons.get(position).getCampus().toString());

        Runnable imageLoader = () -> holder.imageView.post(() -> holder.imageView.setImageBitmap(persons.get(position).loadImage(context)));

        Thread imageThread = new Thread(imageLoader);

        imageThread.start();

    }

    @Override
    public int getItemCount() {
        return persons.size();
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
