package com.example.bottleshop_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bottleshop_app.databinding.ActivityJoinBinding
import com.example.bottleshop_app.db.UserDatabase

class JoinActivity : AppCompatActivity(){
    private lateinit var binding: ActivityJoinBinding
    private lateinit var db: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinBinding.inflate(layoutInflater)

        db = UserDatabase.getInstance(this)



    }
    private fun setOnClick() {
        val btn_join_ok = binding.btnJoinOk
    }
}