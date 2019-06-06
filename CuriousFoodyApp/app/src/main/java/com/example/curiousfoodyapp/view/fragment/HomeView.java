package com.example.curiousfoodyapp.view.fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface HomeView extends BaseView {
    void setUpCarousal(List<Meal>meals);
}
