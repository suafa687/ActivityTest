package com.example.activitytest.thirteen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.LViewModelActivityBinding
import com.example.activitytest.thirteen.base.MainViewModel
import com.example.activitytest.thirteen.base.MainViewModelFactory

class ViewModelActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, ViewModelActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences
    lateinit var lViewModelActivityBinding: LViewModelActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lViewModelActivityBinding = LViewModelActivityBinding.inflate(layoutInflater)
        setContentView(lViewModelActivityBinding.root)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        // viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved))
            .get(MainViewModel::class.java)
        lViewModelActivityBinding.plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        lViewModelActivityBinding.clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this) { count ->
            lViewModelActivityBinding.infoText.text = count.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}