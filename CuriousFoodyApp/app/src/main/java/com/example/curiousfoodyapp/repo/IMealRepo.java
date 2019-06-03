package com.example.curiousfoodyapp.repo;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;

import java.util.List;

public interface IMealRepo {

    void searchByRecipe(ICallbackListener<Recipes> listener, String recipe);
    void searchById(ICallbackListener<List<Meal>> listener, long id);
    void getRandomRecipe(ICallbackListener<List<Meal>> listener);
    void filterByIngredient(ICallbackListener<Recipes> listener, String ingredient);
    void filterByType(ICallbackListener<List<Meal>> listener, String type);
    void filterByRegion(ICallbackListener<List<Meal>> listener, String region);
}
