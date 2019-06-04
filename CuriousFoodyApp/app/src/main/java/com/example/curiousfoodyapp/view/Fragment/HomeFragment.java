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
import com.example.curiousfoodyapp.viewmodel.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements HomeView, FavoriteMealView {

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        viewModel = new HomeViewModel(repo, this);

        DocumentReference topic = db.collection("RecipeOfTheWeekTopic").document("seIcmu69ULnN8qM65dzo");
        topic.get().addOnCompleteListener(task -> {
            DocumentSnapshot doc = task.getResult();
            viewModel.getRecipes(doc.get("topic").toString());
        }).addOnFailureListener(e -> viewModel.getRecipes("chicken"));

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
        recyclerView.setAdapter(new RecipeCardAdapter(getContext(), meals, this));

        progressLoader.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
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
