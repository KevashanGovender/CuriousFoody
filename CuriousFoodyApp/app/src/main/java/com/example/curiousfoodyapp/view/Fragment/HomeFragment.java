package com.example.curiousfoodyapp.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.model.Meal;
import com.example.curiousfoodyapp.repo.MealRepo;
import com.example.curiousfoodyapp.service.MealDbService;
import com.example.curiousfoodyapp.service.RetrofitClient;
import com.example.curiousfoodyapp.view.adapter.RecipeCardAdapter;
import com.example.curiousfoodyapp.viewmodel.HomeViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements HomeView {

    private RecyclerView recyclerView;
    private View progressLoader;
    private View contentView;
    private View errorView;
    private HomeViewModel viewModel;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.swipe_view);
        progressLoader = view.findViewById(R.id.full_screen_loader);
        contentView = view.findViewById(R.id.content_view);
        errorView = view.findViewById(R.id.error_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        Retrofit client = RetrofitClient.getClient();
        MealDbService service = client.create(MealDbService.class);

        MealRepo repo = new MealRepo(service);

        viewModel = new HomeViewModel(repo, this);
        viewModel.getRecipes("chicken");

        return view;
    }

    @Override
    public void showLoader() {
        progressLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView() {
        progressLoader.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        errorView.setOnClickListener(view -> viewModel.getRecipes("chicken"));
    }

    @Override
    public void setUpCarousal(List<Meal> meals) {
        recyclerView.setAdapter(new RecipeCardAdapter(getContext(), meals));

        progressLoader.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }
}