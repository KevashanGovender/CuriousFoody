package com.example.curiousfoodyapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.tasks.GetFavoriteMealsTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FavoritesViewModel extends ViewModel {

    private MutableLiveData<List<Meal>> favorites = new MutableLiveData<>();
    private MutableLiveData<List<Meal>> noFavorites = new MutableLiveData<>();

    private LiveData<List<Meal>> result = new MutableLiveData<>();

    public LiveData<List<Meal>> getFavoriteMeals(GetFavoriteMealsTask task) {
        try {
             result = task.execute().get();
        } catch (ExecutionException e) {
            Log.d("FAVORITES_VIEW_MODEL", e.getLocalizedMessage());
        } catch (InterruptedException e) {
            Log.d("FAVORITES_VIEW_MODEL", e.getLocalizedMessage());
        }
        return result;
    }

    public MutableLiveData<List<Meal>> getFavorites() {
        return favorites;
    }

    public MutableLiveData<List<Meal>> getNoFavorites() {
        return noFavorites;
    }

    public void check(List<Meal> mealList) {
        if(mealList.size() == 0){
            noFavorites.setValue(null);
        } else {
            favorites.setValue(mealList);
        }
    }
}
