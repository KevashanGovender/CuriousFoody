package com.example.curiousfoodyapp.service;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealDbService {

    @GET("/search.php?s={recipe}")
    Call<List<Meal>> searchByRecipe(@Path("recipe") String term);

    @GET("/lookup.php?i={id}")
    Call<List<Meal>> searchById(@Path("id") long id);

    @GET("/random.php")
    Call<List<Meal>> getRandomRecipe();

    @GET("/filter.php?i={ingredient}")
    Call<List<Meal>> filterByIngredient(@Path("ingredient") String ingredient);

    @GET("/filter.php?c={type}")
    Call<List<Meal>> filterByType(@Path("type") String type);

    @GET("/filter.php?a={region}")
    Call<List<Meal>> filterByRegion(@Path("region") String region);
}
