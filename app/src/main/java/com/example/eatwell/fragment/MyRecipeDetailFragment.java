package com.example.eatwell.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatwell.R;
import com.example.eatwell.activity.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MyRecipeDetailFragment extends Fragment {
   private TextView tvMyRecipeName;
   private TextView tvMyIngredients;
   private TextView tvMyInstructions;
   private TextView tvCategoriesHeader6;
   private TextView tvMyRecipesHeader6;
   private ImageView ivImageMyRecipe;
   private ImageView ivEditImage;
   private ImageView ivDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_recipe_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvMyRecipeName= view.findViewById(R.id.tvMyRecipeName);
        tvMyIngredients= view.findViewById(R.id.tvMyIngredients);
        tvMyInstructions= view.findViewById(R.id.tvMyInstructions);
        ivImageMyRecipe= view.findViewById(R.id.ivImageMyRecipe);
        ivEditImage= view.findViewById(R.id.ivEditImage);
        ivDelete = view.findViewById(R.id.ivDelete);
        tvMyRecipesHeader6 = view.findViewById(R.id.tvMyRecipesHeader6);
        tvCategoriesHeader6 = view.findViewById(R.id.tvCategoriesHeader6);

        // the title buttons
        tvCategoriesHeader6.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_myRecipeDetailFragment_to_categoriesFragment);
        });
        tvMyRecipesHeader6.setShadowLayer(3,5,6, Color.GRAY);
        tvMyRecipesHeader6.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_myRecipeDetailFragment_to_myRecipesListFragment3);
        });


        Bundle bag = getArguments();
        if (bag == null) return;
        String title = bag.getString("title");
        String instructions = bag.getString("instructions");
        String ingredient = bag.getString("ingredient");
        String image= bag.getString("image");

        if (image == null){
            ivImageMyRecipe.setImageResource(R.drawable.images_chef);
        }else {
            Picasso.get().load(image).into(ivImageMyRecipe);
        }

        tvMyRecipeName.setText(title);
        tvMyIngredients.setText(ingredient);
        tvMyInstructions.setText(instructions);

        //save the data and go to edit fragment
        ivEditImage.setOnClickListener(v -> {
            Bundle arg= new Bundle();
            arg.putString("titleEdit", title);
            arg.putString("instructionsEdit",instructions);
            arg.putString("ingredientEdit",ingredient);
            arg.putString("imageEdit",image);
            Navigation.findNavController(v).navigate(R.id.action_myRecipeDetailFragment_to_myRecipeEditFragment,arg);
        });

        //delete the data
        ivDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("you sure want to delete?")
                    .setTitle("Delete").setIcon(R.drawable.delete)
                    .setPositiveButton("Delete", (dialog, id) -> {

                        DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child(MainActivity.stringEmail);
                        firebaseDatabase.child(title).removeValue();
                        Navigation.findNavController(v).navigate(R.id.action_myRecipeDetailFragment_to_myRecipesListFragment);
                    })
                    .setNegativeButton("Cancel", (dialog, id) -> { });
            AlertDialog dialog = builder.create();
            dialog.show();


        });
    }
}