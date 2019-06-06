package com.example.curiousfoodyapp.viewmodel;

import android.content.Intent;

import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.model.Recipes;
import com.example.curiousfoodyapp.repo.ICallbackListener;
import com.example.curiousfoodyapp.repo.IMealRepo;
import com.example.curiousfoodyapp.view.activity.RecipeDetailView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Response;

public class RecipeDetailsViewModel implements ICallbackListener<Recipes> {


    private IMealRepo repo;
    private RecipeDetailView view;

    public RecipeDetailsViewModel(IMealRepo repo, RecipeDetailView view) {
        this.repo = repo;
        this.view = view;
    }

    public void checkIntent(Intent intent) {
        if(intent != null){
            if(intent.getStringExtra("RecipeName") != null) {
                repo.searchByRecipe(this, intent.getStringExtra("RecipeName"));
            } else if(intent.getStringExtra("type").equals("random")){
                repo.getRandomRecipe(this);
            }
        }
    }

    @Override
    public void onResponse(@NonNull Call<Recipes> call, @NonNull Response<Recipes> response) {
        view.hideProgressLoader();

        Meal meal = response.body().getMeals().get(0);

        List<String> ingredientNames = getIngredientNames(meal);
        List<String> ingredientQtys = getIngredientQtys(meal);

        view.showRecipe(meal, ingredientNames, ingredientQtys);
    }

    @Override
    public void onFailure(@NonNull Call<Recipes> call, @NonNull Throwable t) {
        view.hideProgressLoader();
        view.showError();
    }

    @Override
    public void onStart() {
        view.showProgressLoader();
    }

    private List<String> getIngredientQtys(Meal meal) {

        List<String> ingredientQtys = new ArrayList<>();

        if(!meal.getStrMeasure1().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure1());
        }

        if(!meal.getStrMeasure2().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure2());
        }

        if(!meal.getStrMeasure3().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure3());
        }

        if(!meal.getStrMeasure4().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure4());
        }

        if(!meal.getStrMeasure5().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure5());
        }

        if(!meal.getStrMeasure6().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure6());
        }

        if(!meal.getStrMeasure7().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure7());
        }

        if(!meal.getStrMeasure8().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure8());
        }

        if(!meal.getStrMeasure9().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure9());
        }

        if(!meal.getStrMeasure10().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure10());
        }

        if(!meal.getStrMeasure11().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure11());
        }

        if(!meal.getStrMeasure12().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure12());
        }

        if(!meal.getStrMeasure13().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure13());
        }

        if(!meal.getStrMeasure14().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure14());
        }

        if(!meal.getStrMeasure15().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure15());
        }

        if(!meal.getStrMeasure16().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure16());
        }

        if(!meal.getStrMeasure17().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure17());
        }

        if(!meal.getStrMeasure18().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure18());
        }

        if(!meal.getStrMeasure19().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure19());
        }

        if(!meal.getStrMeasure20().isEmpty()){
            ingredientQtys.add(meal.getStrMeasure20());
        }

        return ingredientQtys;
    }

    private List<String> getIngredientNames(Meal meal) {

        List<String> ingredientNames = new ArrayList<>();

        if(!meal.getStrIngredient1().isEmpty()){
            ingredientNames.add(meal.getStrIngredient1());
        }

        if(!meal.getStrIngredient2().isEmpty()){
            ingredientNames.add(meal.getStrIngredient2());
        }

        if(!meal.getStrIngredient3().isEmpty()){
            ingredientNames.add(meal.getStrIngredient3());
        }

        if(!meal.getStrIngredient4().isEmpty()){
            ingredientNames.add(meal.getStrIngredient4());
        }

        if(!meal.getStrIngredient5().isEmpty()){
            ingredientNames.add(meal.getStrIngredient5());
        }

        if(!meal.getStrIngredient6().isEmpty()){
            ingredientNames.add(meal.getStrIngredient6());
        }

        if(!meal.getStrIngredient7().isEmpty()){
            ingredientNames.add(meal.getStrIngredient7());
        }

        if(!meal.getStrIngredient8().isEmpty()){
            ingredientNames.add(meal.getStrIngredient8());
        }

        if(!meal.getStrIngredient9().isEmpty()){
            ingredientNames.add(meal.getStrIngredient9());
        }

        if(!meal.getStrIngredient10().isEmpty()){
            ingredientNames.add(meal.getStrIngredient10());
        }

        if(!meal.getStrIngredient11().isEmpty()){
            ingredientNames.add(meal.getStrIngredient11());
        }

        if(!meal.getStrIngredient12().isEmpty()){
            ingredientNames.add(meal.getStrIngredient12());
        }

        if(!meal.getStrIngredient13().isEmpty()){
            ingredientNames.add(meal.getStrIngredient13());
        }

        if(!meal.getStrIngredient14().isEmpty()){
            ingredientNames.add(meal.getStrIngredient14());
        }

        if(!meal.getStrIngredient15().isEmpty()){
            ingredientNames.add(meal.getStrIngredient15());
        }

        if(!meal.getStrIngredient16().isEmpty()){
            ingredientNames.add(meal.getStrIngredient16());
        }

        if(!meal.getStrIngredient17().isEmpty()){
            ingredientNames.add(meal.getStrIngredient17());
        }

        if(!meal.getStrIngredient18().isEmpty()){
            ingredientNames.add(meal.getStrIngredient18());
        }

        if(!meal.getStrIngredient19().isEmpty()){
            ingredientNames.add(meal.getStrIngredient19());
        }

        if(!meal.getStrIngredient20().isEmpty()){
            ingredientNames.add(meal.getStrIngredient20());
        }


        return ingredientNames;
    }
}
