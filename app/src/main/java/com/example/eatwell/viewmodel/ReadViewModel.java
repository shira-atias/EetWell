package com.example.eatwell.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eatwell.activity.MainActivity;
import com.example.eatwell.models.MyRecipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadViewModel extends ViewModel {
    MutableLiveData<List<MyRecipe>> myMealLiveData = new MutableLiveData<>();

    public ReadViewModel() {
    }

    public MutableLiveData<List<MyRecipe>> getMyMealLiveData() {
        ArrayList<MyRecipe> myMealsList = new ArrayList<>();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(MainActivity.stringEmail);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myMealsList.clear();
                for (DataSnapshot row : snapshot.getChildren()) {
                    MyRecipe m = row.getValue(MyRecipe.class);
                    if(m != null) {
                        System.err.println(m.getTitle());
                        myMealsList.add(m);
                    }
                }
                myMealLiveData.postValue(myMealsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println();
            }
        });
        return myMealLiveData;
    }
}
