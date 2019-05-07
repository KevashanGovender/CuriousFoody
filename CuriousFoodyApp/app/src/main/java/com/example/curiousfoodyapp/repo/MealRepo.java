package com.example.curiousfoodyapp.repo;

import com.example.curiousfoodyapp.service.MealDbService;

import retrofit2.Call;

public class MealRepo implements IMealRepo {


    private MealDbService service;

    public MealRepo(MealDbService service) {
        this.service = service;
    }

    @Override
    public void searchByRecipe(ICallbackListener<Object> listener, String recipe) {
        service.searchByRecipe(recipe).enqueue(listener);
    }

    @Override
    public void searchById(ICallbackListener<Object> listener, long id) {
        service.searchById(id).enqueue(listener);
    }
}
