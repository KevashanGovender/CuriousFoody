package com.example.curiousfoodyapp.view.Activity;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface RecipeDetailView {

    void hideProgressLoader();
    void showRecipe(Meal meal, List<String> capture, List<String> strings);
    void showError();

    void showProgressLoader();
}
