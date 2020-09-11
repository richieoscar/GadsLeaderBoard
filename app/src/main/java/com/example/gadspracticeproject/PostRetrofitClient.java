package com.example.gadspracticeproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRetrofitClient {
    private static  Retrofit retrofit;
    private static  final String BASE_URL ="https://docs.google.com/forms/d/e/";

    public  static Retrofit getPostRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
