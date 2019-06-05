package com.example.curiousfoodyapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.curiousfoodyapp.tasks.GetFavoriteMealsTask;

public class FavoritesViewModel extends ViewModel {

    public void getFavoriteMeals(GetFavoriteMealsTask task) {
        task.execute();
    }
}