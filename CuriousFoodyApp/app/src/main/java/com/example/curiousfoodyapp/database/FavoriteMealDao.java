package com.example.curiousfoodyapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

@Dao
public interface FavoriteMealDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);

    @Query("SELECT * FROM meal_db")
    LiveData<List<Meal>> getFavorites();

    @Query("SELECT * FROM meal_db WHERE idMeal=:id ")
    Meal isMealAFavorite(String id);

    @Query("DELETE FROM meal_db WHERE idMeal=:id")
    void deleteMeal(String id);
}
