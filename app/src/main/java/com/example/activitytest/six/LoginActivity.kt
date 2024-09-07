package com.example.activitytest.six

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.activitytest.BaseActivity
import com.example.activitytest.MainActivity
import com.example.activitytest.databinding.EactivityLoginBinding

class LoginActivity : BaseActivity() {
    companion object {
        fun actionStart(context: Context, data: String) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.putExtra("data", data)
            context.startActivity(intent)
        }
    }

    lateinit var loginActiveBind: EactivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        supportActionBar?.hide()
        loginActiveBind = EactivityLoginBinding.inflate(layoutInflater)
        setContentView(loginActiveBind.root)

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password", false)
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            val account = prefs.getString("account", "")
            val password = prefs.getString("password", "")
            loginActiveBind.accountEdit.setText(account)
            loginActiveBind.passwordEdit.setText(password)
            loginActiveBind.rememberPass.isChecked = true
        }

        loginActiveBind.login.setOnClickListener {
            val account = loginActiveBind.accountEdit.text.toString()
            val password = loginActiveBind.passwordEdit.text.toString()
            // 如果账号是admin且密码是123456，就认为登录成功
            if (account == "admin" && password == "123") {
                val editor = prefs.edit()
                if (loginActiveBind.rememberPass.isChecked) { // 检查复选框是否被选中
                    editor.putBoolean("remember_password", true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                } else {
                    editor.clear()
                }
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this, "account or password is invalid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}