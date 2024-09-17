package com.example.activitytest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.databinding.ActivityMainBinding
import com.example.activitytest.eight.EightMainActivity
import com.example.activitytest.five.FiveMainActivity
import com.example.activitytest.four.FourMainActivity
import com.example.activitytest.nine.NineActivity
import com.example.activitytest.seven.SevenMainActivity
import com.example.activitytest.six.SixMainActivity

class MainActivity : BaseActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.forceOffline.setOnClickListener {
            val intent = Intent("com.example.activitytest.FORCE_OFFLINE")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
        // 第四章
        mainBinding.mbutton1.setOnClickListener {
            FourMainActivity.actionStart(this, "第四章")
        }

        // 第五章
        mainBinding.mbutton2.setOnClickListener {
            FiveMainActivity.actionStart(this, "第五章")
        }

        // 第六章
        mainBinding.mbutton3.setOnClickListener {
            SixMainActivity.actionStart(this, "第六章")
        }
        // 第七章
        mainBinding.mbutton4.setOnClickListener {
            SevenMainActivity.actionStart(this, "第六章")
        }
        // 第八章
        mainBinding.mbutton5.setOnClickListener {
            EightMainActivity.actionStart(this, "第八章")
        }
        // 第九章
        mainBinding.mbutton6.setOnClickListener {
            NineActivity.actionStart(this, "第九章")
        }
        // 第十章
        mainBinding.mbutton7.setOnClickListener {
            EightMainActivity.actionStart(this, "第十章")
        }
        // 第十一章
        mainBinding.mbutton8.setOnClickListener {
            EightMainActivity.actionStart(this, "第十一章")
        }
    }
}