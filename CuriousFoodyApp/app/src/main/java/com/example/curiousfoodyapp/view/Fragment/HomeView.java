package com.example.curiousfoodyapp.view.Fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface HomeView {

    void showLoader();
    void showErrorView();
    void setUpCarousal(List<Meal>meals);
}
