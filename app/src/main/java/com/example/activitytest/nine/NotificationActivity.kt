package com.example.activitytest.nine

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.HactivityNotificationBinding

class NotificationActivity : BaseActivity() {
    companion object {
        fun actionStart(
            context: Context,
            title: String,
            content: String,
            image: Int
        ): PendingIntent {
            val intent = Intent(context, NotificationActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("content", content)
            intent.putExtra("image", image)
            val pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            return pi
        }
    }

    lateinit var notifyBind: HactivityNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val image: Int = intent.getIntExtra("image", R.drawable.big_image)
        notifyBind = HactivityNotificationBinding.inflate(layoutInflater)
        supportActionBar?.setTitle(title)
        notifyBind.noticeContentView.setText(content)
        notifyBind.noticeContentImg.setImageResource(image)
        setContentView(notifyBind.root)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        manager.cancel(1)
    }
}
