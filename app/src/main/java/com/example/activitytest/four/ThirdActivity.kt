package com.example.activitytest.four

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import com.example.activitytest.R
import com.example.activitytest.four.base.BaseActivity
import com.example.activitytest.databinding.ThirdLayoutBinding

class ThirdActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun actionStart(context: Context, data1: String, extra_data: String) {
            val intent = Intent(context, ThirdActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("extra_data", extra_data)
            context.startActivity(intent)
        }
    }

    private var thirdLayoutBinding: ThirdLayoutBinding? = null

    //onCreate()。这个方法你已经看到过很多次了，
    // 我们在每个Activity中都重写了这个方法，
    // 它会在Activity第一次被创建的时候调用。
    // 你应该在这个方法中完成Activity的初始化操作，比如加载布局、绑定事件等。
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val extra_data = intent.getStringExtra("extra_data")
        Toast.makeText(this, "传递数据$extra_data", Toast.LENGTH_SHORT).show()

        thirdLayoutBinding = ThirdLayoutBinding.inflate(layoutInflater)
        setContentView(thirdLayoutBinding?.root)
        thirdLayoutBinding?.button8?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val input = thirdLayoutBinding?.editTextText?.text?.toString()
        Toast.makeText(this, "你输入的内容是${input}", Toast.LENGTH_SHORT).show()
        thirdLayoutBinding?.textView2?.text = input
        if (thirdLayoutBinding?.progressBar?.visibility == View.VISIBLE) {
            thirdLayoutBinding?.imageView?.setImageResource(R.drawable.ic_launcher_background)
            thirdLayoutBinding?.progressBar?.visibility = View.GONE
        } else {
            thirdLayoutBinding?.imageView?.setImageResource(R.drawable.ic_launcher_foreground)
            thirdLayoutBinding?.progressBar?.visibility = View.VISIBLE
        }
        AlertDialog.Builder(this).apply {
            setTitle("this is alert")
            setMessage("alert test")
            setCancelable(false)
            setPositiveButton("OK") { dialog, which ->
            }
            setNegativeButton("Canel") { dialog, which ->
            }
            show()
        }
    }

}