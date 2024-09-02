package com.example.activitytest.four

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.activitytest.databinding.ElevenLayoutBinding
import com.example.activitytest.four.base.BaseActivity
import com.example.activitytest.four.base.Msg
import com.example.activitytest.four.base.MsgAdapter
// import com.example.activitytest.databinding.ElevenLayoutBinding


class ElevenActivity : BaseActivity(), View.OnClickListener {
    companion object {
        fun actionStart(context: Context, data: String) {
            val intent = Intent(context, ElevenActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }

    private lateinit var elevenLayoutBinding: ElevenLayoutBinding
    private val msgList = ArrayList<Msg>()
    private var adapter: MsgAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        elevenLayoutBinding = ElevenLayoutBinding.inflate(layoutInflater)
        setContentView(elevenLayoutBinding.root)

        initMsg()
        val layoutManager = LinearLayoutManager(this)
        elevenLayoutBinding.recyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(msgList)
        elevenLayoutBinding.recyclerView.adapter = adapter
        elevenLayoutBinding.send.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            elevenLayoutBinding.send -> {
                val content = elevenLayoutBinding.inputText.text.toString()
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1)
                    // 当有新消息时，                    刷新RecyclerView中的显示
                    elevenLayoutBinding.recyclerView.scrollToPosition(msgList.size - 1)
                    // 将RecyclerView                    定位到最后一行
                    elevenLayoutBinding.inputText.setText("") // 清空输入框中的内容
                }
            }
        }
    }

    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)
    }

}