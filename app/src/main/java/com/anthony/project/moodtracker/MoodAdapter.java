package com.anthony.project.moodtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.UserViewHolder> {
    Context mContext;
    int[] tableauImg;


    public MoodAdapter(Context mContext,int[] tableauImg ) {
        this.mContext = mContext;

        this.tableauImg = tableauImg;
    }

    @NonNull
    @Override
    public MoodAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mood_item,parent,false);

        return  new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoodAdapter.UserViewHolder holder, int position) {
        holder.icon.setImageResource(tableauImg[position]);


    }

    @Override
    public int getItemCount() {
        return tableauImg.length;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;

        //Layout container;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.moodState);

        }
    }
}