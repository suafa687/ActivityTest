package com.example.activitytest.five

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.EfirstLayoutBinding

class FirstActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, FirstActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }
    private lateinit var firstLayoutBinding: EfirstLayoutBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        firstLayoutBinding = EfirstLayoutBinding.inflate(layoutInflater)
        setContentView(firstLayoutBinding.root)
    }
}