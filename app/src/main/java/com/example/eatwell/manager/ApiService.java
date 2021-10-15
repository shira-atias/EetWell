package com.example.eatwell.manager;

import com.example.eatwell.models.categories.CategoriesListRes;
import com.example.eatwell.models.categoryarea.AreaListRes;
import com.example.eatwell.models.meal.AreaMealListRes;
import com.example.eatwell.models.meal.MealListRes;
import com.example.eatwell.models.mealdetail.MealDetailsListRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //list all the category food
    @GET("/api/json/v1/1/categories.php")
    Call<CategoriesListRes> getCategoriesRes();
    //list of meals in the sem category
    @GET("/api/json/v1/1/filter.php")
    Call<MealListRes>getMealListRes(@Query("c") String nameMeal);
    //meal detail
    @GET("/api/json/v1/1/lookup.php")
    Call<MealDetailsListRes> getMealDetailsListRes(@Query("i") String idMeal);
    //area list
    @GET("/api/json/v1/1/list.php?a=list")
    Call<AreaListRes> getAreaListRes();
    //meals area
    @GET("/api/json/v1/1/filter.php")
    Call<AreaMealListRes> getAreaMealListRes(@Query("a") String area);

}
