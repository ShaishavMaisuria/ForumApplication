package com.example.forumapplication;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ForumRecylerAdapter extends RecyclerView.Adapter<ForumRecylerAdapter.forumViewHolder> {

    ArrayList<DataServices.Forum> forumArrayList;

    public  ForumRecylerAdapter (ArrayList<DataServices.Forum> forum){
        this.forumArrayList=forum;
    }

    @NonNull
    @Override
    public forumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull forumViewHolder holder, int position) {
View view = Layout


    }

    @Override
    public int getItemCount() {
        return this.forumArrayList.size();
    }

    public static class forumViewHolder extends RecyclerView.ViewHolder{
        public forumViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
