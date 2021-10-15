package com.example.eatwell.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eatwell.R;
import com.example.eatwell.models.categoryarea.Area;
import java.util.ArrayList;


public class AdapterArea extends RecyclerView.Adapter<AdapterArea.ViewHolder> {
    private ArrayList<Area> arrayList;


    public AdapterArea(ArrayList<Area> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v= inflater.inflate(R.layout.item_category_area,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Area area= arrayList.get(position);
        holder.tvArea.setText("* "+ area.getAreaName());
        holder.itemView.setOnClickListener(v -> {

            Bundle arg= new Bundle();
            arg.putString("nameAreaMeal",area.getAreaName());
            Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_areaMealFragment,arg);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvArea;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvArea= itemView.findViewById(R.id.tvAreaCategory);
        }
    }
}
