package com.example.eatwell.models.meal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MealListRes {
    @SerializedName("meals")
    ArrayList<Meals> allMeals;

    public MealListRes() {
    }

    public ArrayList<Meals> getAllMeals() {
        return allMeals;
    }

    public void setAllMeals(ArrayList<Meals> allMeals) {
        this.allMeals = allMeals;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "allMeals=" + allMeals +
                '}';
    }
}
