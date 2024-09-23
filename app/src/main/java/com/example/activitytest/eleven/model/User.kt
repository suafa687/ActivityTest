package com.example.activitytest.eleven.model

import com.google.gson.Gson

class User(
    var userId: Int,
    var username: String,
    var nickname: String,
    var birthday: String,
    var sex: Int,
    var enabled: Int
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}