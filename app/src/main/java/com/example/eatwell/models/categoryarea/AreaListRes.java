package com.example.eatwell.models.categoryarea;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreaListRes {
    @SerializedName("meals")
    ArrayList<Area> areaArrayList;

    public AreaListRes() {
    }

    public ArrayList<Area> getAreaArrayList() {
        return areaArrayList;
    }

    public void setAreaArrayList(ArrayList<Area> areaArrayList) {
        this.areaArrayList = areaArrayList;
    }

    @Override
    public String toString() {
        return "AreaListRes{" +
                "areaArrayList=" + areaArrayList +
                '}';
    }
}
