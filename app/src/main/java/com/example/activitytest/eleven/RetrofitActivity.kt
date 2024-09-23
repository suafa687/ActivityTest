package com.example.activitytest.eleven

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.databinding.JRetrofitActivityBinding
import com.example.activitytest.eleven.model.User
import com.example.activitytest.eleven.retrofit.UserService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, params: String) {
            val intent = Intent(context, RetrofitActivity::class.java)
            intent.putExtra("params", params)
            context.startActivity(intent)
        }
    }

    lateinit var jRetrofitActivityBinding: JRetrofitActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        jRetrofitActivityBinding = JRetrofitActivityBinding.inflate(layoutInflater)
        setContentView(jRetrofitActivityBinding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.105:8085/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val userService = retrofit.create(UserService::class.java)

        jRetrofitActivityBinding.useAllRequestBtn.setOnClickListener {
            userService.queryAllJson().enqueue(object : Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
                ) {
                    val list = response.body()
                    if (list != null) {
                        for (user in list) {
                            Log.d("MainActivity", "id is ${user.userId}")
                            Log.d("MainActivity", "name is ${user.nickname}")
                            Log.d("MainActivity", "version is ${user.birthday}")
                        }
                        showResponse(list.toString())
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

        jRetrofitActivityBinding.userRequestBtn.setOnClickListener {
            userService.queryOneJson(1).enqueue(object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    val user = response.body()
                    Log.d("MainActivity", "id is ${user?.userId}")
                    Log.d("MainActivity", "name is ${user?.nickname}")
                    Log.d("MainActivity", "version is ${user?.birthday}")
                    showResponse(user.toString())
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

        jRetrofitActivityBinding.userParRequestBtn.setOnClickListener {
            userService.queryOneJsonWithPar(2).enqueue(object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    val user = response.body()
                    Log.d("MainActivity", "id is ${user?.userId}")
                    Log.d("MainActivity", "name is ${user?.nickname}")
                    Log.d("MainActivity", "version is ${user?.birthday}")
                    showResponse(user.toString())
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

        jRetrofitActivityBinding.userInsertRequestBtn.setOnClickListener {
            val user = User(3,"liull", "刘","2024-09-23",1,1)

            userService.insertData(user).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    showResponse(response.body().toString())
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在这里进行UI操作，将结果显示到界面上
            jRetrofitActivityBinding.responseText.text = response
        }
    }
}