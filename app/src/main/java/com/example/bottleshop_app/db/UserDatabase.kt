package com.example.bottleshop_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [UserEntity::class],  //  이 db에 어떤 테이블들이 있는지
    version = 1,  //  스키마가 바뀔 때 버전도 바뀌어야함
    exportSchema = false  //  스키마 구조를 폴더로 export할 수 있음
)
@TypeConverters(OrmConverter::class)
abstract class UserDatabase : RoomDatabase() {  // RoomDatabase 상속하는 추상클래스
    abstract fun getLoginDAO(): LoginDAO  // DAO를 반환하고 인수가 존재하지 않는 추상 함수

    companion object {
        @Volatile  // Java 변수를 Main Memory에 저장
        private var INSTANCE: UserDatabase? = null

        private fun buildDatabase(context: Context): UserDatabase = Room.databaseBuilder(
            context, UserDatabase::class.java, "db_user"
        ).build()

        fun getInstance(context: Context): UserDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }
    }
}