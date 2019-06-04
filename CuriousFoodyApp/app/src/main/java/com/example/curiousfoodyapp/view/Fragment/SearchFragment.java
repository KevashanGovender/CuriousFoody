package com.example.curiousfoodyapp.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.repo.MealRepo;
import com.example.curiousfoodyapp.service.MealDbService;
import com.example.curiousfoodyapp.service.RetrofitClient;
import com.example.curiousfoodyapp.view.adapter.RecipeCardAdapter;
import com.example.curiousfoodyapp.viewmodel.RandomRecipeViewModel;
import com.example.curiousfoodyapp.viewmodel.SearchViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;


public class SearchFragment extends Fragment implements SearchView, FavoriteMealView {

    private EditText searchInput;
    private ImageView searchBtn;
    private RecyclerView searchResultTv;
    private View errorView;
    private View progressView;
    private View content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchInput = view.findViewById(R.id.editText);
        searchBtn = view.findViewById(R.id.searchBtn);
        searchResultTv = view.findViewById(R.id.search_result_rv);
        errorView = view.findViewById(R.id.error_view);
        progressView = view.findViewById(R.id.full_screen_loader);
        content = view.findViewById(R.id.search_bar_container);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        searchResultTv.setLayoutManager(layoutManager);

        Retrofit client = RetrofitClient.getClient();
        MealDbService service = client.create(MealDbService.class);

        MealRepo repo = new MealRepo(service);

        SearchViewModel viewModel = new SearchViewModel(repo, this);

        searchBtn.setOnClickListener(v -> viewModel.search(searchInput.getText().toString()));
    }

    @Override
    public void showSearchResults(List<Meal> meals) {
        progressView.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        searchResultTv.setVisibility(View.VISIBLE);
        RecipeCardAdapter adapter = new RecipeCardAdapter(getContext(), meals, this);
        searchResultTv.setAdapter(adapter);
    }

    @Override
    public void noSearchResults() {
        Toast.makeText(getContext(), "Sorry couldnt find anything", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorView() {
        content.setVisibility(View.GONE);
        searchResultTv.setVisibility(View.GONE);
        progressView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressView() {
        content.setVisibility(View.GONE);
        searchResultTv.setVisibility(View.GONE);
        progressView.setVisibility(View.VISIBLE);
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
