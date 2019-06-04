package com.example.curiousfoodyapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.database.MealDatabase;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.tasks.CheckMealTask;
import com.example.curiousfoodyapp.tasks.DeleteMealTask;
import com.example.curiousfoodyapp.tasks.InsertMealTask;
import com.example.curiousfoodyapp.view.Activity.RecipeDetailActivity;
import com.example.curiousfoodyapp.view.Fragment.FavoriteMealView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeCardViewHolder> {

    private Context context;
    private List<Meal> meals;
    private FavoriteMealView favoriteMealView;

    public RecipeCardAdapter(Context context, List<Meal> meals, FavoriteMealView favoriteMealView) {
        this.context = context;
        this.meals = meals;
        this.favoriteMealView = favoriteMealView;
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent,false);
        return new RecipeCardViewHolder(view, favoriteMealView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardViewHolder holder, int position) {
        holder.setUpCard(meals.get(position));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class RecipeCardViewHolder extends RecyclerView.ViewHolder {

        private MealDatabase database;
        private ImageView recipeThumb;
        private TextView recipeName;
        private ImageView favBtn;
        private FavoriteMealView favoriteMealView;
        private Context context;

        public RecipeCardViewHolder(@NonNull View itemView, FavoriteMealView favoriteMealView, Context context) {
            super(itemView);
            this.favoriteMealView = favoriteMealView;
            this.context = context;

            recipeThumb = itemView.findViewById(R.id.recipe_pic);
            recipeName = itemView.findViewById(R.id.recipie_name);
            favBtn = itemView.findViewById(R.id.favBtn);

            database = MealDatabase.getInstance(context);
        }

        void setUpCard(Meal meal){
            Picasso.get().load(meal.getStrMealThumb()).into(recipeThumb);

            recipeName.setText(meal.getStrMeal());

            recipeThumb.setOnClickListener(view -> {
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra("RecipeName", meal.getStrMeal());

                context.startActivity(intent);
            });

            favBtn.setOnClickListener(v -> {
                try {
                    if(new CheckMealTask(database).execute(meal.getIdMeal()).get() != null){
                        new DeleteMealTask(database, favoriteMealView).execute(meal.getIdMeal());
                    } else {
                        new InsertMealTask(database, favoriteMealView).execute(meal);
                    }

                } catch (ExecutionException e) {
                    Log.d("RECIPE_CARD_ADAPTER", e.getLocalizedMessage());
                } catch (InterruptedException e) {
                    Log.d("RECIPE_CARD_ADAPTER", e.getLocalizedMessage());
                }
            });
        }
    }
}
