package com.example.gadspracticeproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LearnerRecyclerAdapter extends RecyclerView.Adapter<LearnerRecyclerAdapter.LearnerViewHolder> {
    List<Model> learners;

    public LearnerRecyclerAdapter(List<Model> learners) {

        this.learners = learners;
    }

    @NonNull
    @Override
    public LearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       View view =  layoutInflater.inflate(R.layout.item_leaders, parent, false);

        return new LearnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerViewHolder holder, int position) {
        final Model learner = learners.get(position);
        holder.name.setText(learner.getName());
        holder.country.setText(learner.getCountry());
        holder.hour.setText(String.valueOf(learner.getHours() +" hours"));


    }

    @Override
    public int getItemCount()
    {
        return learners.size();
    }

    public class LearnerViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView country;
        TextView hour;
        ImageView badge;

        public LearnerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_name);
            country = itemView.findViewById(R.id.textView_country);
            hour = itemView.findViewById(R.id.textView_hour);
            badge = itemView.findViewById(R.id.imageView_badge);
        }
    }
}
