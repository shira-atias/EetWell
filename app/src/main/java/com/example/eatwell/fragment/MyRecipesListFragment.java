package com.example.eatwell.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatwell.R;
import com.example.eatwell.adapter.MyMealAdapter;
import com.example.eatwell.viewmodel.MyRecipeViewModel;
import com.example.eatwell.viewmodel.ReadViewModel;

public class MyRecipesListFragment extends Fragment {
   private ReadViewModel rViewModel;
    private RecyclerView rvMyRecipeList;
    private ImageView ivPlus;
    private TextView tvAddRecipes;
    private TextView tvCategoriesHeader4;
    private TextView tvMyRecipesHeader4;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_recipes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivPlus= view.findViewById(R.id.ivPlus);
        tvAddRecipes= view.findViewById(R.id.tvAddRecipes);
        tvMyRecipesHeader4= view.findViewById(R.id.tvMyRecipesHeader4);
        tvCategoriesHeader4= view.findViewById(R.id.tvCategoriesHeader4);

        // the title buttons
        tvCategoriesHeader4.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_myRecipesListFragment_to_categoriesFragment);
        });
        tvMyRecipesHeader4.setShadowLayer(3,5,6, Color.GRAY);


        tvAddRecipes.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_myRecipesListFragment_to_myRecipeFragment);
        });
        ivPlus.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_myRecipesListFragment_to_myRecipeFragment);
        });

        rvMyRecipeList= view.findViewById(R.id.rvMyRecipeList);
        rViewModel= new ViewModelProvider(getActivity()).get(ReadViewModel.class);
        rViewModel.getMyMealLiveData().observe(getViewLifecycleOwner(),myMeals -> {
            rvMyRecipeList.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMyRecipeList.setAdapter(new MyMealAdapter(myMeals));
        });

    }
}