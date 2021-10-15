package com.example.eatwell.models.categories;

import com.google.gson.annotations.SerializedName;

public class Categories {
    @SerializedName("idCategory")
    private int id;
    @SerializedName("strCategory")
    private String nameCategories;
    @SerializedName("strCategoryThumb")
    private String imageCategories;

    public Categories() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategories() {
        return nameCategories;
    }

    public void setNameCategories(String nameCategories) {
        this.nameCategories = nameCategories;
    }

    public String getImageCategories() {
        return imageCategories;
    }

    public void setImageCategories(String imageCategories) {
        this.imageCategories = imageCategories;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", nameCategories='" + nameCategories + '\'' +
                ", imageCategories='" + imageCategories + '\'' +
                '}';
    }
}
