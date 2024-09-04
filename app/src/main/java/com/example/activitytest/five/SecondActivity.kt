package com.example.activitytest.five

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.EsecondLayoutBinding

class SecondActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    private lateinit var secondLayoutBinding: EsecondLayoutBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        secondLayoutBinding = EsecondLayoutBinding.inflate(layoutInflater)
        setContentView(secondLayoutBinding.root)

    }
}