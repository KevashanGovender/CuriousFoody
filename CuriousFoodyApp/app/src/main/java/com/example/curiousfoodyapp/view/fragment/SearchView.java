package com.example.curiousfoodyapp.view.fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface SearchView extends BaseView{

    void showSearchResults(List<Meal> meals);

    void noSearchResults();
}
