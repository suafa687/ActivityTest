package com.example.activitytest.seven

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.FactivitySevenMainBinding
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