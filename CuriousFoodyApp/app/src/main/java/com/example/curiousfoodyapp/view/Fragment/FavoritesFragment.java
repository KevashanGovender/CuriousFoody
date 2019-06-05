package com.example.curiousfoodyapp.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.tasks.GetFavoriteMealsTask;
import com.example.curiousfoodyapp.view.adapter.RecipeCardAdapter;
import com.example.curiousfoodyapp.viewmodel.FavoritesViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesFragment extends Fragment implements DisplayFavoriteMealView {

    private RecyclerView favoriteRecipeRv;
    private FavoritesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        favoriteRecipeRv = view.findViewById(R.id.favorite_recipes_rv);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentActivity activity = getActivity();
        if(activity != null){
            viewModel = ViewModelProviders.of(activity).get(FavoritesViewModel.class);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        favoriteRecipeRv.setLayoutManager(layoutManager);

        GetFavoriteMealsTask task = new GetFavoriteMealsTask(MealDatabase.getInstance(getContext()), this);

        viewModel.getFavoriteMeals(task);
    }

    @Override
    public void showFavorites(LiveData<List<Meal>> meals) {
        meals.observe(this, mealList -> {
            favoriteRecipeRv.setVisibility(View.VISIBLE);
            RecipeCardAdapter adapter = new RecipeCardAdapter(getContext(), mealList, this);
            favoriteRecipeRv.setAdapter(adapter);
        });
    }

    @Override
    public void showNoFavorites() {
        favoriteRecipeRv.setVisibility(View.GONE);
        Toast.makeText(getContext(), "No favorites, why don't you add some", Toast.LENGTH_SHORT).show();

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
