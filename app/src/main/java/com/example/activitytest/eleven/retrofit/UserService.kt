package com.example.activitytest.eleven.retrofit

import com.example.activitytest.eleven.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    // 静态header
    @Headers("User-Agent: okhttp", "Cache-Control: max-age=0")
    @GET("queryAllJson")
    fun queryAllJson(): Call<List<User>>

    // 动态header
    @GET("queryAllJson")
    fun queryAllJson(@Header("User-Agent") userAgent: String,
                     @Header("Cache-Control") cacheControl: String): Call<List<User>>

    @GET("queryOneJson/{id}")
    fun queryOneJson(@Path("id") id: Int): Call<User>

    @GET("queryOneJsonWithPar")
    fun queryOneJsonWithPar(@Query("id") id: Int): Call<User>

    @POST("insertData")
    fun insertData(@Body user: User): Call<ResponseBody>

}