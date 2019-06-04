package com.example.curiousfoodyapp.viewmodel;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.Fragment.RandomRecipeView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RandomRecipeViewModelTest {

    @Mock
    private IMealRepo repo;
    @Mock
    private RandomRecipeView view;
    @Mock
    private Call<Recipes> mockCall;
    @Captor
    private ArgumentCaptor<List<Meal>> captor;

    private RandomRecipeViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        viewModel = new RandomRecipeViewModel(repo, view);
    }

    @Test
    public void shouldFetchRandomRecipeAndPassItToTheView() {
        viewModel.getRandomRecipe();

        verify(repo).getRandomRecipe(viewModel);
    }

    @Test
    public void onStartShouldShowProgressView() {
        viewModel.onStart();

        verify(view).showProgressView();
    }

    @Test
    public void onResponseShouldPassRecipesToTheViewAndHideTheProgressView() {
        Meal mockMeal1 = new Meal();

        Recipes meals = mock(Recipes.class);
        Response<Recipes> response = mock(Response.class);

        List<Meal> mealList = new ArrayList<>();
        mealList.add(mockMeal1);


        when(meals.getMeals()).thenReturn(mealList);
        when(response.body()).thenReturn(meals);

        viewModel.onResponse(mockCall, response);

        verify(view).showRandomRecipe(captor.capture());
        verify(view).hideProgressView();
        assertEquals(1, captor.getValue().size());
    }

    @Test
    public void onErrorShouldShowErrorViewAndHideProgressView() {
        Throwable t = mock(Throwable.class);

        viewModel.onFailure(mockCall, t);

        verify(view).showErrorView();
    }
}