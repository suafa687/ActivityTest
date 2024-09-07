package com.example.activitytest.seven

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.FactivitySevenMainBinding
import com.example.activitytest.seven.base.MyDatabaseHelper
import com.example.activitytest.six.ktl.build
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

        sevenMainBinding.mbutton73.setOnClickListener{
            val text = sevenMainBinding.editText72.text
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("name", text.toString())
            editor.putInt("age", 28)
            editor.putBoolean("married", false)
            editor.apply()
            sevenMainBinding.editText72.setText("")
        }
        sevenMainBinding.mbutton74.setOnClickListener{
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

        sevenMainBinding.addDate.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入第二条数据
        }

        sevenMainBinding.updateDate.setOnClickListener{
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        sevenMainBinding.deleteDate.setOnClickListener{
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }
        sevenMainBinding.queryDate.setOnClickListener{
            val db = dbHelper.writableDatabase
            // 查询Book表中所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                var text : String = ""
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    val result = StringBuilder().build {
                        append("book name is $name\n")
                        append("book author is $author\n")
                        append("book pages is $pages\n")
                        append("book price is $price\n")
                    }
                    text = text.plus(result)
                } while (cursor.moveToNext())
                sevenMainBinding.editText72.setText(text)
            }
            cursor.close()
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

    fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
        block()
        return this
    }
}