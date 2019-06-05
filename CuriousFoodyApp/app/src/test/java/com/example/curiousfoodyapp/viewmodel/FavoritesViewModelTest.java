package com.example.curiousfoodyapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.curiousfoodyapp.database.FavoriteMealDao;
import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.tasks.GetFavoriteMealsTask;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

public class FavoritesViewModelTest {

    @Mock
    private Application application;
    @Mock
    private GetFavoriteMealsTask task;
    @Mock
    private MealDatabase database;
    @Mock
    private DisplayFavoriteMealView view;
    @Mock
    private LiveData<List<Meal>> mockResult;
    @Mock
    private FavoriteMealDao dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFavoritesShouldFetchFavoriteRecipes() {
        FavoritesViewModel viewModel = new FavoritesViewModel();

        viewModel.getFavoriteMeals(task);

        verify(task).execute();
    }
}