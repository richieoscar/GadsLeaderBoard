package com.example.gadspracticeproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkillIqRecyclerAdapter extends RecyclerView.Adapter<SkillIqRecyclerAdapter.SkillIqViewHolder> {
    List<Model> SkillIqLearners;

    public SkillIqRecyclerAdapter(List<Model> skillIqLearners) {
        SkillIqLearners = skillIqLearners;
    }

    @NonNull
    @Override
    public SkillIqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_skilliq, parent, false);
        return new SkillIqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqViewHolder holder, int position) {
        final Model skillIqLearners = SkillIqLearners.get(position);
        holder.skillIqName.setText(skillIqLearners.getName());
        holder.skillIqCountry.setText(skillIqLearners.getCountry());
        holder.skillScores.setText(String.valueOf("score " +skillIqLearners.getHours()));

    }

    @Override
    public int getItemCount() {
        return SkillIqLearners.size();
    }

    public class SkillIqViewHolder extends RecyclerView.ViewHolder {
        TextView skillIqName;
        TextView skillIqCountry;
        TextView skillScores;
        ImageView skilIqBadge;
        public SkillIqViewHolder(@NonNull View itemView) {
            super(itemView);
            skillIqName = itemView.findViewById(R.id.textView_skilliq_name);
            skillIqCountry = itemView.findViewById(R.id.textView_skilliq_country);
            skillScores = itemView.findViewById(R.id.textView_skilliq_scores);
            skilIqBadge = itemView.findViewById(R.id.imageView_skilliq_badge);


        }
    }
}
