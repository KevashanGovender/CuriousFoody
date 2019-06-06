package com.example.curiousfoodyapp.view.fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface RandomRecipeView extends BaseView {
    void showRandomRecipe(List<Meal> meals);
}
