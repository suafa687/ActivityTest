package com.example.activitytest.nine

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.app.NotificationCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.HnineActivityBinding

class NineActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, NineActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    var msgId = 1
    val channelId = "channelId"
    lateinit var nineActiveBind: HnineActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        nineActiveBind = HnineActivityBinding.inflate(layoutInflater)
        setContentView(nineActiveBind.root)
        supportActionBar?.hide()

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Normal", NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }
        nineActiveBind.hNotice.setOnClickListener {
            val title = "通知"
            val content = nineActiveBind.noticeContent.text.toString()
            val image = R.drawable.big_image
            val pi = NotificationActivity.actionStart(this, title, content, image)
            val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(content)
//                .setStyle(NotificationCompat.BigTextStyle().bigText(content))
                .setStyle(
                    NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(resources, image)
                    )
                )
                .setSmallIcon(R.drawable.small_icon)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.large_icon
                    )
                )
                .setContentIntent(pi)
//                .setAutoCancel(true)
                .build()
            manager.notify(msgId, notification)
        }
        nineActiveBind.hcamra.setOnClickListener{
            CameraActivity.actionStart(this,"camera")
        }
        nineActiveBind.haudio.setOnClickListener{
            PlayAudioActivity.actionStart(this,"audio")
        }
        nineActiveBind.hvideo.setOnClickListener{
            PlayVideoActivity.actionStart(this, "video")
        }
    }
}