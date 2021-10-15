package com.example.eatwell.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatwell.R;
import com.example.eatwell.models.categories.Categories;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    private ArrayList<Categories> categoriesList;

    public AdapterCategory(ArrayList<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.item_category,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categories category= categoriesList.get(position);
        holder.tvNameCategory.setText(category.getNameCategories());
        Picasso.get().load(category.getImageCategories()).into(holder.ivCategory);

        holder.itemView.setOnClickListener(v -> {
            Bundle arg= new Bundle();
            arg.putString("nameCategory",category.getNameCategories());

            Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_mealsFragment,arg);

        });
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameCategory;
        ImageView ivCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCategory= itemView.findViewById(R.id.tvNameCategory);
            ivCategory= itemView.findViewById(R.id.ivCategory);

        }
    }
}
