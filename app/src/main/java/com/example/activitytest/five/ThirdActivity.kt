package com.example.activitytest.five

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.EthirdLayoutBinding

class ThirdActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, ThirdActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    private lateinit var thirdLayoutBinding: EthirdLayoutBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        thirdLayoutBinding = EthirdLayoutBinding.inflate(layoutInflater)
        setContentView(thirdLayoutBinding.root)

    }
}