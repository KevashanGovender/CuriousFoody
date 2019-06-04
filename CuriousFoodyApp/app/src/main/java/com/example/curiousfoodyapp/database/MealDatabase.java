package com.example.curiousfoodyapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.curiousfoodyapp.model.Meal;

@Database(entities = {Meal.class}, version = 1, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {

    private static MealDatabase instance;

    public abstract FavoriteMealDao dao();

    public static MealDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, MealDatabase.class,
                    "meal_db").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
