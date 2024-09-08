package com.example.activitytest.seven

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.FactivitySevenMainBinding
import com.example.activitytest.seven.base.Book
import com.example.activitytest.seven.base.MyDatabaseHelper
import com.example.activitytest.seven.base.open
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class SevenMainActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, SevenMainActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var sevenMainBinding: FactivitySevenMainBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sevenMainBinding = FactivitySevenMainBinding.inflate(layoutInflater)
        setContentView(sevenMainBinding.root)

        val inputText = load()
        if (inputText.isNotEmpty()) {
            sevenMainBinding.editText71.setText(inputText)
            sevenMainBinding.editText71.setSelection(inputText.length)
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }

        sevenMainBinding.mbutton73.setOnClickListener {
            val text = sevenMainBinding.editText72.text
            getSharedPreferences("data", Context.MODE_PRIVATE).open {
                putString("name", text.toString())
                putInt("age", 28)
                putBoolean("married", false)
            }

            sevenMainBinding.editText72.setText("")
        }
        sevenMainBinding.mbutton74.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            sevenMainBinding.editText72.setText("name is $name " + " age is $age " + " married is $married")
        }

        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 5)
        sevenMainBinding.createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        sevenMainBinding.addDate.setOnClickListener {
            val db = dbHelper.writableDatabase
            val book1 = Book("The Da Vinci Code", "Dan Brown", 454, 16.96)
            book1.insert(db) // 插入第一条数据
            val book2 = Book("The Lost Symbol", "Dan Brown", 510, 19.95)
            book2.insert(db) // 插入第二条数据
        }

        sevenMainBinding.updateDate.setOnClickListener {
            val db = dbHelper.writableDatabase
            val books = Book.query(db, "The Da Vinci Code")
            books.forEach({ v ->
                v.price = 10.99
                v.update(db)
            })
        }
        var flag = false
        sevenMainBinding.deleteDate.setOnClickListener {
            val db = dbHelper.writableDatabase
            if (flag) {
                val books = Book.query(db, "The Da Vinci Code")
                books.forEach({ v -> v.delete(db) })
                flag = false
            } else {
                val books = Book.query(db, "The Lost Symbol")
                books.forEach({ v -> v.delete(db) })
                flag = true
            }
        }
        sevenMainBinding.queryDate.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询Book表中所有的数据
            val books = Book.query(db, null)
            var text: String = ""
            books.forEach({ v -> text = text.plus(v.toString()) })
            sevenMainBinding.editText72.setText(text)
        }
        var transactionFlag = false
        sevenMainBinding.replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() // 开启事务
            try {
                db.delete("Book", null, null)
                if (!transactionFlag) {
                    // 手动抛出一个异常，让事务失败
                    transactionFlag = true
                    throw NullPointerException()
                }
                val values = Book("Game of Thrones", "George Martin", 720, 20.85)
                db.insert("Book", null, values.getContentValues())
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = sevenMainBinding.editText71.text.toString()
        save(inputText)
    }

    private fun save(inputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }


}