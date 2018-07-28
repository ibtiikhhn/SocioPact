package com.example.android.sociopact.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sociopact.Adapters.VolenteerAdapter;
import com.example.android.sociopact.ModelClasses.VolenteerModelClass;
import com.example.android.sociopact.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteersFragment extends Fragment {

    public static List<VolenteerModelClass> list;
    RecyclerView recyclerView;
    public VolunteersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_volunteers, container, false);
        recyclerView = v.findViewById(R.id.recycler_viewVolunteers);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        VolenteerAdapter volenteerAdapter = new VolenteerAdapter(getActivity(), list);
        recyclerView.setAdapter(volenteerAdapter);
        return v;
    }

    public void onDetach() {
        super.onDetach();
        getActivity().getSupportFragmentManager().popBackStack();
    }

}
