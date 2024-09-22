package com.example.activitytest.eleven

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.JActivityWebViewBinding

class WebViewActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var webViewBind:JActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        webViewBind = JActivityWebViewBinding.inflate(layoutInflater)
        setContentView(webViewBind.root)

        webViewBind.webView.settings.javaScriptEnabled=true
        webViewBind.webView.webViewClient = WebViewClient()
        webViewBind.webView.loadUrl("https://www.baidu.com")
    }
}