package com.example.activitytest.five

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import com.example.activitytest.BaseActivity
import com.example.activitytest.R
import com.example.activitytest.databinding.EfirstLayoutBinding
import com.example.activitytest.five.base.AnotherRightFragment
import com.example.activitytest.five.base.LeftFragment
import com.example.activitytest.five.base.RightFragment

class FirstActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, FirstActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    private lateinit var firstLayoutBinding: EfirstLayoutBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        firstLayoutBinding = EfirstLayoutBinding.inflate(layoutInflater)
        setContentView(firstLayoutBinding.root)

        val button: Button = findViewById(R.id.left_fragemen_button)

        button.setOnClickListener {
                replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
        val fragment = supportFragmentManager.findFragmentById(R.id.leftFrag) as LeftFragment
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.rightLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}