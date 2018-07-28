package com.example.android.sociopact.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sociopact.ModelClasses.ProfileModelClass;
import com.example.android.sociopact.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    ImageView myImage;
    ImageButton updatePhotoButton;
    TextView nameTextView;
    TextView bioTextView;
    TextView aboutTextView;
    TextView addressTextView;
    Button editProfileButton;



    public static ProfileFragment newInstance() {
        // Required empty public constructor
        return new ProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        myImage = v.findViewById(R.id.imageView5);
        updatePhotoButton = v.findViewById(R.id.uploadImageButton);
        nameTextView = v.findViewById(R.id.nameTextView);
        bioTextView = v.findViewById(R.id.bioTextView);
        aboutTextView = v.findViewById(R.id.aboutTextView);
        addressTextView = v.findViewById(R.id.addressTextView);

        nameTextView.setText(ProfileModelClass.getName());
        bioTextView.setText(ProfileModelClass.getBio());
        aboutTextView.setText(ProfileModelClass.getAbout());
        addressTextView.setText(ProfileModelClass.getAddress());
        myImage.setImageBitmap(ProfileModelClass.getImage());



        return v;
    }

}
