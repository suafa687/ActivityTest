package com.example.activitytest.four

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.R
import com.example.activitytest.BaseActivity

class EightActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, data: String) {
            val intent = Intent(context, EightActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.deight_layout)

    }
}