package com.example.curiousfoodyapp.repo;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;

import java.util.List;

public interface IMealRepo {

    void searchByRecipe(ICallbackListener<Recipes> listener, String recipe);
    void searchById(ICallbackListener<List<Meal>> listener, long id);
    void getRandomRecipe(ICallbackListener<Recipes> listener);
    void filterByIngredient(ICallbackListener<Recipes> listener, String ingredient);
    void filterByType(ICallbackListener<Recipes> listener, String type);
    void filterByRegion(ICallbackListener<Recipes> listener, String region);
}
