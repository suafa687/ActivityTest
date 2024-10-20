package com.example.activitytest.thirteen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.LViewModelActivityBinding
import com.example.activitytest.thirteen.base.AppDatabase
import com.example.activitytest.thirteen.base.MainViewModel
import com.example.activitytest.thirteen.base.MainViewModelFactory
import com.example.activitytest.thirteen.base.User
import com.example.activitytest.twelve.base.showToast
import kotlin.concurrent.thread

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

        lViewModelActivityBinding.getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }
        viewModel.user.observe(this) { user ->
            lViewModelActivityBinding.infoText.text = user.firstName
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        lViewModelActivityBinding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        lViewModelActivityBinding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        lViewModelActivityBinding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        lViewModelActivityBinding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    runOnUiThread {
                        user.toString().showToast(this)
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}