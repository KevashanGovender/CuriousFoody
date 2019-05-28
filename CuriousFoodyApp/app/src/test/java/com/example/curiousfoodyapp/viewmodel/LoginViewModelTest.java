package com.example.curiousfoodyapp.viewmodel;

import com.example.curiousfoodyapp.view.Activity.ILoginView;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LoginViewModelTest {

    @Mock
    private ILoginView view;

    private LoginViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        viewModel = new LoginViewModel(view);
    }

    @Test
    public void ifUserIsSignedInShowHome(){
        FirebaseUser user = mock(FirebaseUser.class);

        viewModel.checkUser(user);

        verify(view).showHome();
    }

    @Test
    public void ifUserIsNotSignedInShowSignIn() {
        viewModel.checkUser(null);

        verify(view).showSignIn();
    }
}