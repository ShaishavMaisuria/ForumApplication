package com.example.forumapplication;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Array;
import java.util.ArrayList;


public class ForumFragment extends Fragment {

    private static final String ARG_PARAM_FORUM = "ARG_PARAM_FORUM";
    private static final String TAG = "ForumFragment";


    private DataServices.AuthResponse fauth;


    public ForumFragment() {
        // Required empty public constructor
    }


    public static ForumFragment newInstance(DataServices.AuthResponse auth) {
        ForumFragment fragment = new ForumFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_FORUM, auth);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fauth = (DataServices.AuthResponse) getArguments().getSerializable(ARG_PARAM_FORUM);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_forum, container, false);


        new MyTask().execute(fauth);
        Log.d(TAG,"onCreateView  Forum Successful");

        recyclerView=view.findViewById(R.id.recyclerViewForums);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    LinearLayoutManager layoutManager;
ArrayList<DataServices.Forum> forumArrayL = new ArrayList<>();
    RecyclerView recyclerView;
    private class MyTask extends AsyncTask<DataServices.AuthResponse, String, ArrayList<DataServices.Forum>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(ArrayList<DataServices.Forum> forums) {
            super.onPostExecute(forums);
            if(forums !=null){
            forumArrayL.addAll(forums);


            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<DataServices.Forum> doInBackground(DataServices.AuthResponse... authResponses) {
            try {
                ArrayList<DataServices.Forum> forumArrayList= DataServices.getAllForums(authResponses[0].token);
                return forumArrayList;
            } catch (DataServices.RequestException e) {
                e.printStackTrace();
                Log.d(TAG,"error in fourm calling");
            }
            return null;
        }
    }
}