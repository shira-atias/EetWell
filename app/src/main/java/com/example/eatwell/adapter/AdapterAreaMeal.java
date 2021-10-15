package com.example.eatwell.adapter;

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

public class AdapterAreaMeal extends RecyclerView.Adapter<AdapterAreaMeal.ViewHolder> {
    private ArrayList<Meals> areaMeals;
    private Fragment fragment;

    public AdapterAreaMeal(ArrayList<Meals> areaMeals, Fragment fragment) {
        this.areaMeals = areaMeals;
        this.fragment= fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.item_meals,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meals areaMeal= areaMeals.get(position);
        holder.tvNameAreaMeal.setText(areaMeal.getName());
        Picasso.get().load(areaMeal.getImage()).into(holder.ivAreaMeal);
        holder.itemView.setOnClickListener(v -> {
            Bundle arg= new Bundle();
            arg.putString("idMeal",areaMeal.getId()+"");
            Navigation.findNavController(v).navigate(R.id.action_areaMealFragment_to_detailMealFragment,arg);
        });
    }

    @Override
    public int getItemCount() {
        return areaMeals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNameAreaMeal;
        ImageView ivAreaMeal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameAreaMeal= itemView.findViewById(R.id.tvNameMeal);
            ivAreaMeal= itemView.findViewById(R.id.ivMeal);
        }
    }
}
