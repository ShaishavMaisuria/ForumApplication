package com.example.forumapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginListener , CreateNewAccount.CreateNewContactListner {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new LoginFragment())
                .commit();

    }

  DataServices.AuthResponse mauth;

    @Override
    public void loginIsSuccesful(DataServices.AuthResponse auth) {
       mauth= auth;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ForumFragment.newInstance(mauth), "ForumFragment")

                .commit();


    }

    @Override
    public void returnToLogin() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new LoginFragment(), "LoginFragment")
                .commit();
    }

    @Override
    public void createNewAccount() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new CreateNewAccount(), "CreateNewAccount")
                                .commit();
    }
}