package com.example.android.sociopact.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sociopact.ModelClasses.Campgain;
import com.example.android.sociopact.R;

import java.util.List;

public class CampgainAdapter extends RecyclerView.Adapter<CampgainAdapter.CampgainViewHolder> {

    public class CampgainViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
        TextView location;

        public CampgainViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titles);
            description = itemView.findViewById(R.id.descriptions);
            image = itemView.findViewById(R.id.images);
            location = itemView.findViewById(R.id.locationTextView);
        }
    }

    private Context context;
    private List<Campgain> campgainList;


    public CampgainAdapter(Context context, List<Campgain> campgainList) {
        this.context = context;
        this.campgainList = campgainList;
    }

    @NonNull
    @Override

    public CampgainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_view, null);
        return new CampgainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CampgainViewHolder holder, int position) {
        Campgain campgain = campgainList.get(position);
        holder.title.setText(campgain.getTitle());
        holder.description.setText(campgain.getInfo());
        holder.image.setImageBitmap(campgain.getImage());
        holder.location.setText(campgain.getLocation());

    }

    @Override
    public int getItemCount() {
        return campgainList.size();
    }


}
