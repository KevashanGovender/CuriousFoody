package com.example.curiousfoodyapp.view.Fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface RandomRecipeView {
    void showProgressView();
    void showRandomRecipe(List<Meal> meals);
    void hideProgressView();

    void showErrorView();
}
