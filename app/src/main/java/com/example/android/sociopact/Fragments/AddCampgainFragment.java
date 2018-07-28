package com.example.android.sociopact.Fragments;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.sociopact.Interfaces.CreateCampgainInterface;
import com.example.android.sociopact.ModelClasses.Campgain;
import com.example.android.sociopact.NetworkUtils.PostCreateCampgain;
import com.example.android.sociopact.NetworkUtils.Urls;
import com.example.android.sociopact.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddCampgainFragment extends Fragment implements CreateCampgainInterface {

    public static final String HELL = "HEll";
    public static final int PICK_IMAGE = 1;
    private static int RESULT_LOAD_IMAGE = 1;
    public static final String TAG = "Hell";

    public static AddCampgainFragment newInstance() {
        return new AddCampgainFragment();
    }

    EditText mTitle;
    EditText mInfo;
    EditText mLocation;
    Button mUploadImage;
    Button postButton;
    public List list;
    ImageView imageView;
    Bitmap bitmap;
    PostCreateCampgain postCreateCampgain;
    ProgressBar progressBar;
    Campgain campgain;


    HomeFeedFragment homeFeedFragment = new HomeFeedFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_campgain, container, false);

        imageView = v.findViewById(R.id.selectedImage);
        list = new ArrayList();
        mTitle = v.findViewById(R.id.titleEditText);
        mInfo = v.findViewById(R.id.infoEditText);
        mLocation = v.findViewById(R.id.locationEditText);
        mUploadImage = v.findViewById(R.id.uploadImageButton);
        postButton = v.findViewById(R.id.postButton);
        progressBar = v.findViewById(R.id.campgainProgressBar);

        mUploadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getImage();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString();
                String info = mInfo.getText().toString();
                String location = mLocation.getText().toString();
                campgain = new Campgain(title, info, location,bitmap);

                Log.i(HELL, title);
                if (title != null && info != null && location != null){
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("campaign-title", title);
                        jsonObject.put("campaign-intro", info);
                        jsonObject.put("campaign-location", location);
//                        jsonObject.put("campaign-image", "image");

                        postCreateCampgain = new PostCreateCampgain(AddCampgainFragment.this, getContext(),progressBar, getActivity(),jsonObject);
                        postCreateCampgain.execute(Urls.createCampgain);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HomeFeedFragment.addLists(campgain);
                }else{
                    Toast.makeText(getActivity(), "Fill the form properly", Toast.LENGTH_SHORT).show();
                }

            }
        });



        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    @Override
    public void setResult(Boolean result) {
        if (!result) {
            Log.i(TAG, "setResult: "+result.toString());
        }else {
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
