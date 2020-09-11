package com.example.gadspracticeproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillIqRetrofitInstance {
    private static Retrofit skillIqRetrofit ;
    private  static  final  String BASE_URL = "https://gadsapi.herokuapp.com";

    public static  Retrofit getSkillIqRetrofitInstance(){
        if(skillIqRetrofit == null){
            skillIqRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  skillIqRetrofit;
    }
}
