package com.example.activitytest.eleven.base

import com.example.activitytest.eleven.retrofit.ServiceCreator
import com.example.activitytest.eleven.retrofit.UserService
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun request(address: String): String {
    return suspendCoroutine { continuation ->
        HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
            override fun onFinish(response: String) {
                continuation.resume(response)
            }

            override fun onError(e: Exception) {
                continuation.resumeWithException(e)
            }
        })
    }
}

suspend fun <T> Call<T>.await(): T {
    return suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) continuation.resume(body)
                else continuation.resumeWithException(
                    RuntimeException("response body is null")
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

fun main() {
    runBlocking {
        getAppData()
    }
}

suspend fun getAppData() {
    try {
        val appList = ServiceCreator.create<UserService>().queryAllJson().await()
        // 对服务器响应的数据进行处理
    } catch (e: Exception) {
        // 对异常情况进行处理
    }
}