package com.example.curiousfoodyapp.view.Fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface SearchView extends BaseView{

    void showSearchResults(List<Meal> meals);

    void noSearchResults();
}
