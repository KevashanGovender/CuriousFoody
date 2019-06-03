package com.example.curiousfoodyapp.repo;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.service.MealDbService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import retrofit2.Call;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MealRepoTest {

    @Mock
    private MealDbService service;
    @Mock
    private ICallbackListener<List<Meal>> listener;
    @Mock
    private Call<List<Meal>> mockCall;

    private MealRepo repo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        repo = new MealRepo(service);
    }

    @Test
    public void searchByRecipe() {
        Call<Recipes> recipesCall = mock(Call.class);
        ICallbackListener<Recipes> recipesICallbackListener = mock(ICallbackListener.class);
        when(service.searchByRecipe(anyString())).thenReturn(recipesCall);

        repo.searchByRecipe(recipesICallbackListener, "curry");

        verify(recipesICallbackListener).onStart();
        verify(recipesCall).enqueue(recipesICallbackListener);
    }

    @Test
    public void searchById(){
        when(service.searchById(anyLong())).thenReturn(mockCall);

        repo.searchById(listener, 15552);

        verify(listener).onStart();
        verify(mockCall).enqueue(listener);
    }

    @Test
    public void getRandomRecipe(){
        when(service.getRandomRecipe()).thenReturn(mockCall);

        repo.getRandomRecipe(listener);

        verify(listener).onStart();
        verify(mockCall).enqueue(listener);
    }

    @Test
    public void filterByIngredient() {
        Call<Recipes> recipesCall = mock(Call.class);
        ICallbackListener<Recipes> recipesICallbackListener = mock(ICallbackListener.class);
        when(service.filterByIngredient("Chicken")).thenReturn(recipesCall);

        repo.filterByIngredient(recipesICallbackListener, "Chicken");

        verify(recipesICallbackListener).onStart();
        verify(recipesCall).enqueue(recipesICallbackListener);
    }

    @Test
    public void filterByType() {
        when(service.filterByType("Seafood")).thenReturn(mockCall);

        repo.filterByType(listener, "Seafood");

        verify(listener).onStart();
        verify(mockCall).enqueue(listener);
    }

    @Test
    public void filterByRegion() {
        when(service.filterByRegion("China")).thenReturn(mockCall);

        repo.filterByRegion(listener, "China");

        verify(listener).onStart();
        verify(mockCall).enqueue(listener);
    }
}