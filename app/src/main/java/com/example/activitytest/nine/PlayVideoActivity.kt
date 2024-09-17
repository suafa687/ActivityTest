package com.example.activitytest.nine

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.HVideoActivityBinding

class PlayVideoActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, PlayVideoActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }
    lateinit var videoBind : HVideoActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        videoBind = HVideoActivityBinding.inflate(layoutInflater)
        setContentView(videoBind.root)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoBind.videoView.setVideoURI(uri)
        videoBind.play.setOnClickListener {
            if (!videoBind.videoView.isPlaying) {
                videoBind.videoView.start() // 开始播放
            }
        }
        videoBind.pause.setOnClickListener {
            if (videoBind.videoView.isPlaying) {
                videoBind.videoView.pause() // 暂停播放
            }
        }
        videoBind.replay.setOnClickListener {
            if (videoBind.videoView.isPlaying) {
                videoBind.videoView.resume() // 重新播放
            }
        }
    }
}