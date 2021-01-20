package com.example.dat153.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dat153.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    List<GameObject> allGameObjects;
    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        //void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1;
        ImageView mDeleteImage, mCardImage;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mCardImage = itemView.findViewById(R.id.CardImageView);
            myText1 = itemView.findViewById(R.id.CardName);
            mDeleteImage = itemView.findViewById(R.id.deleteCard);



            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });


        }
    }

    public MyAdapter(Context ct, List<GameObject> gameObjects){
        context = ct;

        allGameObjects = gameObjects;



    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        MyViewHolder mVh = new MyViewHolder(view, mListener);

        return mVh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.myText1.setText(allGameObjects.get(position).getName());
        holder.mCardImage.setImageResource(allGameObjects.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return allGameObjects.size();

    }


}