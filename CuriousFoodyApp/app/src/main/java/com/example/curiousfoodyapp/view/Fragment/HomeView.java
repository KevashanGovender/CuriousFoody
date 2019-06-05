package com.example.curiousfoodyapp.view.Fragment;

import com.example.curiousfoodyapp.model.Meal;

import java.util.List;

public interface HomeView extends BaseView {
    void setUpCarousal(List<Meal>meals);
}
