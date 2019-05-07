package com.example.curiousfoodyapp.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealDbService {

    @GET("/search.php?s={recipe}")
    Call<Object> searchByRecipe(@Path("recipe") String term);

    @GET("/lookup.php?i={id}")
    Call<Object> searchById(@Path("id") long id);

    @GET("/random.php")
    Call<Object> getRandomRecipe();

    @GET("/filter.php?i={ingredient}")
    Call<Object> filterByIngredient(@Path("ingredient") String ingredient);

    @GET("/filter.php?c={type}")
    Call<Object> filterByType(@Path("type") String type);

    @GET("/filter.php?a={region}")
    Call<Object> filterByRegion(@Path("region") String region);
}
