package com.example.activitytest.thirteen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.LLifecyclesActivityBinding
import com.example.activitytest.thirteen.base.MyObserver

class LifecyclesActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, LifecyclesActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }
    lateinit var lLifecyclesActivityBinding: LLifecyclesActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lLifecyclesActivityBinding = LLifecyclesActivityBinding.inflate(layoutInflater)
        setContentView(lLifecyclesActivityBinding.root)
        lifecycle.addObserver(MyObserver(lifecycle))
    }
}