package com.example.curiousfoodyapp.viewmodel;

import androidx.annotation.NonNull;

import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.ICallbackListener;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.Fragment.RandomRecipeView;

import retrofit2.Call;
import retrofit2.Response;

public class RandomRecipeViewModel implements ICallbackListener<Recipes> {


    private IMealRepo repo;
    private RandomRecipeView view;

    public RandomRecipeViewModel(IMealRepo repo, RandomRecipeView view) {

        this.repo = repo;
        this.view = view;
    }

    public void getRandomRecipe() {
        repo.getRandomRecipe(this);
    }

    @Override
    public void onResponse(@NonNull Call<Recipes> call, @NonNull Response<Recipes> response) {
        view.showRandomRecipe(response.body().getMeals());
    }

    @Override
    public void onFailure(@NonNull Call<Recipes> call, @NonNull Throwable t) {
        view.showErrorView();
    }

    @Override
    public void onStart() {
        view.showProgressView();
    }
}
