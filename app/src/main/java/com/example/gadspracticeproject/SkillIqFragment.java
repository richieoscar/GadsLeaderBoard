package com.example.gadspracticeproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqFragment extends Fragment {

    public SkillIqFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SkillIqRetrofitInterface connect = SkillIqRetrofitInstance.getSkillIqRetrofitInstance().create(SkillIqRetrofitInterface.class);
        Call<List<Model>> call = connect.getSkillIqLearners();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                generateSkillIqLearners(response.body());
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });
    }

    private void generateSkillIqLearners(List<Model> body) {
        RecyclerView rvSkilliq = getView().findViewById(R.id.rv_skilliq);
        SkillIqRecyclerAdapter adapter = new SkillIqRecyclerAdapter(body);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvSkilliq.setLayoutManager(layoutManager);
        rvSkilliq.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_iq, container, false);
    }
}