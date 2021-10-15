package com.example.eatwell.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eatwell.manager.ApiManager;
import com.example.eatwell.models.categories.Categories;
import com.example.eatwell.models.categoryarea.Area;
import com.example.eatwell.models.meal.Meals;
import com.example.eatwell.models.mealdetail.MealDetails;

import java.util.ArrayList;

public class ApiViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Categories>> mutableLiveData= new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Meals>> mealMutableLiveData= new MutableLiveData<>();
    private final MutableLiveData<ArrayList<MealDetails>> mealDetailLiveData= new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Area>> areaLiveData= new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Meals>> areaMealLiveData= new MutableLiveData<>();
    private ApiManager manager;

    public ApiViewModel() {
        manager= new ApiManager();
        manager.getCategory(mutableLiveData);

    }
    //category food
    public MutableLiveData<ArrayList<Categories>> getMutableLiveDataCategory() {
        return mutableLiveData;
    }
    //category meals
    public MutableLiveData<ArrayList<Meals>> getMealMutableLiveData(String nameCategory) {
        manager.getMealStr(mealMutableLiveData,nameCategory);
        return mealMutableLiveData;
    }
    //meal detail
    public MutableLiveData<ArrayList<MealDetails>> getMealDetailLiveData(String idMeal) {
        manager.getMealDetail(mealDetailLiveData,idMeal);
        return mealDetailLiveData;
    }
    //area name
    public MutableLiveData<ArrayList<Area>> getAreaLiveData() {
        manager.getAreaList(areaLiveData);
        return areaLiveData;
    }
    //area meal
    public MutableLiveData<ArrayList<Meals>> getAreaMealLiveData(String nameMeal) {
        manager.getAreaMealList(areaMealLiveData,nameMeal);
        return areaMealLiveData;
    }
}
