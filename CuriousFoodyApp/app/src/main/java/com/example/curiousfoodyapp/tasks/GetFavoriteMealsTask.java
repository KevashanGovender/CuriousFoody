package com.example.curiousfoodyapp.tasks;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.view.Fragment.DisplayFavoriteMealView;

import java.util.List;

public class GetFavoriteMealsTask extends AsyncTask<Void, Void, LiveData<List<Meal>>> {

    private MealDatabase database;
    private DisplayFavoriteMealView view;

    public GetFavoriteMealsTask(MealDatabase database, DisplayFavoriteMealView view) {
        this.database = database;
        this.view = view;
    }

    @Override
    protected LiveData<List<Meal>> doInBackground(Void... voids) {
        return database.dao().getFavorites();
    }

    @Override
    protected void onPostExecute(LiveData<List<Meal>> listLiveData) {
        super.onPostExecute(listLiveData);

        if(listLiveData.getValue() != null){
            if(listLiveData.getValue().size() > 0){
                view.showFavorites(listLiveData);
            }
        } else {
            view.showNoFavorites();
        }
    }
}
