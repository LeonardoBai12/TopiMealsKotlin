package com.example.topimealskotlin.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.topimealskotlin.R
import com.example.topimealskotlin.model.meal.MealConstants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofitInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(MealConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    fun providesRequestOptions() : RequestOptions{
        return RequestOptions().placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    fun providesGlideInstance(application: Application, requestOptions: RequestOptions) : RequestManager{
        return Glide.with(application).setDefaultRequestOptions(requestOptions)
    }

}