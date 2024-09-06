package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.databinding.ActivityMainBinding
import com.example.activitytest.five.FiveMainActivity
import com.example.activitytest.four.FourMainActivity
import com.example.activitytest.six.SixMainActivity

class MainActivity : BaseActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        // 第四章
        mainBinding.mbutton1.setOnClickListener{
            FourMainActivity.actionStart(this, "第四章")
        }

        // 第五章
        mainBinding.mbutton2.setOnClickListener{
            FiveMainActivity.actionStart(this, "第五章")
        }

        // 第六章
        mainBinding.mbutton3.setOnClickListener{
            SixMainActivity.actionStart(this, "第六章")
        }

        mainBinding.forceOffline.setOnClickListener {
            val intent = Intent("com.example.activitytest.FORCE_OFFLINE")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }
}