package com.example.gadspracticeproject;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearnerFragment extends Fragment {

    private static final String TAG = "LearnerFragment";
    private ProgressDialog progressDialog;

    public LearnerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading");
        progressDialog.show();
        RetrofitInterface connect = LearnerRetrofitInstance.getRetrofitInstance().create(RetrofitInterface.class);
        Call<List<Model>> call = connect.getLearners();
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                progressDialog.dismiss();
                Log.d("TAG", "checkConnecting");
                generateLearners(response.body());
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void generateLearners(List<Model> body) {
        RecyclerView rvLearners = getView().findViewById(R.id.rv_learners);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LearnerRecyclerAdapter adapter = new LearnerRecyclerAdapter(body);
        rvLearners.setLayoutManager(layoutManager);
        rvLearners.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learner, container, false);
    }
}