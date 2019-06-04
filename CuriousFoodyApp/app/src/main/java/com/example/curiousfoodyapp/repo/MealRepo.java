package com.example.curiousfoodyapp.repo;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.service.MealDbService;

import java.util.List;

public class MealRepo implements IMealRepo {


    private MealDbService service;

    public MealRepo(MealDbService service) {
        this.service = service;
    }

    @Override
    public void searchByRecipe(ICallbackListener<Recipes> listener, String recipe) {
        listener.onStart();
        service.searchByRecipe(recipe).enqueue(listener);
    }

    @Override
    public void searchById(ICallbackListener<List<Meal>> listener, long id) {
        listener.onStart();
        service.searchById(id).enqueue(listener);
    }

    @Override
    public void getRandomRecipe(ICallbackListener<Recipes> listener) {
        listener.onStart();
        service.getRandomRecipe().enqueue(listener);
    }

    @Override
    public void filterByIngredient(ICallbackListener<Recipes> listener, String ingredient) {
        listener.onStart();
        service.filterByIngredient(ingredient).enqueue(listener);
    }

    @Override
    public void filterByType(ICallbackListener<List<Meal>> listener, String type) {
        listener.onStart();
        service.filterByType(type).enqueue(listener);
    }

    @Override
    public void filterByRegion(ICallbackListener<List<Meal>> listener, String region) {
        listener.onStart();
        service.filterByRegion(region).enqueue(listener);
    }
}
