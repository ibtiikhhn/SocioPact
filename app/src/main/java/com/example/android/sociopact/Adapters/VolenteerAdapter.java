package com.example.android.sociopact.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sociopact.ModelClasses.VolenteerModelClass;
import com.example.android.sociopact.R;

import java.util.List;

public class VolenteerAdapter extends RecyclerView.Adapter<VolenteerAdapter.VolenteerViewHolder> {

    public class VolenteerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView skills;
        TextView cause;

        public VolenteerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.volunteerImage);
            name = itemView.findViewById(R.id.volunteerNameTextView);
            skills = itemView.findViewById(R.id.volunteerSkills);
            cause = itemView.findViewById(R.id.volenteerCauseTextView);

        }
    }

    private Context context;
    public List<VolenteerModelClass> list;

    public VolenteerAdapter(Context context, List<VolenteerModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VolenteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.volunteer_card_view, null);
        return new VolenteerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolenteerViewHolder holder, int position) {
        VolenteerModelClass volenteerModelClass = list.get(position);
        holder.imageView.setImageBitmap(volenteerModelClass.getImage());
        holder.name.setText(volenteerModelClass.getName());
        holder.cause.setText(volenteerModelClass.getCause());
        holder.skills.setText(volenteerModelClass.getSkills());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}
