package com.example.livedata

import androidx.room.*

@Dao
interface DaoOfQuestion {
    @Query("SELECT * fROM QuestionEntity")
    fun getAll(): List<QuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg questions: QuestionEntity)

    @Delete
    fun delete(question : QuestionEntity)

}