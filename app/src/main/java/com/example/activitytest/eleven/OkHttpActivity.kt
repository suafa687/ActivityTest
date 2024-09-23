package com.example.activitytest.eleven

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.JActivityOkHttpBinding
import com.example.activitytest.eleven.base.ContentHandler
import com.example.activitytest.eleven.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class OkHttpActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, OkHttpActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var okHttpBinding: JActivityOkHttpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        okHttpBinding = JActivityOkHttpBinding.inflate(layoutInflater)
        setContentView(okHttpBinding.root)

        okHttpBinding.jsonRequestBtn.setOnClickListener {
            sendRequestWithOkHttp("http://192.168.0.105:8085/queryOneJson/1", ::parseJSONWithJSONObject)
        }

        okHttpBinding.gsonRequestBtn.setOnClickListener {
            sendRequestWithOkHttp("http://192.168.0.105:8085/queryAllJson", ::parseJSONWithGSON)
        }

        okHttpBinding.pullRequestBtn.setOnClickListener {
            sendRequestWithOkHttp("http://192.168.0.105:8085/queryOne/1", ::parseXMLWithPull)
        }

        okHttpBinding.saxRequestBtn.setOnClickListener {
            sendRequestWithOkHttp("http://192.168.0.105:8085/queryAll", ::parseXMLWithSAX)
        }
    }

    private fun sendRequestWithOkHttp(url: String, function: (String) -> Unit) {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
                    function(responseData)
                    showResponse(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作，将结果显示到界面上
            okHttpBinding.responseText.text = response
        }
    }

    private fun parseJSONWithJSONObject(jsonData: String) {
        try {
            val jsonObject = JSONObject(jsonData)
            val nickname = jsonObject.getString("nickname")
            val birthday = jsonObject.getString("birthday")
            Log.d("MainActivity", "nickname is $nickname")
            Log.d("MainActivity", "birthday is $birthday")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseJSONWithGSON(jsonData: String) {
        val gson = Gson()
        val typeOf = object : TypeToken<List<User>>() {}.type
        val appList = gson.fromJson<List<User>>(jsonData, typeOf)
        for (app in appList) {
            Log.d("MainActivity", "id is ${app.userId}")
            Log.d("MainActivity", "name is ${app.nickname}")
            Log.d("MainActivity", "version is ${app.birthday}")
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType
            var userId = ""
            var nickname = ""
            var birthday = ""
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val nodeName = xmlPullParser.name
                when (eventType) {
                    // 开始解析某个节点
                    XmlPullParser.START_TAG -> {
                        when (nodeName) {
                            "userId" -> userId = xmlPullParser.nextText()
                            "nickname" -> nickname = xmlPullParser.nextText()
                            "birthday" -> birthday = xmlPullParser.nextText()
                        }
                    }
                    // 完成解析某个节点
                    XmlPullParser.END_TAG -> {
                        if ("data" == nodeName) {
                            Log.d("MainActivity", "userId is $userId")
                            Log.d("MainActivity", "nickname is $nickname")
                            Log.d("MainActivity", "birthday is $birthday")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseXMLWithSAX(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()
            // 将ContentHandler的实例设置到XMLReader中
            xmlReader.contentHandler = handler
            // 开始执行解析
            xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


