package com.example.activitytest.twelve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.KDrawerLayoutBinding

class DrawerLayoutActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, DrawerLayoutActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }
    lateinit var kDrawerLayoutBinding: KDrawerLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        kDrawerLayoutBinding = KDrawerLayoutBinding.inflate(layoutInflater)
        setContentView(kDrawerLayoutBinding.root)
        setSupportActionBar(kDrawerLayoutBinding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> kDrawerLayoutBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}