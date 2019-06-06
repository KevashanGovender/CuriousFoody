package com.example.curiousfoodyapp.viewmodel;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.fragment.SearchView;

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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchViewModelTest {

    @Mock
    private IMealRepo repo;
    @Mock
    private SearchView view;
    @Mock
    private  Call<Recipes> mockCall;
    @Mock
    private Response<Recipes> response;

    @Captor
    private ArgumentCaptor<List<Meal>> captor;

    private SearchViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        viewModel = new SearchViewModel(repo, view);
    }

    @Test
    public void shouldSearchForInputTermViaSearchByRecipeService() {
        viewModel.search("chicken");

        verify(repo).searchByRecipe(viewModel, "chicken");
    }

    @Test
    public void onResponseShouldCheckIfResultIsNotNullAndPassResultToView(){
        Recipes recipes = mock(Recipes.class);
        List<Meal> meals = new ArrayList<>();

        Meal meal1 = new Meal();
        Meal meal2 = new Meal();
        Meal meal3 = new Meal();
        meals.add(meal1);
        meals.add(meal2);
        meals.add(meal3);
        meals.add(null);
        meals.add(null);


        when(response.body()).thenReturn(recipes);
        when(recipes.getMeals()).thenReturn(meals);

        viewModel.onResponse(mockCall, response);

        verify(view).showSearchResults(captor.capture());

        assertEquals(3, captor.getValue().size());
        assertEquals(meal1, captor.getValue().get(0));
        assertEquals(meal2, captor.getValue().get(1));
        assertEquals(meal3, captor.getValue().get(2));
    }

    @Test
    public void onResponseShouldCheckIfResultIsNullAndIfNullShowNoResultsView() {
        Recipes recipes = mock(Recipes.class);
        List<Meal> meals = new ArrayList<>();

        meals.add(null);
        meals.add(null);


        when(response.body()).thenReturn(recipes);
        when(recipes.getMeals()).thenReturn(meals);

        viewModel.onResponse(mockCall, response);

        verify(view, never()).showSearchResults(captor.capture());
        verify(view).noSearchResults();
    }

    @Test
    public void onFailureShouldShowErrorView() {
        Throwable t = mock(Throwable.class);

        viewModel.onFailure(mockCall, t);

        verify(view).showErrorView();
    }

    @Test
    public void onStartShouldShowProgressView(){
        viewModel.onStart();

        verify(view).showProgressView();
    }
}