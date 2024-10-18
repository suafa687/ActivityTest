package com.example.activitytest.twelve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.activitytest.R
import com.example.activitytest.databinding.KActivityFruitBinding
import com.example.activitytest.databinding.KFruitItemBinding

class FruitActivity : AppCompatActivity() {
    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, FruitActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var kActivityFruitBinding: KActivityFruitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        kActivityFruitBinding = KActivityFruitBinding.inflate(layoutInflater)
        setContentView(kActivityFruitBinding.root)

        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        setSupportActionBar(kActivityFruitBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        kActivityFruitBinding.collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(kActivityFruitBinding.fruitImageView)
        kActivityFruitBinding.fruitContentText.text = generateFruitContent(fruitName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generateFruitContent(fruitName: String) = fruitName.repeat(500)
}