package com.example.curiousfoodyapp.view.Fragment;

import androidx.lifecycle.LiveData;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface DisplayFavoriteMealView extends FavoriteMealView {

    void showFavorites(LiveData<List<Meal>> meals);
    void showNoFavorites();
}
