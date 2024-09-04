package com.example.activitytest.five.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.activitytest.R
import com.example.activitytest.databinding.EnewsContentFragBinding

class NewsContentFragment : Fragment() {

     lateinit var newsContentFrag : EnewsContentFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        newsContentFrag = EnewsContentFragBinding.inflate(layoutInflater)
        return inflater.inflate(R.layout.enews_content_frag, container, false)
    }

    fun refresh(title: String, content: String) {
        newsContentFrag.contentLayout.visibility = View.VISIBLE
        newsContentFrag.newsTitle.text = title // 刷新新闻的标题
        newsContentFrag.newsContent.text = content // 刷新新闻的内容
    }

}