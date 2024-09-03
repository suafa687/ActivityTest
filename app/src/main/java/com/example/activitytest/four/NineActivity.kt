package com.example.activitytest.four

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.R
import com.example.activitytest.databinding.DnineLayoutBinding
import com.example.activitytest.BaseActivity
import com.example.activitytest.four.base.Fruit
import com.example.activitytest.four.base.FruitAdapter

class NineActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, NineActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    private var nineLayoutBinding: DnineLayoutBinding? = null
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        nineLayoutBinding = DnineLayoutBinding.inflate(layoutInflater)
        setContentView(nineLayoutBinding?.root)
        initFruits() // 初始化水果数据
        val adapter = FruitAdapter(this, R.layout.dfruit_item, fruitList)
        nineLayoutBinding?.listView?.adapter = adapter
        nineLayoutBinding?.listView?.setOnItemClickListener { _, _, position, _ ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
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

}