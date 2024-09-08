package com.example.activitytest.seven.base

import android.database.sqlite.SQLiteDatabase

class BookDataWithSql {
    fun insertData(db: SQLiteDatabase) {
        db.execSQL(
            "insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
            arrayOf("The Da Vinci Code", "Dan Brown", "454", "16.96")
        )
        db.execSQL(
            "insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
            arrayOf("The Lost Symbol", "Dan Brown", "510", "19.95")
        )
    }

    fun updateData(db: SQLiteDatabase) {
        db.execSQL("update Book set price = ? where name = ?", arrayOf("10.99", "The Da Vinci Code"))
    }

    fun deleteData(db: SQLiteDatabase) {
        db.execSQL("delete from Book where pages > ?", arrayOf("500"))
    }

    fun queryData(db: SQLiteDatabase) {
        val cursor = db.rawQuery("select * from Book", null)
    }
}