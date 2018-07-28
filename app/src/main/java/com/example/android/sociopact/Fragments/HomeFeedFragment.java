package com.example.android.sociopact.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.sociopact.Adapters.CampgainAdapter;
import com.example.android.sociopact.Interfaces.AllCampgainsInterface;
import com.example.android.sociopact.ModelClasses.Campgain;
import com.example.android.sociopact.NetworkUtils.GetAllCampgains;
import com.example.android.sociopact.NetworkUtils.Urls;
import com.example.android.sociopact.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFeedFragment extends Fragment implements AllCampgainsInterface {
    public static final String TAG = "The Tags";


    public static HomeFeedFragment newInstance() {
        return new HomeFeedFragment();
    }
    SharedPreferences.Editor editor;
    FloatingActionButton fab;
    LoginFragment loginFragment;
    AddCampgainFragment  addCampgainFragment;
    ProgressBar progressBar;

    SwipeRefreshLayout swipeRefreshLayout;

    public static List<Campgain> list;
    RecyclerView recyclerView;

    GetAllCampgains getAllCampgains;
    CampgainAdapter campgainAdapter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_feed, container, false);

        progressBar = v.findViewById(R.id.progressBarHome);
        recyclerView = v.findViewById(R.id.recycler_view);
        swipeRefreshLayout = v.findViewById(R.id.swiperefresh);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        campgainAdapter = new CampgainAdapter(getActivity(), list);

        updateUi();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllCampgains = new GetAllCampgains(HomeFeedFragment.this, getContext(), progressBar, getActivity());
                getAllCampgains.execute(Urls.allCampgains);
                updateUi();
                swipeRefreshLayout.setRefreshing(false);
            }
        });



        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.placeholderUser, addCampgainFragment).addToBackStack(null)
                        .commit();

            }
        });
        getAllCampgains = new GetAllCampgains(HomeFeedFragment.this, getContext(), progressBar, getActivity());
        getAllCampgains.execute(Urls.allCampgains);
        return v;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().finish();
    }

    public static void addLists(Campgain lista) {
        list.add(lista);
    }

    @Override
    public void allCampgains(Campgain campgains) {
        Log.i(TAG, "allCampgains: " + campgains);
        addLists(campgains);
        updateUi();

    }

    public void updateUi() {
        addCampgainFragment = AddCampgainFragment.newInstance();
        recyclerView.setAdapter(campgainAdapter);
    }
}
