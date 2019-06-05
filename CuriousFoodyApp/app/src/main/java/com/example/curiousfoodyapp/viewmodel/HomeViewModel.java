package com.example.curiousfoodyapp.viewmodel;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.ICallbackListener;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.Fragment.HomeView;

import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Response;

public class HomeViewModel implements ICallbackListener<Recipes> {

    private IMealRepo repo;
    private HomeView view;

    public HomeViewModel(IMealRepo repo, HomeView view) {
        this.repo = repo;
        this.view = view;
    }

    public void getRecipes(String ingredient) {
        repo.filterByIngredient(this, ingredient);
    }

    @Override
    public void onResponse(@NonNull Call<Recipes> call, @NonNull Response<Recipes> response) {
        view.setUpCarousal(filterList(response.body().getMeals()));
    }

    @Override
    public void onFailure(@NonNull Call<Recipes> call, @NonNull Throwable t) {
        view.showErrorView();
    }

    @Override
    public void onStart() {
        view.showProgressView();
    }

    private List<Meal> filterList(List<Meal> meals) {
        return meals.subList(0, 3);
    }
}
