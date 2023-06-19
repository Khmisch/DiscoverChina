package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.DetailsActivity;
import com.example.myapplication.fragment.HomeFragment;
import com.example.myapplication.model.Photo;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private HomeFragment context;
    private ArrayList<Photo> items;

    public HomeAdapter(HomeFragment context, ArrayList<Photo> items) {
        this.context = context;
        this.items = items;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addPhotos(ArrayList<Photo> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Photo photoItem = items.get(position);
        String photoUrl = photoItem.getUrls().getThumb();
        String photoColor = photoItem.getColor();
//        Object s1 = photoItem.getAltDescription();
        String s2 = photoItem.getDescription();
        String s3 = photoItem.getUser().getName();

        Picasso.get().load(photoUrl)
                .placeholder(new ColorDrawable(Color.parseColor(photoColor)))
                .into(holder.iv_pin);

        holder.iv_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetails(position);
            }
        });
    }

    private void callDetails(int position) {
        Intent intent = new Intent(context.getActivity(), DetailsActivity.class);
        String json = new Gson().toJson(items);
        intent.putExtra("photoList", json);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView iv_pin;
        TextView tv_description;
        ImageView iv_more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pin = itemView.findViewById(R.id.iv_pin);
            tv_description = itemView.findViewById(R.id.tv_description);
            iv_more = itemView.findViewById(R.id.iv_more);
        }
    }

    private String getDescription(Object s1, String s2, String s3) {
        if (s1 != null) {
            return s1.toString();
        } else if (s2 != null) {
            return s2;
        } else {
            return "Photo was made by " + s3;
        }
    }
}
