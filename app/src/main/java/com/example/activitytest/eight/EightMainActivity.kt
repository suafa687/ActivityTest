package com.example.activitytest.eight

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.R
import com.example.activitytest.databinding.GactivityEightMainBinding

class EightMainActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, EightMainActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }
    lateinit var gactive : GactivityEightMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        gactive = GactivityEightMainBinding.inflate(layoutInflater)
        setContentView(gactive.root)
    }
}