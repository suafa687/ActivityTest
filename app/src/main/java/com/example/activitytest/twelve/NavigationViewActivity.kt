package com.example.activitytest.twelve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.KNavigationViewBinding
import com.google.android.material.snackbar.Snackbar

class NavigationViewActivity  : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, NavigationViewActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }
    lateinit var kNavigationViewBinding: KNavigationViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        kNavigationViewBinding =KNavigationViewBinding.inflate(layoutInflater)
        setContentView(kNavigationViewBinding.root)
        setSupportActionBar(kNavigationViewBinding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        kNavigationViewBinding.navView.setCheckedItem(R.id.navCall)
        kNavigationViewBinding.navView.setNavigationItemSelectedListener {
            kNavigationViewBinding.drawerLayout.closeDrawers()
            true
        }
        kNavigationViewBinding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo") {
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> kNavigationViewBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}