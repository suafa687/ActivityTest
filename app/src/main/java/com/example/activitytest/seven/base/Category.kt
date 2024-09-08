package com.example.activitytest.seven.base

import android.content.ContentValues

class Category(
    var category_name: String,
    var category_code: Int
) {

    companion object {
        fun createSql(): String = "create table Category (" +
                "id integer primary key autoincrement," +
                "category_name text," +
                "category_code integer)"

        fun dropSql(): String = "drop table if exists Category"
    }

    fun getContentValues(): ContentValues =
        ContentValues().apply {
            // 开始组装第一条数据
            put("category_name", category_name)
            put("category_code", category_code)
        }
}