package com.example.activitytest.eleven.base

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}