package com.example.activitytest.twelve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.KTwelveMainBinding
import com.example.activitytest.ten.TenMainActivity

class TwelveMainActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, TwelveMainActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }
    lateinit var kTwelveMainBinding: KTwelveMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        kTwelveMainBinding = KTwelveMainBinding.inflate(layoutInflater)
        setContentView(kTwelveMainBinding.root)
        kTwelveMainBinding.toolBarBtn.setOnClickListener{
            ToolbarActivity.actionStart(this,"toolbar")
        }
        kTwelveMainBinding.drawerLayoutBtn.setOnClickListener{
            DrawerLayoutActivity.actionStart(this,"DrawerLayout")
        }
        kTwelveMainBinding.navigationViewBtn.setOnClickListener{
            NavigationViewActivity.actionStart(this, "NavigationView")
        }
        kTwelveMainBinding.materialCardViewBtn.setOnClickListener{
            MaterialCardViewActivity.actionStart(this, "MaterialCardView")
        }
    }
}