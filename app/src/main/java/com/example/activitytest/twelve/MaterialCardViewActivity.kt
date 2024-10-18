package com.example.activitytest.twelve

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.activitytest.R
import com.example.activitytest.databinding.KMaterialCardViewBinding
import com.example.activitytest.twelve.base.Fruit
import com.example.activitytest.twelve.base.FruitAdapter
import com.example.activitytest.twelve.base.showToast
import kotlin.concurrent.thread


class MaterialCardViewActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, MaterialCardViewActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple), Fruit(
            "Banana",
            R.drawable.banana
        ), Fruit("Orange", R.drawable.orange), Fruit(
            "Watermelon",
            R.drawable.watermelon
        ), Fruit("Pear", R.drawable.pear), Fruit(
            "Grape",
            R.drawable.grape
        ), Fruit("Pineapple", R.drawable.pineapple), Fruit(
            "Strawberry",
            R.drawable.strawberry
        ), Fruit("Cherry", R.drawable.cherry), Fruit(
            "Mango",
            R.drawable.mango
        )
    )

    val fruitList = ArrayList<Fruit>()

    lateinit var kMaterialCardViewBinding: KMaterialCardViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        kMaterialCardViewBinding = KMaterialCardViewBinding.inflate(layoutInflater)
        setContentView(kMaterialCardViewBinding.root)
        setSupportActionBar(kMaterialCardViewBinding.toolbar)
        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        kMaterialCardViewBinding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(this, fruitList)
        kMaterialCardViewBinding.recyclerView.adapter = adapter

        kMaterialCardViewBinding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        kMaterialCardViewBinding.swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                kMaterialCardViewBinding.swipeRefresh.isRefreshing = false
            }
        }
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
