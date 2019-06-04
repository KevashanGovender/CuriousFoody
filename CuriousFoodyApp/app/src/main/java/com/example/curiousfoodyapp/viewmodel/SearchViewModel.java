package com.example.curiousfoodyapp.viewmodel;

import androidx.annotation.NonNull;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.ICallbackListener;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.Fragment.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SearchViewModel implements ICallbackListener<Recipes> {

    private IMealRepo repo;
    private SearchView view;

    public SearchViewModel(IMealRepo repo, SearchView view) {
        this.repo = repo;
        this.view = view;
    }

    public void search(String searchTerm) {
        repo.searchByRecipe(this, searchTerm);
    }

    @Override
    public void onResponse(@NonNull Call<Recipes> call, @NonNull Response<Recipes> response) {
        if(response.body() != null){
            List<Meal> filteredMeals = filterResponse(response.body().getMeals());

            if(filteredMeals.size() == 0){
                view.noSearchResults();
            } else {
                view.showSearchResults(filteredMeals);
            }

        }
    }

    private List<Meal> filterResponse(List<Meal> meals) {
        List<Meal> filteredMeals = new ArrayList<>();

        for(Meal m : meals){
            if(m != null){
                filteredMeals.add(m);
            }
        }

        return filteredMeals;
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
