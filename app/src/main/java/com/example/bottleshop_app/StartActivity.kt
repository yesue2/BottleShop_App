package com.example.bottleshop_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bottleshop_app.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity(){
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickBtn()
    }

    fun setOnClickBtn() {
        val btn_start_login = binding.btnStartLogin
        val btn_start_join = binding.btnStartJoin

        btn_start_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btn_start_join.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }

}