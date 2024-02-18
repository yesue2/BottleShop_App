package com.example.bottleshop_app.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoginDAO {
    @Query("select * from UserInfo")
    fun getEmailList(): List<UserEntity>  // 등록된 회원인지 확인

    @Query("select password from userinfo where email = :email")
    fun getPasswordByEmail(email: String): String  // 이메일에 따른 비밀번호 반환

    @Insert
    suspend fun insertUser(userInfo: UserEntity)  // 회원 등록 (코루틴 안에서 비동기적으로 수행)

    @Delete
    suspend fun deleteUser(userInfo: UserEntity)  // 회원 삭제 (코루틴 안에서 비동기적으로 수행)
}