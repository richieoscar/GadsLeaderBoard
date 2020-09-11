package com.example.gadspracticeproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillIqRetrofitInterface {
    @GET("/api/skilliq")
    Call<List<Model>> getSkillIqLearners();
}
