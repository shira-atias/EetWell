package com.example.eatwell.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eatwell.R;
import com.example.eatwell.adapter.AdapterArea;
import com.example.eatwell.adapter.AdapterCategory;
import com.example.eatwell.models.categoryarea.Area;
import com.example.eatwell.viewmodel.ApiViewModel;

public class CategoriesFragment extends Fragment {
    private RecyclerView rvArea;
    private RecyclerView rvCategory;
    private ApiViewModel aViewModel;
    private TextView tvCategoriesHeader;
    private TextView tvMyRecipesHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        rvCategory = root.findViewById(R.id.rvCategory);
        rvArea = root.findViewById(R.id.rvArea);
        aViewModel = new ViewModelProvider(this).get(ApiViewModel.class);

        //recycler view of the category
        aViewModel.getMutableLiveDataCategory().observe(getViewLifecycleOwner(), categories -> {
            rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
            rvCategory.setAdapter(new AdapterCategory(categories));
        });
        //recycler view of the area category
        aViewModel.getAreaLiveData().observe(getViewLifecycleOwner(), areas -> {
            System.err.println(areas.size());
            for (Area area : areas) {
                System.err.println(area.getAreaName());
            }
            rvArea.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            rvArea.setAdapter(new AdapterArea(areas));
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //the title buttons
        tvCategoriesHeader = view.findViewById(R.id.tvCategoriesHeader);
        tvCategoriesHeader.setShadowLayer(3, 5, 6, Color.GRAY);

        tvMyRecipesHeader = view.findViewById(R.id.tvMyRecipesHeader);
        tvMyRecipesHeader.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_categoriesFragment_to_myRecipesListFragment);
        });

    }
}