package com.example.eatwell.models.meal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreaMealListRes {
    @SerializedName("meals")
    ArrayList<Meals> areaMealsList;

    public AreaMealListRes() {
    }

    public ArrayList<Meals> getAreaMealsList() {
        return areaMealsList;
    }

    public void setAreaMealsList(ArrayList<Meals> areaMealsList) {
        this.areaMealsList = areaMealsList;
    }

    @Override
    public String toString() {
        return "AreaMealListRes{" +
                "areaMealsList=" + areaMealsList +
                '}';
    }
}
