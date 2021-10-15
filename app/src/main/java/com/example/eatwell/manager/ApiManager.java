package com.example.eatwell.manager;

import androidx.lifecycle.MutableLiveData;

import com.example.eatwell.models.categories.Categories;
import com.example.eatwell.models.categories.CategoriesListRes;
import com.example.eatwell.models.categoryarea.Area;
import com.example.eatwell.models.categoryarea.AreaListRes;
import com.example.eatwell.models.meal.AreaMealListRes;
import com.example.eatwell.models.meal.MealListRes;
import com.example.eatwell.models.meal.Meals;
import com.example.eatwell.models.mealdetail.MealDetails;
import com.example.eatwell.models.mealdetail.MealDetailsListRes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static String url="https://www.themealdb.com";

    private Retrofit retrofit= new Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()).build();

    private ApiService service= retrofit.create(ApiService.class);

    //call to all category
    public void getCategory(MutableLiveData<ArrayList<Categories>> mutableLiveData){
        Call<CategoriesListRes> resCall=service.getCategoriesRes();
        resCall.enqueue(new Callback<CategoriesListRes>() {
            @Override
            public void onResponse(Call<CategoriesListRes> call, Response<CategoriesListRes> response) {
                CategoriesListRes categoriesListRes= response.body();
                if (categoriesListRes== null){
                    System.out.println("the data of all category is null");
                }
                ArrayList<Categories> categories=categoriesListRes.getCategoriesArrayList();
                mutableLiveData.postValue(categories);
            }

            @Override
            public void onFailure(Call<CategoriesListRes> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    //call to the meal in the category
    public void getMealStr(MutableLiveData<ArrayList<Meals>> mutableLiveData, String nameCategory){
        Call<MealListRes> mealListResCall= service.getMealListRes(nameCategory);
        mealListResCall.enqueue(new Callback<MealListRes>() {
            @Override
            public void onResponse(Call<MealListRes> call, Response<MealListRes> response) {
                MealListRes mealListRes= response.body();
                if (mealListRes== null){
                    System.out.println("the data to meal category is null");
                }
                ArrayList<Meals> meals= mealListRes.getAllMeals();
                mutableLiveData.postValue(meals);
            }
            @Override
            public void onFailure(Call<MealListRes> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    //call the meal detail
    public void getMealDetail(MutableLiveData<ArrayList<MealDetails>> detailMutableLiveData, String idMeal){
        Call<MealDetailsListRes> mealDetailsListResCall= service.getMealDetailsListRes(idMeal);
        mealDetailsListResCall.enqueue(new Callback<MealDetailsListRes>() {
            @Override
            public void onResponse(Call<MealDetailsListRes> call, Response<MealDetailsListRes> response) {
                MealDetailsListRes mealDetailsListRes= response.body();
                if (mealDetailsListRes== null){
                    System.out.println("the data for meal detail is null");
                }
                ArrayList<MealDetails>mealDetails=mealDetailsListRes.getMealDetails();
                detailMutableLiveData.postValue(mealDetails);
            }

            @Override
            public void onFailure(Call<MealDetailsListRes> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    //call to the name area
    public void getAreaList(MutableLiveData<ArrayList<Area>> areaListLiveData){
        Call<AreaListRes> areaListResCall= service.getAreaListRes();
        areaListResCall.enqueue(new Callback<AreaListRes>() {
            @Override
            public void onResponse(Call<AreaListRes> call, Response<AreaListRes> response) {
                AreaListRes areaListRes= response.body();
                if (areaListRes== null){
                    System.out.println("the data of area is null");
                }
                ArrayList<Area> areaArrayList= areaListRes.getAreaArrayList();
                areaListLiveData.postValue(areaArrayList);
            }

            @Override
            public void onFailure(Call<AreaListRes> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }
    //call to the meal in the sem area
    public void getAreaMealList(MutableLiveData<ArrayList<Meals>> areaMealLiveData, String areaName){
        Call<AreaMealListRes> areaMealListResCall= service.getAreaMealListRes(areaName);
        areaMealListResCall.enqueue(new Callback<AreaMealListRes>() {
            @Override
            public void onResponse(Call<AreaMealListRes> call, Response<AreaMealListRes> response) {
                AreaMealListRes areaMealListRes= response.body();
                if (areaMealListRes== null){
                }
                ArrayList<Meals> areaMealArrayList=areaMealListRes.getAreaMealsList();
                areaMealLiveData.postValue(areaMealArrayList);
            }

            @Override
            public void onFailure(Call<AreaMealListRes> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}
