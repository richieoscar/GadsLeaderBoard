package com.example.gadspracticeproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIqFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;

    public SkillIqFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout = getActivity().findViewById(R.id.swipe_to_refresh);

        getDataFromApi();


    }

    private void getDataFromApi() {
        SkillIqRetrofitInterface connect = SkillIqRetrofitInstance.getSkillIqRetrofitInstance().create(SkillIqRetrofitInterface.class);
        Call<List<Model>> call = connect.getSkillIqLearners();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                generateSkillIqLearners(response.body());
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getActivity(), "Turn on Network\nSwipe Down To Refresh", Toast.LENGTH_LONG).show();
                refresh();

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

    private void refresh (){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                refreshDataFromApi();

            }
        });

    }
    private void  refreshDataFromApi(){
        SkillIqRetrofitInterface connect = SkillIqRetrofitInstance.getSkillIqRetrofitInstance().create(SkillIqRetrofitInterface.class);
        Call<List<Model>> call = connect.getSkillIqLearners();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                generateSkillIqLearners(response.body());
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(getActivity(), "Turn on Network\n Swipe to Refresh", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

    }
}