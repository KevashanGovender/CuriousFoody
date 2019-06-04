package com.example.curiousfoodyapp.tasks;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.view.Fragment.DisplayFavoriteMealView;

import java.util.List;

public class GetFavoriteMealsTask extends AsyncTask<Void, Void, List<Meal>> {

    private MealDatabase database;
    private DisplayFavoriteMealView view;

    public GetFavoriteMealsTask(MealDatabase database, DisplayFavoriteMealView view) {
        this.database = database;
        this.view = view;
    }

    @Override
    protected List<Meal> doInBackground(Void... voids) {
        return database.dao().getFavorites();
    }

    @Override
    protected void onPostExecute(List<Meal> listLiveData) {
        super.onPostExecute(listLiveData);

        if(listLiveData != null){
            if(listLiveData.size() > 0){
                view.showFavorites(listLiveData);
            }
        } else {
            view.showNoFavorites();
        }
    }
}
