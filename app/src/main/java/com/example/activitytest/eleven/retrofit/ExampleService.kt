package com.example.activitytest.eleven.retrofit

import com.example.activitytest.eleven.model.Data
import retrofit2.Call
import retrofit2.http.GET

interface ExampleService {

    @GET("get_data.json")
    fun getData(): Call<Data>

}