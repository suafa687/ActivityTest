package com.example.activitytest.ten

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.ITenMainActivityBinding
import com.example.activitytest.ten.base.MyIntentService
import com.example.activitytest.ten.base.MyService
import kotlin.concurrent.thread

class TenMainActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, TenMainActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var tenBind: ITenMainActivityBinding
    val updateText = 1
    val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            // 在这里可以进行UI操作
            when (msg.what) {
                updateText -> tenBind.textView.text = "Nice to meet you"
            }
        }
    }
    lateinit var downloadBinder: MyService.DownloadBinder
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        tenBind = ITenMainActivityBinding.inflate(layoutInflater)
        setContentView(tenBind.root)
        tenBind.changeTextBtn.setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg) // 将Message对象发送出去
            }
        }

        tenBind.startServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent) // 启动Service
        }
        tenBind.stopServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent) // 停止Service
        }

        tenBind.bindServiceBtn.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE) // 绑定Service
        }
        tenBind.unbindServiceBtn.setOnClickListener {
            unbindService(connection) // 解绑Service
        }
        tenBind.startIntentServiceBtn.setOnClickListener {
            // 打印主线程的id
            Log.d("MainActivity", "Thread id is ${Thread.currentThread().name}")
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
    }
}