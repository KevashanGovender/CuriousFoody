package com.example.curiousfoodyapp.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.example.curiousfoodyapp.R;
import com.example.curiousfoodyapp.viewmodel.LoginViewModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ILoginView {

    private ProgressBar progressBar;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private View signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInButton = findViewById(R.id.sign_in_btn);
        progressBar = findViewById(R.id.progress_bar);

        LoginViewModel viewModel = new LoginViewModel(this);
        viewModel.checkUser(user);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                System.out.println("THIS FAILED");
            }
        }
    }

    @Override
    public void showHome() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Hello, " + user.getDisplayName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSignIn() {
        signInButton.setVisibility(View.VISIBLE);
        signInButton.setOnClickListener(v -> {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder().build(),
                    new AuthUI.IdpConfig.EmailBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    123);
        });
    }
}
