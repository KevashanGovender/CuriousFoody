package com.example.curiousfoodyapp.viewmodel;

import com.example.curiousfoodyapp.view.Activity.ILoginView;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel {
    private ILoginView view;

    public LoginViewModel(ILoginView view) {

        this.view = view;
    }

    public void checkUser(FirebaseUser user) {
        if(user != null) {
            view.showHome();
        } else {
            view.showSignIn();
        }
    }
}
