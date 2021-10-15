package com.example.eatwell.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eatwell.R;
import com.example.eatwell.models.mealdetail.MealDetails;
import com.example.eatwell.viewmodel.ApiViewModel;
import com.squareup.picasso.Picasso;

public class DetailMealFragment extends Fragment {
    private TextView tvMyRecipesHeader2;
    private TextView tvCategoriesHeader2;
    private TextView tvCountry;
    private TextView tvCategory;
    private TextView tvInstructions;
    private TextView tvIngredients;
    private TextView tvMeasure;
    private TextView tvRecipeName;
    private ImageView ivRecipe;
    private ImageView ivYouTube;
    private ApiViewModel aViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvMyRecipesHeader2= view.findViewById(R.id.tvMyRecipesHeader2);
        tvCategoriesHeader2= view.findViewById(R.id.tvCategoriesHeader2);
        tvRecipeName= view.findViewById(R.id.tvRecipeName);
        ivRecipe= view.findViewById(R.id.ivRecipe);
        ivYouTube= view.findViewById(R.id.ivYouTube);
        tvInstructions= view.findViewById(R.id.tvInstructions);
        tvIngredients=view.findViewById(R.id.tvIngredients);
        tvMeasure=view.findViewById(R.id.tvMeasure);
        tvCountry= view.findViewById(R.id.tvCountry);
        tvCategory= view.findViewById(R.id.tvCategory);

        // the title buttons
        tvCategoriesHeader2.setShadowLayer(3,5,6, Color.GRAY);
        tvCategoriesHeader2.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_detailMealFragment_to_categoriesFragment);
        });
        tvMyRecipesHeader2.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_detailMealFragment_to_myRecipesListFragment);
        });


        Bundle bag=getArguments();
        if (bag== null){
            System.out.println("bag= null");
        }
        String idMeal = bag.getString("idMeal");

        aViewModel= new ViewModelProvider(this).get(ApiViewModel.class);
        aViewModel.getMealDetailLiveData(idMeal).observe(getViewLifecycleOwner(), mealDetails -> {
            MealDetails mealDetailss;
            mealDetailss=mealDetails.get(0);
            tvRecipeName.setText(mealDetailss.getRecipeName());

            ivYouTube.setOnClickListener(v -> {
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
                if (!mealDetailss.getYoutub().isEmpty()){
                    intentYoutube.setData(Uri.parse(mealDetailss.getYoutub()));
                    startActivity(intentYoutube);
                }else {
                    Toast.makeText(getContext(), "The video does not exist", Toast.LENGTH_SHORT).show();
                }
            });

            tvCategory.setText(mealDetailss.getNameCategory());
            tvCategory.setOnClickListener(v -> {
                Bundle arg= new Bundle();
                arg.putString("nameCategory",mealDetailss.getNameCategory());
                Navigation.findNavController(v).navigate(R.id.action_detailMealFragment_to_mealsFragment,arg);

            });
            tvCountry.setText(mealDetailss.getNameArea());
            tvCountry.setOnClickListener(v -> {
                Bundle arg= new Bundle();
                arg.putString("nameAreaMeal",mealDetailss.getNameArea());
                Navigation.findNavController(v).navigate(R.id.action_detailMealFragment_to_areaMealFragment,arg);
            });

            tvInstructions.setText(mealDetailss.getInstruction());
            Picasso.get().load(mealDetailss.getImageRecipe()).into(ivRecipe);

            appendString(mealDetailss.getIngredient1());
            appendString(mealDetailss.getIngredient2());
            appendString(mealDetailss.getIngredient3());
            appendString(mealDetailss.getIngredient4());
            appendString(mealDetailss.getIngredient5());
            appendString(mealDetailss.getIngredient6());
            appendString(mealDetailss.getIngredient7());
            appendString(mealDetailss.getIngredient8());
            appendString(mealDetailss.getIngredient9());
            appendString(mealDetailss.getIngredient10());
            appendString(mealDetailss.getIngredient11());
            appendString(mealDetailss.getIngredient12());
            appendString(mealDetailss.getIngredient13());
            appendString(mealDetailss.getIngredient14());
            appendString(mealDetailss.getIngredient15());
            appendString(mealDetailss.getIngredient16());
            appendString(mealDetailss.getIngredient17());
            appendString(mealDetailss.getIngredient18());
            appendString(mealDetailss.getIngredient19());
            appendString(mealDetailss.getIngredient20());


            appendString1(mealDetailss.getMeasure1());
            appendString1(mealDetailss.getMeasure2());
            appendString1(mealDetailss.getMeasure3());
            appendString1(mealDetailss.getMeasure4());
            appendString1(mealDetailss.getMeasure5());
            appendString1(mealDetailss.getMeasure6());
            appendString1(mealDetailss.getMeasure7());
            appendString1(mealDetailss.getMeasure8());
            appendString1(mealDetailss.getMeasure9());
            appendString1(mealDetailss.getMeasure10());
            appendString1(mealDetailss.getMeasure11());
            appendString1(mealDetailss.getMeasure12());
            appendString1(mealDetailss.getMeasure13());
            appendString1(mealDetailss.getMeasure14());
            appendString1(mealDetailss.getMeasure15());
            appendString1(mealDetailss.getMeasure16());
            appendString1(mealDetailss.getMeasure17());
            appendString1(mealDetailss.getMeasure18());
            appendString1(mealDetailss.getMeasure19());
            appendString1(mealDetailss.getMeasure20());
        });

    }

        private void appendString(String name){
            if (name!=null && !name.equals("")){
                tvIngredients.append("\u2022 "+name+"\n ");
            }
        }
        private void appendString1(String name){
            if (name!=null && !name.equals("") && !name.equals(" ")) {
                tvMeasure.append(" : " + name+"\n");
            }
        }
}