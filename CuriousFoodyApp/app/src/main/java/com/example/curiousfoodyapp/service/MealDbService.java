package com.example.curiousfoodyapp.service;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealDbService {

    @GET("api/json/v1/1/search.php")
    Call<Recipes> searchByRecipe(@Query("s") String term);

    @GET("/lookup.php?i={id}")
    Call<List<Meal>> searchById(@Query("id") long id);

    @GET("api/json/v1/1/random.php")
    Call<Recipes> getRandomRecipe();

    @GET("api/json/v1/1/filter.php")
    Call<Recipes> filterByIngredient(@Query("i") String ingredient);

    @GET("api/json/v1/1/filter.php")
    Call<Recipes> filterByType(@Query("c") String type);

    @GET("api/json/v1/1/filter.php")
    Call<Recipes> filterByRegion(@Query("a") String region);
}
