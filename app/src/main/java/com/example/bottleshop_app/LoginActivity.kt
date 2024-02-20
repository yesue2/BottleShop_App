package com.example.bottleshop_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bottleshop_app.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        setOnClickBtn()
    }

    private fun setOnClickBtn() {
        val btn_back = binding.btnBack
        val btn_login = binding.btnLogin
        val btn_join = binding.btnJoin
        val msg_search_password = binding.msgSearchPassword

        // 뒤로가기 버튼
        btn_back.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 로그인 버튼
        btn_login.setOnClickListener {
            login()
        }

        // 회원가입 버튼
        btn_join.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
            finish()
        }

        msg_search_password.setOnClickListener {
            // TODO(비밀번호 재설정 할 이메일 입력 팝업 출력 코드 작성)
        }
    }

    // 로그인
    private fun login() {
        val email = binding.etAdress.text.toString()
        val password = binding.etPassword.text.toString()
        if (email.isBlank()) {
            Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (password.isBlank()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginActivity", "signInWithEmail:success")
                    user = auth.currentUser

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                    finish()
                } else {
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}