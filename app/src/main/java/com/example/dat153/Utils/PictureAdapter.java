package com.example.dat153.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat153.R;

import java.util.ArrayList;
import java.util.List;

public class PictureAdapter extends ListAdapter<Picture, PictureAdapter.PictureHolder> {

    public class PictureHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private ImageView imageView;

        public PictureHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            imageView = itemView.findViewById(R.id.CardImageView);

        }
    }

    public PictureAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Picture> DIFF_CALLBACK = new DiffUtil.ItemCallback<Picture>() {
        @Override
        public boolean areItemsTheSame(@NonNull Picture oldItem, @NonNull Picture newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Picture oldItem, @NonNull Picture newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };


    public PictureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.picture_item, parent, false);

        return new PictureHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.PictureHolder holder, int position) {
        holder.textViewTitle.setText(getItem(position).getTitle());
        byte[] imageAsBytes = getItem(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length) ;
        holder.imageView.setImageBitmap(bitmap);


    }

    public Picture getPictureAt(int position) {
        return getItem(position);
    }



}
