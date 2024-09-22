package com.example.activitytest.eleven.base

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class ContentHandler : DefaultHandler() {

    private var nodeName = ""

    private lateinit var userId: StringBuilder

    private lateinit var nickname: StringBuilder

    private lateinit var birthday: StringBuilder

    override fun startDocument() {
        userId = StringBuilder()
        nickname = StringBuilder()
        birthday = StringBuilder()
    }

    override fun startElement(
        uri: String, localName: String, qName: String, attributes: Attributes
    ) {
        // 记录当前节点名
        nodeName = localName
        Log.d("ContentHandler", "uri is $uri")
        Log.d("ContentHandler", "localName is $localName")
        Log.d("ContentHandler", "qName is $qName")
        Log.d("ContentHandler", "attributes is $attributes")
    }

    override fun characters(ch: CharArray, start: Int, length: Int) {
        // 根据当前节点名判断将内容添加到哪一个StringBuilder对象中
        when (nodeName) {
            "userId" -> userId.append(ch, start, length)
            "nickname" -> nickname.append(ch, start, length)
            "birthday" -> birthday.append(ch, start, length)
        }
    }

    override fun endElement(uri: String, localName: String, qName: String) {
        if ("data" == localName) {
            Log.d("ContentHandler", "userId is ${userId.toString().trim()}")
            Log.d("ContentHandler", "nickname is ${nickname.toString().trim()}")
            Log.d("ContentHandler", "birthday is ${birthday.toString().trim()}")
            // 最后要将StringBuilder清空
            userId.setLength(0)
            nickname.setLength(0)
            birthday.setLength(0)
        }
    }

    override fun endDocument() {
    }

}