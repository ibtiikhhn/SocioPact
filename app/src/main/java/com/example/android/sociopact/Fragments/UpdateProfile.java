package com.example.android.sociopact.Fragments;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.sociopact.ModelClasses.ProfileModelClass;
import com.example.android.sociopact.ModelClasses.SignUpModelClass;
import com.example.android.sociopact.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateProfile extends Fragment {


    ImageView imageView;
    EditText address;
    EditText bio;
    EditText about;
    EditText phone;
    Button updateInfoButton;
    ImageButton imageButton;
    Bitmap bitmap;
    public static UpdateProfile newInstance(){
        // Required empty public constructor
        return new UpdateProfile();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_profile, container, false);
        imageView = v.findViewById(R.id.updateImageView);
        address = v.findViewById(R.id.addressEditText);
        bio = v.findViewById(R.id.bioEditText);
        about = v.findViewById(R.id.aboutMeEditText);
        phone = v.findViewById(R.id.phoneEditText);
        imageButton = v.findViewById(R.id.selectImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImage();
            }
        });

        updateInfoButton = v.findViewById(R.id.updateProfileInfoButton);
        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfiles();
            }
        });




        return v;
    }


    public void getImage() {
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // ******** code for crop image
        i.putExtra("crop", "true");
        i.putExtra("aspectX", 100);
        i.putExtra("aspectY", 100);
        i.putExtra("outputX", 256);
        i.putExtra("outputY", 356);

        try {

            i.putExtra("return-data", true);
            startActivityForResult(
                    Intent.createChooser(i, "Select Picture"), 0);
        }catch (ActivityNotFoundException ex){
            ex.printStackTrace();
        }
    }

    public void updateProfiles() {
        String addresss = address.getText().toString();
        String bioo = bio.getText().toString();
        String aboutt = about.getText().toString();
        String phonee = phone.getText().toString();

        ProfileModelClass.about = aboutt;
        ProfileModelClass.address = addresss;
        ProfileModelClass.bio = bioo;
        ProfileModelClass.phone = phonee;
        ProfileModelClass.name = SignUpModelClass.getName();
        ProfileModelClass.image = bitmap;

        Toast.makeText(getContext(), "Profile has been updated", Toast.LENGTH_SHORT).show();

        getActivity().getSupportFragmentManager().popBackStack();


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode == Activity.RESULT_OK){
            try {

                Bundle bundle = data.getExtras();
                bitmap = bundle.getParcelable("data");
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
