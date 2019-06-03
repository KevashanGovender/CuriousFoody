package com.example.curiousfoodyapp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.curiousfoodyapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<String> ingredientName;
    private List<String> ingredientQty;

    public IngredientAdapter(List<String> ingredientName, List<String> ingredientQty) {
        this.ingredientName = ingredientName;
        this.ingredientQty = ingredientQty;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_item, parent,false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.setUp(ingredientName.get(position), ingredientQty.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredientName.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        private TextView ingredientName;
        private TextView ingredientQty;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = itemView.findViewById(R.id.ingreditent_name);
            ingredientQty = itemView.findViewById(R.id.ingredient_quantity);
        }

        public void setUp(String ingredient, String quantity){
            ingredientName.setText(ingredient);
            ingredientQty.setText(quantity);
        }
    }
}
