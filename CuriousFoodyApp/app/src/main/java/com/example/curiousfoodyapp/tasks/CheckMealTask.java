package com.example.curiousfoodyapp.tasks;

import android.os.AsyncTask;

import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;

public class CheckMealTask extends AsyncTask<String, Void, Meal> {

    private MealDatabase database;

    public CheckMealTask(MealDatabase database) {
        this.database = database;
    }

    @Override
    protected Meal doInBackground(String... ids) {
        return database.dao().isMealAFavorite(ids[0]);
    }
}
