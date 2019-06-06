package com.example.curiousfoodyapp.view.activity;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface RecipeDetailView {

    void hideProgressLoader();
    void showRecipe(Meal meal, List<String> capture, List<String> strings);
    void showError();

    void showProgressLoader();
}
