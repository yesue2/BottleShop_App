package com.example.bottleshop_app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bottleshop_app.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickBtn()
    }

    private fun setOnClickBtn() {
        val btn_back = binding.btnBack
        val btn_login = binding.btnLogin
        val btn_join = binding.btnJoin
        val msg_search_password = binding.msgSearchPassword

        btn_back.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_login.setOnClickListener {
            if (checkRegister() == true) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this,"로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_join.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
            finish()
        }

        msg_search_password.setOnClickListener {
            // TODO(비밀번호 재설정 할 이메일 입력 팝업 출력 코드 작성)
        }
    }

    private fun checkRegister():Boolean {
        var loginResult = false

        return loginResult
    }
}