package com.example.forumapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CreateNewAccount extends Fragment {

    private static final String ARG_PARAM_CREATE_ACCOUNT = "ARG_PARAM_CREATE_ACCOUNT";

    private String cauth;
    private String Tag = "CreateNewAccount";

    public CreateNewAccount() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cauth = getArguments().getString(ARG_PARAM_CREATE_ACCOUNT);

        }
    }

    TextView viewUsername;
    TextView viewEmail;
    TextView viewPassword;
    String name;
    String email;
    String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_new_account, container, false);


        viewUsername = view.findViewById(R.id.editextPersonNameRegister);
        viewEmail = view.findViewById(R.id.editTextEmailAddressRegister);
        viewPassword = view.findViewById(R.id.editTextPasswordRegister);


        view.findViewById(R.id.buttonSubmitRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = viewUsername.getText().toString();
                email = viewEmail.getText().toString();
                password = viewPassword.getText().toString();
                Log.d("register", "name" + name);
                Log.d("register", "email" + email);
                Log.d("register", "password" + password);


                if (email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                    Toast.makeText(getActivity(), "Password/Email or Password cannot be empty", Toast.LENGTH_SHORT).show();

                } else {




                    new MyTask().execute(name,email,password);

                }
            }
        });


        view.findViewById(R.id.buttonCancelRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.returnToLogin();
            }
        });


        return view;
    }

    CreateNewContactListner mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragment.LoginListener) {
            mListener = (CreateNewContactListner) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement RegisterListener");
        }
    }

    interface CreateNewContactListner {
        void loginIsSuccesful(DataServices.AuthResponse auth);

        void returnToLogin();
    }


    private class MyTask extends AsyncTask<String, String, DataServices.AuthResponse>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(DataServices.AuthResponse authResponse) {
            super.onPostExecute(authResponse);

            if(authResponse!=null) {

                mListener.loginIsSuccesful(authResponse);
            }else{
                Toast.makeText(getActivity(), "UnSuccessfull Login", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected DataServices.AuthResponse doInBackground(String... strings) {
           String username=strings[0];
           String email=strings[1];
           String password=strings[2];


            try {
                DataServices.AuthResponse data= DataServices.register(username,email,password);
                return data;
            } catch (DataServices.RequestException e) {
                e.printStackTrace();
                return null;
            }


        }
    }
}
