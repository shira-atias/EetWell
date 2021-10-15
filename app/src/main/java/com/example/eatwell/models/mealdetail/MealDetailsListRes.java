package com.example.eatwell.models.mealdetail;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MealDetailsListRes {

    @SerializedName("meals")
    private ArrayList<MealDetails> mealDetails;

    public MealDetailsListRes() {
    }

    public ArrayList<MealDetails> getMealDetails() {
        return mealDetails;
    }

    public void setMealDetails(ArrayList<MealDetails> mealDetails) {
        this.mealDetails = mealDetails;
    }

    @Override
    public String toString() {
        return "MealDetailsListRes{" +
                "mealDetails=" + mealDetails +
                '}';
    }
}
