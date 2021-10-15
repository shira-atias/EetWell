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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatwell.R;
import com.example.eatwell.models.meal.Meals;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMeal extends RecyclerView.Adapter<AdapterMeal.ViewHolder> {
    private ArrayList<Meals> meals;

    public AdapterMeal(ArrayList<Meals> meals ){
        this.meals = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.item_meals,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Meals meal= meals.get(position);
    holder.tvNameMeal.setText(meal.getName());
        Picasso.get().load(meal.getImage()).into(holder.ivMeal);
        holder.itemView.setOnClickListener(v -> {
            Bundle arg= new Bundle();
            arg.putString("idMeal",meal.getId()+"");
           Navigation.findNavController(v).navigate(R.id.action_mealsFragment_to_detailMealFragment,arg);
        });

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameMeal;
        ImageView ivMeal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMeal = itemView.findViewById(R.id.tvNameMeal);
            ivMeal=itemView.findViewById(R.id.ivMeal);
        }
    }
}
