package com.example.topimealskotlin.model.meal

import com.google.gson.annotations.SerializedName

class EnvelopeMeal {
    @SerializedName("meals")
    private lateinit var mealList: List<Meal>

    fun getMealList(): List<Meal> {
        return mealList
    }

    fun setMealList(mealList: List<Meal>) {
        this.mealList = mealList
    }
}