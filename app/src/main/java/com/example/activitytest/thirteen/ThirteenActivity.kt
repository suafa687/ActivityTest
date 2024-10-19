package com.example.activitytest.thirteen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.LThirteenActivityBinding

class ThirteenActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, ThirteenActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var lThirteenActivityBinding: LThirteenActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lThirteenActivityBinding = LThirteenActivityBinding.inflate(layoutInflater)
        setContentView(lThirteenActivityBinding.root)
        lThirteenActivityBinding.barBtn1.setOnClickListener {
            ViewModelActivity.actionStart(this, "ViewModel")
        }
        lThirteenActivityBinding.barBtn2.setOnClickListener {
            LifecyclesActivity.actionStart(this, "ViewModel")
        }
    }

}