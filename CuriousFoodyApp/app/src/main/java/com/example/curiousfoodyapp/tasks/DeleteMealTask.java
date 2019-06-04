package com.example.curiousfoodyapp.tasks;

import android.os.AsyncTask;

import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.view.Fragment.FavoriteMealView;

public class DeleteMealTask extends AsyncTask<String, Void, Void> {

    private MealDatabase database;
    private FavoriteMealView view;

    public DeleteMealTask(MealDatabase database, FavoriteMealView view) {
        this.database = database;
        this.view = view;
    }

    @Override
    protected Void doInBackground(String... strings) {
        database.dao().deleteMeal(strings[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        view.mealRemoved();
    }
}
