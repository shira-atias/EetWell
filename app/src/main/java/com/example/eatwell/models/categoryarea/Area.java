package com.example.eatwell.models.categoryarea;

import com.google.gson.annotations.SerializedName;

public class Area {
    @SerializedName("strArea")
    private String areaName;

    public Area() {
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaName='" + areaName + '\'' +
                '}';
    }
}
