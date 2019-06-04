package com.example.curiousfoodyapp.viewmodel;

import android.content.Intent;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.Activity.RecipeDetailView;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeDetailsViewModelTest {

    @Mock
    private IMealRepo repo;
    @Mock
    private RecipeDetailView view;
    @Mock
    private Call<Recipes> mockCall;

    @Captor
    private ArgumentCaptor<List<String>> ingredientNameCaptor;
    @Captor
    private ArgumentCaptor<List<String>> ingredientQtyCaptor;

    private RecipeDetailsViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new RecipeDetailsViewModel(repo, view);
    }

    @Test
    public void shouldGetIntentFromViewAndCheckIfNotNullThenCheckIfRecipeNameIsPopulatedAndRecipeNameIsNotEmptyAndCallRepo() {
        Intent intent = mock(Intent.class);
        String recipeName = "Bucatini all'amatriciana";
        when(intent.getStringExtra("RecipeName")).thenReturn(recipeName);

        viewModel.checkIntent(intent);

        verify(intent, times(3)).getStringExtra("RecipeName");
        verify(repo).searchByRecipe(viewModel, recipeName);
    }

    @Ignore
    @Test
    public void onResponseShouldGetMealFromResponseAndPassItToTheView() {
        Response<Recipes> mockResponse = mock(Response.class);

        Recipes recipes = mock(Recipes.class);

        Meal meal = new Meal();
        meal.setStrIngredient1("Hello");
        meal.setStrIngredient2("Hello");
        meal.setStrIngredient3("Hello");
        meal.setStrIngredient4("Hello");
        meal.setStrIngredient5("Hello");
        meal.setStrIngredient6("Hello");
        meal.setStrIngredient7("Hello");
        meal.setStrIngredient8("Hello");
        meal.setStrIngredient9("Hello");
        meal.setStrIngredient10("Hello");
        meal.setStrIngredient11("");
        meal.setStrIngredient12("");
        meal.setStrIngredient13("");
        meal.setStrIngredient14("");
        meal.setStrIngredient15("");
        meal.setStrIngredient16("");
        meal.setStrIngredient17("");
        meal.setStrIngredient18("");
        meal.setStrIngredient19("");
        meal.setStrIngredient20("");

        meal.setStrMeasure1("Hello");
        meal.setStrMeasure2("Hello");
        meal.setStrMeasure3("Hello");
        meal.setStrMeasure4("Hello");
        meal.setStrMeasure5("Hello");
        meal.setStrMeasure6("Hello");
        meal.setStrMeasure7("Hello");
        meal.setStrMeasure8("Hello");
        meal.setStrMeasure9("Hello");
        meal.setStrMeasure10("Hello");
        meal.setStrMeasure11("");
        meal.setStrMeasure12("");
        meal.setStrMeasure13("");
        meal.setStrMeasure14("");
        meal.setStrMeasure15("");
        meal.setStrMeasure16("");
        meal.setStrMeasure17("");
        meal.setStrMeasure18("");
        meal.setStrMeasure19("");
        meal.setStrMeasure20("");

        List<Meal> meals = mock(List.class);

        when(recipes.getMeals()).thenReturn(meals);
        when(meals.get(0)).thenReturn(meal);
        when(mockResponse.body()).thenReturn(recipes);

        viewModel.onResponse(mockCall, mockResponse);

        verify(view).hideProgressLoader();
        verify(view).showRecipe(meal, ingredientNameCaptor.capture(), ingredientQtyCaptor.capture());

        assertEquals(ingredientNameCaptor.getValue().size(), 10);
        assertEquals(ingredientQtyCaptor.getValue().size(), 10);
    }

    @Test
    public void onErrorShouldTellViewToShowErrorView() {
        Throwable t = mock(Throwable.class);

        viewModel.onFailure(mockCall, t);

        verify(view).hideProgressLoader();
        verify(view).showError();
    }

    @Test
    public void onStartShouldShowProgressView() {
        viewModel.onStart();

        verify(view).showProgressLoader();
    }
}