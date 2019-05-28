package com.example.curiousfoodyapp.view.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.curiousfoodyapp.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    CarouselView carouselView;

    int [] images = {R.drawable.com_facebook_button_icon_blue, R.drawable.com_facebook_button_login_logo};


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = view.findViewById(R.id.swipe_view);

        carouselView.setPageCount(images.length);
        carouselView.setImageListener((position, imageView) -> imageView.setImageResource(images[position]));

        return view;
    }
}
