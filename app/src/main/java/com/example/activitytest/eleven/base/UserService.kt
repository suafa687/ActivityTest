package com.example.activitytest.eleven.base

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("queryAllJson")
    fun getUserData(): Call<List<User>>
}