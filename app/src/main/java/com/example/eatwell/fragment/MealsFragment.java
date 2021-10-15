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
import android.widget.TextView;

import com.example.eatwell.R;
import com.example.eatwell.adapter.AdapterMeal;
import com.example.eatwell.viewmodel.ApiViewModel;

public class MealsFragment extends Fragment {
   private ApiViewModel aViewModel;
   private TextView tvCategoriesMeal;
   private TextView tvCategoriesHeader1;
   private TextView tvMyRecipesHeader1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCategoriesMeal= view.findViewById(R.id.tvCategoriesMeal);
        tvCategoriesHeader1= view.findViewById(R.id.tvCategoriesHeader1);
        tvMyRecipesHeader1= view.findViewById(R.id.tvMyRecipesHeader1);

        // the title buttons
        tvCategoriesHeader1.setShadowLayer(3,5,6, Color.GRAY);
        tvCategoriesHeader1.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_mealsFragment_to_categoriesFragment);
        });
        tvMyRecipesHeader1.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_mealsFragment_to_myRecipesListFragment);
        });


        aViewModel= new ViewModelProvider(this).get(ApiViewModel.class);
        Bundle bag=getArguments();
        if (bag==null)return;
        String name= bag.getString("nameCategory");
        tvCategoriesMeal.setText(name);

        aViewModel.getMealMutableLiveData(name).observe(getViewLifecycleOwner(),meals -> {
            RecyclerView rvMeal= view.findViewById(R.id.rvMeal);
            rvMeal.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMeal.setAdapter(new AdapterMeal(meals));
        });

    }
}