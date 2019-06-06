package com.example.curiousfoodyapp.viewmodel;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.fragment.HomeView;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HomeViewModelTest {

    @Mock
    private IMealRepo repo;
    @Mock
    private HomeView view;
    @Mock
    private Call<Recipes> call;
    @Mock
    private Throwable throwable;
    @Captor
    private ArgumentCaptor<List<Meal>> captor;

    private HomeViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        viewModel = new HomeViewModel(repo, view);
    }

    @Test
    public void shouldGetListOfRecipes() {
        viewModel.getRecipes("chicken");

        verify(repo).filterByIngredient(viewModel, "chicken");
    }

    @Test
    public void onStartShouldShowProgressLoader() {
        viewModel.onStart();

        verify(view).showProgressView();
    }

    @Test
    public void onFailureShouldShowErrorView() {
        viewModel.onFailure(call, throwable);

        verify(view).showErrorView();
    }

    @Test
    public void onResponseShouldPassSublistToViewIfResponseIsNotNull() {
        Meal mockMeal1 = new Meal();
        Meal mockMeal2 = new Meal();
        Meal mockMeal3 = new Meal();
        Meal mockMeal4 = new Meal();

        Recipes meals = mock(Recipes.class);
        Response<Recipes> response = mock(Response.class);

        List<Meal> mealList = new ArrayList<>();
        mealList.add(mockMeal1);
        mealList.add(mockMeal2);
        mealList.add(mockMeal3);
        mealList.add(mockMeal4);

        when(meals.getMeals()).thenReturn(mealList);
        when(response.body()).thenReturn(meals);

        viewModel.onResponse(call, response);

        verify(view).setUpCarousal(captor.capture());
        assertEquals(3, captor.getValue().size());
    }
}