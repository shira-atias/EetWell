package com.example.eatwell.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eatwell.R;
import com.example.eatwell.activity.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MyRecipeEditFragment extends Fragment {
   private EditText etEditRecipeName;
   private EditText etEditMyIngredient;
   private EditText etEditMyInstructions;
   private ImageView ivEditMyRecipe;
   private Button btnEditSave;
   private TextView tvCategoriesHeader7;
   private TextView tvMyRecipesHeader7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_recipe_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etEditRecipeName = view.findViewById(R.id.etEditRecipeName);
        etEditMyIngredient = view.findViewById(R.id.etEditMyIngredient);
        etEditMyInstructions = view.findViewById(R.id.etEditMyInstructions);
        ivEditMyRecipe = view.findViewById(R.id.ivEditMyRecipe);
        btnEditSave = view.findViewById(R.id.btnEditSave);
        tvMyRecipesHeader7 = view.findViewById(R.id.tvMyRecipesHeader7);
        tvCategoriesHeader7 = view.findViewById(R.id.tvCategoriesHeader7);



        Bundle bag = getArguments();
        if (bag == null) return;
        String title = bag.getString("titleEdit");
        String instructions = bag.getString("instructionsEdit");
        String ingredient = bag.getString("ingredientEdit");
        String image= bag.getString("imageEdit");
        if (image == null){
            ivEditMyRecipe.setImageResource(R.drawable.images_chef);
        }else {
            Picasso.get().load(image).into(ivEditMyRecipe);
        }

        // the title buttons
        tvCategoriesHeader7.setOnClickListener(v -> {
            openDialog("save","Before you leave would you like to save your changes?",title,v,image);
        });
        tvMyRecipesHeader7.setShadowLayer(3,5,6, Color.GRAY);
        tvMyRecipesHeader7.setOnClickListener(v -> {
            openDialog("save","Before you leave would you like to save your changes?",title,v,image);
        });



        etEditRecipeName.setText(title);
        etEditMyIngredient.setText(ingredient);
        etEditMyInstructions.setText(instructions);

        btnEditSave.setOnClickListener(v -> {
         saveDetail(title,v,image);
        });
    }

    public void openDialog(String textT,String textM,String title,View v,String image){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(textM)
                .setTitle(textT)
                .setPositiveButton("save", (dialog, id) -> {
                    saveDetail(title,v,image);
                })
                .setNegativeButton("Cancel", (dialog, id) -> { });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void saveDetail(String title, View v,String image){
        String newTitle = etEditRecipeName.getText().toString();
        String newInstructions = etEditMyInstructions.getText().toString();
        String newIngredients = etEditMyIngredient.getText().toString();

        if (newTitle.isEmpty()){
            etEditRecipeName.setHint("enter the name");
            etEditRecipeName.setHintTextColor(Color.RED);
        }else {
            DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
            DatabaseReference mealRef = firebaseDatabase.child(MainActivity.stringEmail).child(title);
            mealRef.child("id").get().addOnSuccessListener(command -> {
                mealRef.removeValue().addOnSuccessListener(command1 -> {
                    DatabaseReference newRef = firebaseDatabase.child(MainActivity.stringEmail).child(newTitle);
                    newRef.child("title").setValue(newTitle);
                    newRef.child("id").setValue(command.getValue());
                    newRef.child("ingredient").setValue(newIngredients);
                    newRef.child("instructions").setValue(newInstructions);
                    newRef.child("image").setValue(image);
                });
            });
            Navigation.findNavController(v).navigate(R.id.action_myRecipeEditFragment_to_myRecipesListFragment);
        }
    }
}