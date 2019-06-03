package com.example.curiousfoodyapp.view.Activity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.repo.MealRepo;
import com.example.curiousfoodyapp.service.MealDbService;
import com.example.curiousfoodyapp.service.RetrofitClient;
import com.example.curiousfoodyapp.view.adapter.IngredientAdapter;
import com.example.curiousfoodyapp.viewmodel.RecipeDetailsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Retrofit;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailView {

    private ImageView recipeHeader;
    private FloatingActionButton youtubeFab;
    private TextView recipeTypeTv;
    private TextView recipeRegionTv;
    private RecyclerView ingredientsRv;
    private TextView recipeMethodTv;
    private View progressLoader;
    private View errorView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_activity);

        recipeHeader = findViewById(R.id.recipe_header);
        youtubeFab = findViewById(R.id.youtube_fab);
        recipeTypeTv = findViewById(R.id.recipe_type);
        recipeRegionTv = findViewById(R.id.recipe_region);
        ingredientsRv = findViewById(R.id.ingredient_rv);
        recipeMethodTv = findViewById(R.id.recipe_method);

        progressLoader = findViewById(R.id.full_screen_loader);
        errorView = findViewById(R.id.error_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        ingredientsRv.setLayoutManager(layoutManager);

        Retrofit client = RetrofitClient.getClient();
        MealDbService service = client.create(MealDbService.class);

        MealRepo repo = new MealRepo(service);
        RecipeDetailsViewModel viewModel = new RecipeDetailsViewModel(repo, this);
        viewModel.checkIntent(getIntent());
    }

    @Override
    public void hideProgressLoader() {
        progressLoader.setVisibility(View.GONE);
    }

    @Override
    public void showRecipe(Meal meal, List<String> capture, List<String> strings) {
        IngredientAdapter adapter = new IngredientAdapter(capture, strings);
        ingredientsRv.setAdapter(adapter);

        Picasso.get().load(meal.getStrMealThumb()).into(recipeHeader);
        recipeTypeTv.setText(meal.getStrCategory());
        recipeRegionTv.setText(meal.getStrArea());
        recipeMethodTv.setText(meal.getStrInstructions());
    }

    @Override
    public void showError() {
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressLoader() {
        progressLoader.setVisibility(View.VISIBLE);
    }
}
