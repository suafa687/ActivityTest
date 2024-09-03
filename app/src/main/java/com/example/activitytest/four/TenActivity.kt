package com.example.activitytest.four

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.activitytest.R
import com.example.activitytest.databinding.DtenLayoutBinding
import com.example.activitytest.BaseActivity
import com.example.activitytest.four.base.Fruit
import com.example.activitytest.four.base.NewFruitAdapter

class TenActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, data: String) {
            val intent = Intent(context, TenActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }

    private val fruitList = ArrayList<Fruit>()
    var tenLayoutBinding: DtenLayoutBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        tenLayoutBinding = DtenLayoutBinding.inflate(layoutInflater)
        setContentView(tenLayoutBinding?.root)

        val extra_data = intent.getStringExtra("data")

        if (extra_data == "horizontal") {
            initFruit() // 初始化水果数据
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            tenLayoutBinding?.recyclerView?.layoutManager = layoutManager
            val adapter = NewFruitAdapter(fruitList, R.layout.dfruit_item_horizontal)
            tenLayoutBinding?.recyclerView?.adapter = adapter
        } else if (extra_data == "vertical") {
            initFruit() // 初始化水果数据
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            tenLayoutBinding?.recyclerView?.layoutManager = layoutManager
            val adapter = NewFruitAdapter(fruitList, R.layout.dfruit_item)
            tenLayoutBinding?.recyclerView?.adapter = adapter
        } else if (extra_data == "staggered_grid") {
            initFruits() // 初始化水果数据
            val layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
            tenLayoutBinding?.recyclerView?.layoutManager = layoutManager
            val adapter = NewFruitAdapter(fruitList, R.layout.dfruit_item_staggered_grid)
            tenLayoutBinding?.recyclerView?.adapter = adapter
        }


    }

    private fun initFruit() {
        repeat(2) {
            fruitList.add(Fruit("Apple", R.drawable.apple_pic))
            fruitList.add(Fruit("Banana", R.drawable.banana_pic))
            fruitList.add(Fruit("Orange", R.drawable.orange_pic))
            fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic))
            fruitList.add(Fruit("Pear", R.drawable.pear_pic))
            fruitList.add(Fruit("Grape", R.drawable.grape_pic))
            fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic))
            fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic))
            fruitList.add(Fruit("Cherry", R.drawable.cherry_pic))
            fruitList.add(Fruit("Mango", R.drawable.mango_pic))
        }
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(
                Fruit(
                    getRandomLengthString("Apple"),
                    R.drawable.apple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Banana"),
                    R.drawable.banana_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Orange"),
                    R.drawable.orange_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Watermelon"),
                    R.drawable.watermelon_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Pear"),
                    R.drawable.pear_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Grape"),
                    R.drawable.grape_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Pineapple"),
                    R.drawable.pineapple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Strawberry"),
                    R.drawable.strawberry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Cherry"),
                    R.drawable.cherry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Mango"),
                    R.drawable.mango_pic
                )
            )
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}