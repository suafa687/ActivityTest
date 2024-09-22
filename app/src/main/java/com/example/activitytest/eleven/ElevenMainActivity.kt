package com.example.activitytest.eleven

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.JElevenActivityBinding
import com.example.activitytest.ten.TenMainActivity

class ElevenMainActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, ElevenMainActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var elevenBind: JElevenActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        elevenBind = JElevenActivityBinding.inflate(layoutInflater)
        setContentView(elevenBind.root)

        elevenBind.webClientTextBtn.setOnClickListener{
            WebViewActivity.actionStart(this, "webClient")
        }

        elevenBind.httpBtn.setOnClickListener{
            HttpActivity.actionStart(this, "http")
        }

        elevenBind.okHttpBtn.setOnClickListener{
            OkHttpActivity.actionStart(this,"okhttp")
        }
    }
}