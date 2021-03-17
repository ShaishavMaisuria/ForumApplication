package com.example.forumapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;


public class LoginFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private String Tag = "loginFragment";


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }


    EditText userEmail;
    EditText userPassword;
    ExecutorService threadPool;

    Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        userEmail = view.findViewById(R.id.editTextEmailAddressLogin);
        userPassword = view.findViewById(R.id.editTextPasswordLogin);


        view.findViewById(R.id.buttonLoginFragmentLogin).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = userEmail.getText().toString();
                        String password = userPassword.getText().toString();


                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(getActivity(), "Password/Email cannot be empty", Toast.LENGTH_SHORT).show();

                        } else {

                                new MyTask().execute(email,password);
//
//                            threadPool = Executors.newFixedThreadPool(2);
//                            threadPool.execute(new DoWork(email, password));
//
//                            handler = new Handler(new Handler.Callback() {
//                                @Override
//                                public boolean handleMessage(@NonNull Message msg) {
//                                    switch (msg.what) {
//                                        case RegisterAccount.DoWork.STATUS_EXCEPTION:
//                                            Toast.makeText(getActivity(), "UnSuccessfull Login", Toast.LENGTH_SHORT).show();
//                                            break;
//                                        case RegisterAccount.DoWork.ACQUIRED_TOKEN:
//                                            mListener.loginIsSuccesful(msg.getData().getString(RegisterAccount.DoWork.DATA_TOKEN));
//                                            break;
//                                    }
//
//
//                                    return false;
//                                }
//                            });

                        }

                    }
                }
        );


        view.findViewById(R.id.buttonCreateNewAccountLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mListener.createNewAccount();
            }
        });


        return view;
    }



    private class MyTask extends AsyncTask<String, String, DataServices.AuthResponse> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(DataServices.AuthResponse s) {
            super.onPostExecute(s);
            if(s!=null) {

                mListener.loginIsSuccesful(s);
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
            String email = strings[0];
            String password = strings[1];

            try {
                DataServices.AuthResponse data= DataServices.login(email,password);
                return data;
            } catch (DataServices.RequestException e) {
                e.printStackTrace();
                return null;
            }




        }
    }



    LoginListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof LoginListener) {
            mListener = (LoginListener) context;
        } else {
            throw new RuntimeException(context.toString() + "must implement loginListener");
        }
    }

    interface LoginListener {
        void loginIsSuccesful( DataServices.AuthResponse auth);

        void createNewAccount();
    }


}
