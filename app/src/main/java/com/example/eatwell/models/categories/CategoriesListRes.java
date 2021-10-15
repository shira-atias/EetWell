package com.example.eatwell.models.categories;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoriesListRes {
    @SerializedName("categories")
    ArrayList<Categories> categoriesArrayList;

    public CategoriesListRes() {
    }

    public ArrayList<Categories> getCategoriesArrayList() {
        return categoriesArrayList;
    }

    public void setCategoriesArrayList(ArrayList<Categories> categoriesArrayList) {
        this.categoriesArrayList = categoriesArrayList;
    }

    @Override
    public String toString() {
        return "CategoriesListRes{" +
                "categoriesArrayList=" + categoriesArrayList +
                '}';
    }
}
