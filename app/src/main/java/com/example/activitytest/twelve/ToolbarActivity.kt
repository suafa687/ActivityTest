package com.example.activitytest.twelve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.KActivityToolbarBinding
import com.example.activitytest.twelve.base.showToast

class ToolbarActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, ToolbarActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var kActivityToolbarBinding: KActivityToolbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        kActivityToolbarBinding = KActivityToolbarBinding.inflate(layoutInflater)
        setContentView(kActivityToolbarBinding.root)
        setSupportActionBar(kActivityToolbarBinding.toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> "You clicked Backup".showToast(this)
            R.id.delete -> "You clicked Delete".showToast(this)
            R.id.settings -> "You clicked Settings".showToast(this)
        }
        return true
    }
}