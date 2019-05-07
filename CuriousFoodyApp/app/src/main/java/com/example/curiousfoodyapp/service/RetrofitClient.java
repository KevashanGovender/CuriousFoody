package com.example.curiousfoodyapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit INSTANCE = null;

    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1";

    public static Retrofit getClient(){
        if (INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}
