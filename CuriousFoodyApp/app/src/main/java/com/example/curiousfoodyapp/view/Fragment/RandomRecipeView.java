package com.example.curiousfoodyapp.view.Fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface RandomRecipeView extends BaseView {
    void showRandomRecipe(List<Meal> meals);
}
