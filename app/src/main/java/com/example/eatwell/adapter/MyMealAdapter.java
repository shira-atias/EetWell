package com.example.eatwell.adapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eatwell.R;
import com.example.eatwell.models.MyRecipe;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MyMealAdapter extends RecyclerView.Adapter<MyMealAdapter.ViewHolder> {
    private List<MyRecipe> myRecipes;

    public MyMealAdapter(List<MyRecipe> myRecipes) {
        this.myRecipes = myRecipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_meals, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyRecipe myRecipe = myRecipes.get(position);
        holder.tvNameMeal.setText(myRecipe.getTitle());

        if (myRecipe.getImage()== null){
            holder.ivMeal.setImageResource(R.drawable.images_chef);
        }else {
            Picasso.get().load(myRecipe.getImage()).into(holder.ivMeal);
        }

        holder.itemView.setOnClickListener(v -> {
            Bundle arg= new Bundle();
            arg.putString("title", myRecipe.getTitle());
            arg.putString("instructions",myRecipe.getInstructions());
            arg.putString("ingredient",myRecipe.getIngredient());
            arg.putString("image",myRecipe.getImage());
            Navigation.findNavController(v).navigate(R.id.action_myRecipesListFragment_to_myRecipeDetailFragment,arg);
        });
    }

    @Override
    public int getItemCount() {
        return myRecipes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameMeal;
        ImageView ivMeal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMeal = itemView.findViewById(R.id.tvNameMeal);
            ivMeal= itemView.findViewById(R.id.ivMeal);
        }
    }
}
