package com.example.topimealskotlin.network

import com.example.topimealskotlin.model.EnvelopeMeal
import com.example.topimealskotlin.model.Meal
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealRepository {
    @GET("api/json/v1/1/search.php")
    suspend fun getMeals(@Query("s") filter: String): Response<EnvelopeMeal>
}
