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
import com.example.eatwell.adapter.AdapterAreaMeal;
import com.example.eatwell.viewmodel.ApiViewModel;

public class AreaMealFragment extends Fragment {
   private TextView tvCategoriesArea;
   private TextView tvCategoriesHeader3;
   private TextView tvMyRecipesHeader3;
   private ApiViewModel aViewModel;
   private RecyclerView rvAreaMeal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_area_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvCategoriesArea= view.findViewById(R.id.tvCategoriesArea);
        tvMyRecipesHeader3 = view.findViewById(R.id.tvMyRecipesHeader3);
        tvCategoriesHeader3 = view.findViewById(R.id.tvCategoriesHeader3);

        // the title buttons
        tvCategoriesHeader3.setShadowLayer(3,5,6, Color.GRAY);
        tvCategoriesHeader3.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_areaMealFragment_to_categoriesFragment);
        });
        tvMyRecipesHeader3.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_areaMealFragment_to_myRecipesListFragment);
        });


        Bundle bag= getArguments();
        if (bag== null)return;
        String name= bag.getString("nameAreaMeal");
        tvCategoriesArea.setText(name);
        aViewModel= new ViewModelProvider(this).get(ApiViewModel.class);
        aViewModel.getAreaMealLiveData(name).observe(getViewLifecycleOwner(),areaMeals -> {
            rvAreaMeal =view.findViewById(R.id.rvAreaMeal);
            rvAreaMeal.setLayoutManager(new LinearLayoutManager(getContext()));
            rvAreaMeal.setAdapter(new AdapterAreaMeal(areaMeals,this));
        });
    }
}