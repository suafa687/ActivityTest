package com.example.activitytest.four

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.R
import com.example.activitytest.BaseActivity

class FiveActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, FiveActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.dfive_layout)

    }
}