package com.example.eatwell.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatwell.DialogProgress;
import com.example.eatwell.R;
import com.example.eatwell.viewmodel.MyRecipeViewModel;

import static android.app.Activity.RESULT_OK;

public class MyRecipeFragment extends Fragment {
    public static final int IMAGE_PICKER_REQUEST = 1;
    private MyRecipeViewModel mRViewModel= new MyRecipeViewModel();
    private EditText etRecipeName, etMyInstructions,  etMyIngredient;
    private Button btnSave;
    private ImageView ivMyRecipe;
    private TextView tvCategoriesHeader5;
    private TextView tvMyRecipesHeader5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etRecipeName= view.findViewById(R.id.etRecipeName);
        etMyInstructions= view.findViewById(R.id.etMyInstructions);
        etMyIngredient= view.findViewById(R.id.etMyIngredient);
        ivMyRecipe= view.findViewById(R.id.ivMyRecipe);
        btnSave= view.findViewById(R.id.btnSave);
        tvMyRecipesHeader5= view.findViewById(R.id.tvMyRecipesHeader5);
        tvCategoriesHeader5= view.findViewById(R.id.tvCategoriesHeader5);

        // the title buttons
        tvCategoriesHeader5.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_myRecipeFragment_to_categoriesFragment);
        });
        tvMyRecipesHeader5.setShadowLayer(3,5,6, Color.GRAY);
        tvMyRecipesHeader5.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_myRecipeFragment_to_myRecipesListFragment2);
        });

        //open and save image
        ivMyRecipe.setImageResource(R.drawable.images_chef);
        ivMyRecipe.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, IMAGE_PICKER_REQUEST);
        });

        //save all data
        btnSave.setOnClickListener(v -> {
            if (etRecipeName== null){
                etRecipeName.setHint("enter the name");
                etRecipeName.setHintTextColor(Color.RED);
            }else {

                String title = etRecipeName.getText().toString();
                String instructions = etMyInstructions.getText().toString();
                String ingredient = etMyIngredient.getText().toString();
                if (title.isEmpty()) {
                    etRecipeName.setHint("enter name recipe");
                    etRecipeName.setHintTextColor(Color.RED);
                } else {
                    DialogProgress.getDialogProgress(true, getContext());
                    mRViewModel.addMyMeal(title, instructions, ingredient);
                }

                mRViewModel.isDone.observe(getViewLifecycleOwner(), aBoolean -> {
                    etRecipeName.setHint("");
                    etRecipeName.setText("");
                    etMyIngredient.setText("");
                    etMyInstructions.setText("");
                    ivMyRecipe.setImageResource(R.drawable.images_chef);
                    DialogProgress.getDialogProgress(false, getContext());

                    NavHostFragment.findNavController(MyRecipeFragment.this)
                            .navigate(R.id.action_myRecipeFragment_to_myRecipesListFragment);
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICKER_REQUEST && resultCode == RESULT_OK && data != null){
            Uri uri= data.getData();
            String nameImage= etRecipeName.getText().toString();
            mRViewModel.saveImageToStorage(uri,nameImage,getContext(),ivMyRecipe);

        }
    }
}