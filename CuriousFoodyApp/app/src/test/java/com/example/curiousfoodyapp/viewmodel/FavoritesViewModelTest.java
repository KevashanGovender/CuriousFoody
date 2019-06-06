package com.example.curiousfoodyapp.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.tasks.GetFavoriteMealsTask;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoritesViewModelTest {

    @Mock
    private GetFavoriteMealsTask task;
    @Mock
    private LiveData<List<Meal>> result;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Ignore
    @Test
    public void getFavoritesShouldFetchFavoriteRecipes() throws ExecutionException, InterruptedException {
        FavoritesViewModel viewModel = new FavoritesViewModel();

        viewModel.getFavoriteMeals(task);

        verify(task).execute();
    }
}