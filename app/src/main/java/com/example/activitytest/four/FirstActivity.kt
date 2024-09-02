package com.example.activitytest.four

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.activitytest.R
import com.example.activitytest.four.base.BaseActivity
import com.example.activitytest.databinding.FirstLayoutBinding

class FirstActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params:String) {
            val intent = Intent(context, FirstActivity::class.java)
            intent.putExtra("params",params)
            context.startActivity(intent)
        }
    }
    private val tag = "FirstActivity"
    private var firstLayoutBinding: FirstLayoutBinding? = null;
    lateinit var lunch01: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        Log.d(tag, "Task id is $taskId")
        enableEdgeToEdge()
        firstLayoutBinding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(firstLayoutBinding?.root)
        lunch01 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //此处使用lambda表达式，是第二个参数ActivityResultCallback的实现
            if (it.resultCode == Activity.RESULT_OK) {
                Toast.makeText(
                    this,
                    "数据成功返回： ${it.data?.extras?.get("data_return")}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "数据失败返回", Toast.LENGTH_SHORT).show()
            }
        }
        firstLayoutBinding?.button?.setOnClickListener {
            val intent = Intent("com.example.activitytest.ACTION_START")
            intent.addCategory("com.example.activitytest.MY_CATEGORY")
            intent.putExtra("extra_data", "Hello, SecondActivity")
            lunch01.launch(intent)
        }
        firstLayoutBinding?.button2?.setOnClickListener {
            finish()
        }
        firstLayoutBinding?.button6?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }
        firstLayoutBinding?.button7?.setOnClickListener {
            val intent = Intent(this, DialogActivity::class.java)
            startActivity(intent)
        }
        firstLayoutBinding?.button8?.setOnClickListener {
            ThirdActivity.actionStart(this, "par1", "hello page3")
        }
        firstLayoutBinding?.button19?.setOnClickListener {
            FourActivity.actionStart(this, "hello page4")
        }
        firstLayoutBinding?.button110?.setOnClickListener {
            ElevenActivity.actionStart(this, "hello page4")
        }
    }

    // onDestroy()。这个方法在Activity被销毁之前调用，之后Activity的状态将变为销毁状态。
    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    // onStart()。这个方法在Activity由不可见变为可见的时候调用。
    override fun onStart() {
        super.onStart()
        Log.d(tag, "onRestart")
    }

    // onStop()。这个方法在Activity完全不可见的时候调用。
    // 它和onPause()方法的主要区别在于，
    // 如果启动的新Activity是一个对话框式的Activity，那么onPause()方法会得到执行，而onStop()方法并不会执行。
    override fun onStop() {
        super.onStop()
        Log.d(tag, "onRestart")
    }

    // onResume()。这个方法在Activity准备好和用户进行交互的时候调用。
    // 此时的Activity一定位于返回栈的栈顶，并且处于运行状态。
    override fun onResume() {
        super.onResume()
        Log.d(tag, "onRestart")
    }

    //  onPause()。这个方法在系统准备去启动或者恢复另一个Activity的时候调用。
    //  我们通常会在这个方法中将一些消耗CPU的资源释放掉，以及保存一些关键数据，
    //  但这个方法的执行速度一定要快，不然会影响到新的栈顶Activity的使用。
    override fun onPause() {
        super.onPause()
        Log.d(tag, "onRestart")
    }

    // onRestart()。这个方法在Activity由停止状态变为运行状态之前调用，也就是Activity被重新启动了。
    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }

}