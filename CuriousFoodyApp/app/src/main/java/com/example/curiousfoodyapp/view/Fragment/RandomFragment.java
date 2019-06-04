package com.example.curiousfoodyapp.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.repo.MealRepo;
import com.example.curiousfoodyapp.service.MealDbService;
import com.example.curiousfoodyapp.service.RetrofitClient;
import com.example.curiousfoodyapp.view.adapter.RecipeCardAdapter;
import com.example.curiousfoodyapp.viewmodel.RandomRecipeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;


public class RandomFragment extends Fragment implements RandomRecipeView, FavoriteMealView {

    private View contentView;
    private View errorView;
    private View progressView;
    private FloatingActionButton randomFab;
    private RecyclerView randomRecipeRv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_random, container, false);

        contentView = view.findViewById(R.id.content_view);
        errorView = view.findViewById(R.id.error_view);
        progressView = view.findViewById(R.id.full_screen_loader);
        randomFab = view.findViewById(R.id.random_fab);
        randomRecipeRv = view.findViewById(R.id.random_recipe_rv);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        randomRecipeRv.setLayoutManager(layoutManager);

        Retrofit client = RetrofitClient.getClient();
        MealDbService service = client.create(MealDbService.class);

        MealRepo repo = new MealRepo(service);

        RandomRecipeViewModel viewModel = new RandomRecipeViewModel(repo, this);
        viewModel.getRandomRecipe();

        randomFab.setOnClickListener(v -> viewModel.getRandomRecipe());
    }

    @Override
    public void showProgressView() {
        contentView.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRandomRecipe(List<Meal> meals) {
        randomRecipeRv.setAdapter(new RecipeCardAdapter(getContext(), meals, this));
        contentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressView() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void mealAdded() {
        Toast.makeText(getContext(), "Recipe added to favorites", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mealRemoved() {
        Toast.makeText(getContext(), "Recipe removed from favorites", Toast.LENGTH_SHORT).show();
    }
}
