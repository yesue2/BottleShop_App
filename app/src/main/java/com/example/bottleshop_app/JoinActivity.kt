package com.example.bottleshop_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bottleshop_app.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickBtn()

        // 확인 버튼 눌렀을 때
        val btn_join_ok = binding.btnJoinOk
        btn_join_ok.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

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
}