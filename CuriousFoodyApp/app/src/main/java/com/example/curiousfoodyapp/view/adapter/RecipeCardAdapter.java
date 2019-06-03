package com.example.curiousfoodyapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.view.Activity.RecipeDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeCardViewHolder> {

    static Context context;
    private List<Meal> meals;

    public RecipeCardAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent,false);
        return new RecipeCardViewHolder(view);
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

        private ImageView recipeThumb;
        private TextView recipeName;

        public RecipeCardViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeThumb = itemView.findViewById(R.id.recipe_pic);
            recipeName = itemView.findViewById(R.id.recipie_name);
        }

        void setUpCard(Meal meal){
            Picasso.get().load(meal.getStrMealThumb()).into(recipeThumb);
            recipeName.setText(meal.getStrMeal());
            recipeThumb.setOnClickListener(view -> {
                Intent intent = new Intent(RecipeCardAdapter.context, RecipeDetailActivity.class);
                intent.putExtra("RecipeName", meal.getStrMeal());

                context.startActivity(intent);
            });
        }
    }
}
