package com.example.bottleshop_app

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bottleshop_app.databinding.ActivityJoinBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class JoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        // 뒤로가기 버튼
        setOnClickBtn()

        // 확인 버튼
        val btn_join_ok = binding.btnJoinOk
        btn_join_ok.setOnClickListener {
            // 회원가입
            Join()
        }
    }

    private fun setOnClickBtn() {
        val btn_back = binding.btnBack

        btn_back.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun Join() {
        val name = binding.etJoinName.text.toString()
        val email = binding.etJoinAdress.text.toString()
        val password = binding.etJoinPassword.text.toString()
        val passwordCheck = binding.etJoinPasswordCheck.text.toString()

        if (name.isBlank()) {
            Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        } else if (email.isBlank()) {
            // TODO(이메일 형식 검사 추가 구현)
            Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        } else if (password.isBlank()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        } else if (password != passwordCheck) {
            Toast.makeText(this, "비밀번호 확인이 틀렸습니다.", Toast.LENGTH_SHORT).show()
            return
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {  // 회원가입 성공
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(this, "회원가입에 성공했습니다. 다시 로그인 해주세요.", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {  // 회원가입 실패
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
}