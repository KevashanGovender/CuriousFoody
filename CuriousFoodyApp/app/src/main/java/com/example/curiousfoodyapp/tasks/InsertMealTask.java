package com.example.curiousfoodyapp.tasks;

import android.os.AsyncTask;

import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.view.Fragment.FavoriteMealView;

public class InsertMealTask extends AsyncTask<Meal, Void, Void> {

    private MealDatabase database;
    private FavoriteMealView view;

    public InsertMealTask(MealDatabase database, FavoriteMealView view) {
        this.database = database;
        this.view = view;
    }

    @Override
    protected Void doInBackground(Meal... meals) {
        database.dao().insertMeal(meals[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        view.mealAdded();
    }
}
