package com.example.livedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuestionEntity::class],version = 1)
abstract class MyDatabase:RoomDatabase() {
    abstract fun daoOfQuestion(): DaoOfQuestion

    companion object {
        var INSTANCE: MyDatabase? = null

        fun getAppDataBase(context: Context): MyDatabase? {
            if (INSTANCE == null){
                synchronized(MyDatabase::class){
                    INSTANCE =
                        Room.databaseBuilder(context.applicationContext,
                            MyDatabase::class.java, "myDB",)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}