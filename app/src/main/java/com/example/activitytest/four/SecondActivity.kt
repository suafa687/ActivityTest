package com.example.activitytest.four

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.databinding.DsecondLayoutBinding
import com.example.activitytest.BaseActivity

class SecondActivity : BaseActivity() {
    private var secondLayoutBinding: DsecondLayoutBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extra_data = intent.getStringExtra("extra_data")
        Toast.makeText(this, "传递数据$extra_data", Toast.LENGTH_SHORT).show()
        enableEdgeToEdge()
        secondLayoutBinding = DsecondLayoutBinding.inflate(layoutInflater)
        //enableEdgeToEdge()
        setContentView(secondLayoutBinding?.root)
        secondLayoutBinding?.button3?.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return", "Hello FirstActivity")
            setResult(RESULT_OK, intent)
            finish()
        }
        secondLayoutBinding?.button4?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.baidu.com?" + extra_data)
            startActivity(intent)
        }
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}
