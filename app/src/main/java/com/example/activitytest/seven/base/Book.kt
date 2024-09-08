package com.example.activitytest.seven.base

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Book(
    var name: String,
    var author: String,
    var pages: Int,
    var price: Double
) {
    @SuppressLint("Range")
    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex("name")),
        cursor.getString(cursor.getColumnIndex("author")),
        cursor.getInt(cursor.getColumnIndex("pages")),
        cursor.getDouble(cursor.getColumnIndex("price"))
    )

    companion object {
        fun createSql(): String = "create table Book (" +
                " id integer primary key autoincrement," +
                "author text," +
                "price real," +
                "pages integer," +
                "name text," +
                "category_id integer)"

        fun dropSql(): String = "drop table if exists Book"

        fun query(db: SQLiteDatabase, name: String? = null): ArrayList<Book> {
            var cursor: Cursor
            if (name == null) {
                cursor = db.query("Book", null, null, null, null, null, null)
            } else {
                cursor = db.query("Book", null, "name = ?", arrayOf(name), null, null, null)
            }
            var books = ArrayList<Book>()
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    books.add(Book(cursor))
                } while (cursor.moveToNext())
            }
            cursor.close()
            return books
        }
    }

    fun getContentValues(): ContentValues =
        ContentValues().apply {
            // 开始组装第一条数据
            put("name", name)
            put("author", author)
            put("pages", pages)
            put("price", price)
        }


    override fun toString(): String = StringBuilder().build {
        append("book name is $name\n")
        append("book author is $author\n")
        append("book pages is $pages\n")
        append("book price is $price\n")
    }.toString()

    fun insert(db: SQLiteDatabase) =
        db.insert("Book", null, this.getContentValues()) // 插入第一条数据


    fun update(db: SQLiteDatabase) =
        db.update("Book", getContentValues(), "name = ?", arrayOf(name))

    fun delete(db: SQLiteDatabase) =
        db.delete("Book", "name =  ?", arrayOf(name))
}