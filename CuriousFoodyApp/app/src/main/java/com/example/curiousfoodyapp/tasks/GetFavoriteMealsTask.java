package com.example.curiousfoodyapp.tasks;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public class GetFavoriteMealsTask extends AsyncTask<Void, Void, LiveData<List<Meal>>> {

    private MealDatabase database;

    public GetFavoriteMealsTask(MealDatabase database) {
        this.database = database;
    }

    @Override
    protected LiveData<List<Meal>> doInBackground(Void... voids) {
        return database.dao().getFavorites();
    }
}
