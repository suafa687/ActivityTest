package com.example.activitytest.five

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.EfiveMainLayoutBinding

class FiveMainActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, FiveMainActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    private lateinit var emainLayout: EfiveMainLayoutBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        emainLayout = EfiveMainLayoutBinding.inflate(layoutInflater)
        setContentView(emainLayout.root)

        emainLayout.button51.setOnClickListener {
            FirstActivity.actionStart(this, "par1")
        }
    }
}