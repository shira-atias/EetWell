package com.example.eatwell.models.meal;

import com.google.gson.annotations.SerializedName;

public class Meals {

    @SerializedName("strMeal")
    private String name;
    @SerializedName("strMealThumb")
    private String image;
    @SerializedName("idMeal")
    private int id;

    public Meals() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
