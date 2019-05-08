package com.example.curiousfoodyapp.repo;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.service.MealDbService;

import java.util.List;

public class MealRepo implements IMealRepo {


    private MealDbService service;

    public MealRepo(MealDbService service) {
        this.service = service;
    }

    @Override
    public void searchByRecipe(ICallbackListener<List<Meal>> listener, String recipe) {
        service.searchByRecipe(recipe).enqueue(listener);
    }

    @Override
    public void searchById(ICallbackListener<List<Meal>> listener, long id) {
        service.searchById(id).enqueue(listener);
    }

    @Override
    public void getRandomRecipe(ICallbackListener<List<Meal>> listener) {
        service.getRandomRecipe().enqueue(listener);
    }

    @Override
    public void filterByIngredient(ICallbackListener<List<Meal>> listener, String ingredient) {
        service.filterByIngredient(ingredient).enqueue(listener);
    }

    @Override
    public void filterByType(ICallbackListener<List<Meal>> listener, String type) {
        service.filterByType(type).enqueue(listener);
    }

    @Override
    public void filterByRegion(ICallbackListener<List<Meal>> listener, String region) {
        service.filterByRegion(region).enqueue(listener);
    }
}
