package com.example.activitytest.four

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.four.base.BaseActivity
import com.example.activitytest.databinding.FourLayoutBinding

class FourActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, data: String) {
            val intent = Intent(context, FourActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }

    private var fourLayoutBinding: FourLayoutBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()

        val extra_data = intent.getStringExtra("data")
        Toast.makeText(this, "传递数据$extra_data", Toast.LENGTH_SHORT).show()

        fourLayoutBinding = FourLayoutBinding.inflate(layoutInflater)
        setContentView(fourLayoutBinding?.root)

        fourLayoutBinding?.button41?.setOnClickListener {
            FiveActivity.actionStart(this)
        }

        fourLayoutBinding?.button42?.setOnClickListener {
            SixActivity.actionStart(this, "data")
        }

        fourLayoutBinding?.button43?.setOnClickListener {
            SevenActivity.actionStart(this, "data")
        }

        fourLayoutBinding?.button45?.setOnClickListener {
            NineActivity.actionStart(this, "data")
        }

        fourLayoutBinding?.button46?.setOnClickListener {
            TenActivity.actionStart(this, "horizontal")
        }

        fourLayoutBinding?.button47?.setOnClickListener {
            TenActivity.actionStart(this, "vertical")
        }

        fourLayoutBinding?.button48?.setOnClickListener {
            TenActivity.actionStart(this, "staggered_grid")
        }
    }
}